package hu.gghf.model;

import java.awt.*;

public abstract class GameObject {
    private Point position;
    private int objecttype;
    private GameObject childObject;
    private boolean stepable;

    public int getType() {
        return objecttype;
    }

    public GameObject getChildObject() {
        return childObject;
    }

    public boolean isStepable() {
        return stepable;
    }
}
