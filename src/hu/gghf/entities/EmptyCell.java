package hu.gghf.entities;

import hu.gghf.interfaces.CellInterface;
import hu.gghf.interfaces.Moveable;

public class EmptyCell implements CellInterface {
    @Override
    public boolean isStepable() { return true; }
    @Override
    public void onStepIn(Moveable object) { }
    @Override
    public void onStepOut() { }
    @Override
    public boolean isShootable() { return false; }

    @Override
    public void shot(Player player, Color color) {

    }
}
