package hu.gghf.entities;

import hu.gghf.interfaces.CellInterface;
import hu.gghf.interfaces.Moveable;
import hu.gghf.model.Map;

import java.awt.*;

public class PressurePlate implements CellInterface {
    private final Map map;
    private Point door;

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
        // TODO: sulyozas
        if (d != null)
            d.setOpen(true);
    }

    @Override
    public void onStepOut() {
        Door d = (Door) map.getMapObject(door);

        if (d != null)
            d.setOpen(false);
    }

    @Override
    public boolean isShootable() { return false; }
    @Override
    public void shot(Player player, Color color) { }
}
