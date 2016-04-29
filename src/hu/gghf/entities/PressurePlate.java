package hu.gghf.entities;

import hu.gghf.interfaces.CellInterface;
import hu.gghf.interfaces.Moveable;
import hu.gghf.model.Images;
import hu.gghf.model.Map;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PressurePlate implements CellInterface {
    private final Map map;
    private Point door;
    private float weight = 0.0f;

    public PressurePlate(Map map, Point door) {
        this.door = door;
        this.map = map;
    }

    @Override
    public boolean isStepable() {
        return true;
    }

    @Override
    public void onStepIn(Moveable obj) {
        Door d = (Door) map.getMapObject(door);
        weight += obj.getWeight();

        if (d != null && weight >= 1.0f)
            d.setOpen(true);
    }

    @Override
    public void onStepOut(Moveable obj) {
        Door d = (Door) map.getMapObject(door);
        weight -= obj.getWeight();

        if (d != null && weight < 1.0f)
            d.setOpen(false);
    }

    @Override
    public boolean isShootable() { return false; }
    @Override
    public void shot(Player player, Color color) { }

    @Override
    public BufferedImage getImage() {
        return Images.pressure_plate;
    }
}
