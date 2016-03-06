package hu.gghf.entities;

import java.awt.*;

public class Player {
    int zpmCounter = 0;
    private AbstractGameObject bindObject;
    private int direction;
    private Point position;

    public Player(int direction, Point position) {
        this.direction = direction;
        this.position = position;
    }

    public boolean isCarry() { return bindObject != null; }

    public Point getPosition() { return position; }
    public void setPosition(Point position) { this.position = position; }

    public AbstractGameObject getBindObject() { return bindObject; }
    public void setBindObject(AbstractGameObject box) { this.bindObject = box; }
}
