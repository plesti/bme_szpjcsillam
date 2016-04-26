package hu.gghf.entities;

import hu.gghf.interfaces.CellInterface;
import hu.gghf.interfaces.Location;
import hu.gghf.interfaces.Moveable;
import hu.gghf.model.Application;

import java.awt.*;

public class PortalWall extends Location implements CellInterface {
    private static PortalWall blue = null;
    private static PortalWall yellow = null;
    private static PortalWall red = null;
    private static PortalWall green = null;

    public PortalWall(Point position) {
        this.setPosition(position);
    }

    @Override
    public boolean isStepable() {
        if (blue == this  && yellow != null) {
            return true;
        } else if (yellow == this  && blue != null) {
            return true;
        } else if (red == this  && green != null) {
            return true;
        } else if (green == this  && red != null) {
            return true;
        }
        return false;
    }

    public Color getColor() {
        if (blue == this) {
            return Color.BLUE;
        } else if (yellow == this) {
            return Color.YELLOW;
        } else if (red == this) {
            return Color.RED;
        } else if (green == this) {
            return Color.GREEN;
        }
        return null;
    }

    @Override
    public void onStepIn(Moveable object) {
        if (blue == this && yellow != null) {
            object.setPosition(yellow.getPosition());
            object.setDirection(yellow.getDirection());
        } else if (yellow == this && blue != null) {
            object.setPosition(blue.getPosition());
            object.setDirection(blue.getDirection());
        } else if (red == this && green != null) {
            object.setPosition(green.getPosition());
            object.setDirection(green.getDirection());
        } else if (green == this && red != null) {
            object.setPosition(red.getPosition());
            object.setDirection(red.getDirection());
        }
    }

    @Override
    public void onStepOut(Moveable object) {
    }

    @Override
    public boolean isShootable() {
        return true;
    }

    @Override
    public void shot(Player player, Color color) {
        if (color == Color.BLUE) {
            blue = this;
        } else if (color == Color.YELLOW) {
            yellow = this;
        } else if (color == Color.RED) {
            red = this;
        } else if (color == Color.GREEN) {
            green = this;
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

        Application.printCall(this, String.format("Portal loves: [%s,%s] %s -> %s",
                getPosition().x,
                getPosition().y,
                color,
                isStepable()));
    }
}