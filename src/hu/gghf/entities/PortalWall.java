package hu.gghf.entities;

import java.awt.*;

public class PortalWall extends AbstractCell {
    private static PortalWall blue = null;
    private static PortalWall yellow = null;
    private Point position;

    public PortalWall(Point position) {
        this.position = position;
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
    public void onStepIn(Moveable obj) {
        super.onStepIn(obj);

        if (blue == this  && yellow != null) {
            obj.setPosition(yellow.getPosition());
        } else if (yellow == this  && blue != null) {
            obj.setPosition(blue.getPosition());
        }
    }

    private Point getPosition() {
        return position;
    }
}