package hu.gghf.entities;

import java.awt.*;

public abstract class AbstractGameObject {
    private Point position;
    private int objecttype = -1;
    public AbstractGameObject childObject;

    public void setChildObject(AbstractGameObject object) { childObject = object; }
    public Point getPosition() { return position; }
    public void setPosition(Point position) { this.position = position; }

    public abstract boolean isStepable();

    public void stepOn(AbstractGameObject object) {
        childObject = object;
    }
    public void stepOut() {
        childObject = null;
    }
}
