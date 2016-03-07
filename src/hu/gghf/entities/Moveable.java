package hu.gghf.entities;

import java.awt.*;

public class Moveable {
    private Point position;
    private int direction;

    public Point getPosition() { return position; }
    public void setPosition(Point position) { this.position = position; }

    public int getDirection() { return direction; }
    public void setDirection(int direction) { this.direction = direction; }
}
