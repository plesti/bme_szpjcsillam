package hu.gghf.entities;

import java.awt.*;

public abstract class Location {
    public enum Direction { UP, DOWN, WEST, EAST }

    private Point position;
    private int direction;

    public Point getPosition() { return position; }
    public void setPosition(Point position) { this.position = position; }

    public int getDirection() { return direction; }
    public void setDirection(int direction) { this.direction = direction; }

    /**
     * Ez hivodik meg ha szakadekba lep.
     */
    public abstract void destroy();
}
