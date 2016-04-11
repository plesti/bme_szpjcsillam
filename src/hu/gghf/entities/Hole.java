package hu.gghf.entities;

import hu.gghf.interfaces.CellInterface;
import hu.gghf.interfaces.Moveable;

public class Hole implements CellInterface {
    @Override
    public boolean isStepable() {
        return true;
    }

    @Override
    public void onStepIn(Moveable object) {
        object.destroy();
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
