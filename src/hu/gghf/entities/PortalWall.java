package hu.gghf.entities;

import java.awt.*;

public class PortalWall extends Location implements CellInterface {
    private static PortalWall blue = null;
    private static PortalWall yellow = null;

    public PortalWall(Point position) {
        this.setPosition(position);
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
    public void onStepIn(Location object) {
        if (blue == this  && yellow != null) {
            object.setPosition(yellow.getPosition());
        } else if (yellow == this  && blue != null) {
            object.setPosition(blue.getPosition());
        }
    }

    @Override
    public void onStepOut() {
    }

    @Override
    public boolean isShootable() {
        return true;
    }

    @Override
    public void shoot(Player player, Color color) {
        // TODO: setDirection()

        if (color == Color.BLUE) {
            blue = this;
        } else {
            yellow = this;
        }
    }

    @Override
    public void destroy() { }
}