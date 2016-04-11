package hu.gghf.entities;

import hu.gghf.interfaces.CellInterface;
import hu.gghf.interfaces.Moveable;
import hu.gghf.model.Application;
import hu.gghf.model.Map;

import java.awt.*;
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
        if (!discovered && obj.getClass() == Player.class) {
            if (zpm_counter.containsKey(obj)) {
                zpm_counter.put(obj, zpm_counter.get(obj) + 1);
            } else {
                zpm_counter.put(obj, 1);
            }
            Application.printCall(this, "ZPM felveve: " + zpm_counter.get(obj));
            discovered = true;
            putRandomZPM();
        }
    }

    private void putRandomZPM() {
        Random random = new Random();
        CellInterface cell = null;
        Point point = null;

        while (cell == null || (cell.getClass() != EmptyCell.class)) {
            int x = random.nextInt(this.map.maxsize - 1);
            int y = random.nextInt(this.map.maxsize - 1);
            point = new Point(x, y);
            cell = map.getMapObject(point);
            System.out.println("x:" + x + " y:" + y);
        }
        map.setMapObject(point, new ZPM(map));
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
}
