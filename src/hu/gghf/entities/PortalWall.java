package hu.gghf.entities;

import hu.gghf.model.Game;

import java.awt.*;

public class PortalWall extends AbstractCell {
    private static PortalWall blue = null;
    private static PortalWall yellow = null;
    private Point position;

    public PortalWall(Point position) {
        this.position = position;
    }

    private Point getPosition() {
        return position;
    }

    @Override
    public boolean isStepable() {
        if (blue == this  && yellow != null) {
            return true;
        } else if (yellow == this  && blue != null) {
            return true;
        }
        return false;
    }

    @Override
    public void onStepIn(Moveable object) {
        super.onStepIn(object);

        if (blue == this  && yellow != null) {
            object.setPosition(yellow.getPosition());
        } else if (yellow == this  && blue != null) {
            object.setPosition(blue.getPosition());
        }
    }

    @Override
    public boolean isShootable() {
        return true;
    }

    @Override
    public void shoot(Player player, int color) {
        if (color == Game.PORTAL_BLUE) {
            blue = this;
        } else {
            yellow = this;
        }
    }
}