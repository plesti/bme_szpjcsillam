package hu.gghf.entities;

import hu.gghf.interfaces.CellInterface;
import hu.gghf.interfaces.Moveable;

public class Door implements CellInterface {
    private boolean open = false;

    public void setOpen(boolean isopen) { open = isopen; }

    @Override
    public boolean isStepable() {
        return open;
    }

    @Override
    public void onStepIn(Moveable object) { }

    @Override
    public void onStepOut() { }

    @Override
    public boolean isShootable() { return !open; }

    @Override
    public void shot(Player player, Color color) {
    }
}
