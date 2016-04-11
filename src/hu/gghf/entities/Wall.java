package hu.gghf.entities;

import hu.gghf.interfaces.CellInterface;
import hu.gghf.interfaces.Moveable;

public class Wall implements CellInterface {
    @Override
    public boolean isStepable() {
        return false;
    }

    @Override
    public void onStepIn(Moveable object) { }

    @Override
    public void onStepOut(Moveable object) { }

    @Override
    public boolean isShootable() {
        return true;
    }

    @Override
    public void shot(Player player, Color color) { }
}
