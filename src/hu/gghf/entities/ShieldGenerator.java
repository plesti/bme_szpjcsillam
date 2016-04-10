package hu.gghf.entities;

import hu.gghf.interfaces.CellInterface;
import hu.gghf.interfaces.Moveable;
public class ShieldGenerator implements CellInterface {
    public ShieldGenerator() {
    }

    @Override
    public boolean isStepable() {
        return true;
    }

    @Override
    public void onStepIn(Moveable object) {
        // TODO: jatekvege?
    }

    @Override
    public void onStepOut() {
    }

    @Override
    public boolean isShootable() {
        return true;
    }

    @Override
    public void shot(Player player, Color color) {
    }
}
