package hu.gghf.entities;

import hu.gghf.interfaces.CellInterface;
import hu.gghf.interfaces.Moveable;
import hu.gghf.model.Application;
import hu.gghf.model.Images;
import hu.gghf.model.Map;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Random;

public class ZPM implements CellInterface {
    private boolean discovered = false;
    private static java.util.Map<Moveable, Integer> zpm_counter = new HashMap<>();
    private Map map;

    public ZPM(Map map) {
        this.map = map;
    }

    @Override
    public boolean isStepable() {
        return true;
    }

    @Override
    public void onStepIn(Moveable obj) {
        if (!discovered) {
            int zpms = 0;

            if (zpm_counter.containsKey(obj)) {
                zpms = zpm_counter.get(obj) + 1;
                zpm_counter.put(obj, zpms);
            } else {
                zpms = 1;
                zpm_counter.put(obj, zpms);
            }
            discovered = true;

            if (!Application.test && obj.getClass() == Player.class && zpms == 2) {
                putRandomZPM();
            } else if (Application.test && obj.getClass() == Player.class && zpms == 2) {
                putInfrontZPM(obj);
            }

            Application.printCall(this, "ZPM felveve: " + zpm_counter.get(obj));
        }
    }

    private void putInfrontZPM(Moveable obj) {
        Point point = obj.posInDirection(obj.getDirection(), 1);
        CellInterface cell = map.getMapObject(point);

        if (cell.getClass() == EmptyCell.class || (cell.getClass() == ZPM.class && ((ZPM)cell).isDiscovered())) {
            map.setMapObject(point, new ZPM(map));
        }
    }

    private void putRandomZPM() {
        Random random = new Random();
        CellInterface cell = null;
        Point point = null;

        while (cell == null || (cell.getClass() != EmptyCell.class)) {
            int x = random.nextInt(this.map.maxsize);
            int y = random.nextInt(this.map.maxsize);
            point = new Point(x, y);
            cell = map.getMapObject(point);
//            System.out.println("x:" + x + " y:" + y);
        }
        map.setMapObject(point, new ZPM(map));
    }

    public boolean isDiscovered() {
        return discovered;
    }

    @Override
    public void onStepOut(Moveable object) {
    }

    @Override
    public boolean isShootable() {
        return false;
    }

    @Override
    public void shot(Player player, Color color) {

    }

    public static int getZPMCount(Moveable object) {
        if (zpm_counter.containsKey(object)) {
            return zpm_counter.get(object);
        }
        return 0;
    }

    @Override
    public BufferedImage getImage() {
        if (discovered)
            return Images.emptycell;
        return Images.zpm;
    }
}
