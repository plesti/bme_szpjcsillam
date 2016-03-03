package hu.gghf.model;

import java.awt.*;

public abstract class GameObject {
    Point position;

    /**
     * @return
     */
    public Point getPosition() {
        return position;
    }

    /**
     * @param position
     */
    public void setPosition(Point position) {
        this.position = position;
    }
}
