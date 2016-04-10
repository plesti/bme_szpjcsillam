package hu.gghf.entities;

import hu.gghf.interfaces.CellInterface;
import hu.gghf.interfaces.Moveable;

public class ZPM implements CellInterface {
    private boolean discovered = false;

    public ZPM() {
    }

    @Override
    public boolean isStepable() {
        return true;
    }

    @Override
    public void onStepIn(Moveable obj) {
        if (!discovered) {
            // TODO: events? addZPM?
//            game.addZPM();
            discovered = true;
        }
    }

    @Override
    public void onStepOut() {
    }

    @Override
    public boolean isShootable() {
        return false;
    }

    @Override
    public void shot(Player player, Color color) {

    }
}
