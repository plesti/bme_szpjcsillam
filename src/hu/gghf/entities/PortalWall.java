package hu.gghf.entities;

import hu.gghf.interfaces.CellInterface;
import hu.gghf.interfaces.Location;
import hu.gghf.interfaces.Moveable;

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
    public void onStepIn(Moveable object) {
        if (blue == this  && yellow != null) {
            object.setPosition(yellow.getPosition());
            object.setDirection(yellow.getDirection());
        } else if (yellow == this  && blue != null) {
            object.setPosition(blue.getPosition());
            object.setDirection(blue.getDirection());
        }
        // TODO: szinek?
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
        if (color == Color.BLUE) {
            blue = this;
            System.out.println(String.format("Kekvagyok: %s,%s", getPosition().x, getPosition().y));
        } else {
            yellow = this;
            System.out.println(String.format("Sargavagyok: %s,%s", getPosition().x, getPosition().y));
        }

        Direction pdir = player.getDirection();

        switch (pdir) {
            case UP:
                setDirection(Direction.DOWN);
                break;
            case DOWN:
                setDirection(Direction.UP);
                break;
            case LEFT:
                setDirection(Direction.RIGHT);
                break;
            case RIGHT:
                setDirection(Direction.LEFT);
                break;
        }
    }
}