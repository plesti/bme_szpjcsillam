package hu.gghf.interfaces;

import java.awt.*;

public abstract class Moveable extends Location {
    public Point posInDirection(Direction direction, int distance) {
        Point pos = getPosition();
        int x = pos.x, y = pos.y;

        switch (direction) {
            case UP:
                y -= distance;
                break;
            case DOWN:
                y += distance;
                break;
            case LEFT:
                x -= distance;
                break;
            case RIGHT:
                x += distance;
                break;
        }
        return new Point(x, y);
    }

    public abstract void destroy();
    public abstract float getWeight();
}
