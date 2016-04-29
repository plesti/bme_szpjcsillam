package hu.gghf.entities;

import hu.gghf.interfaces.CellInterface;
import hu.gghf.interfaces.Moveable;
import hu.gghf.model.Images;

import java.awt.image.BufferedImage;

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
    public void onStepOut(Moveable obj) { }

    @Override
    public boolean isShootable() { return !open; }

    @Override
    public void shot(Player player, Color color) {
    }

    @Override
    public BufferedImage getImage() {
        if (open)
            return Images.door_open;
        return Images.door_closed;
    }
}
