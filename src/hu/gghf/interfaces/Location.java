package hu.gghf.interfaces;

import java.awt.*;

public abstract class Location {
    public enum Direction { UP, DOWN, LEFT, RIGHT }

    protected Point position;
    protected Direction direction;

    public Location() {
        position = new Point(0, 0);
        direction = Direction.UP;
    }

    public Point getPosition() {
        return position;
    }
    public void setPosition(Point position) {
        this.position = position;
    }

    public Direction getDirection() {
        return direction;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
