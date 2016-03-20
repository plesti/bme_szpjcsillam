package hu.gghf.entities;

import hu.gghf.model.Application;

import java.awt.*;

public abstract class Location {
    public enum Direction { UP, DOWN, LEFT, RIGHT }

    private Point position;
    private Direction direction;

    public Location() {
        Application.printCall(this, "Location()");

        position = new Point(0, 0);
        direction = Direction.UP;
    }

    public Point getPosition() {
        Application.printCall(this, "getPosition()");

        return position;
    }
    public void setPosition(Point position) {
        Application.printCall(this, "setPosition()");
        this.position = position;
    }

    public Direction getDirection() {
        Application.printCall(this, "getDirection()");

        return direction;
    }
    public void setDirection(Direction direction) {
        Application.printCall(this, "setDirection()");

        this.direction = direction;
    }

    /**
     * Ez hivodik meg ha szakadekba lep.
     */
    public abstract void destroy();
}
