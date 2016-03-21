package hu.gghf.entities;

import hu.gghf.model.Application;
import hu.gghf.model.Test;

import java.awt.*;
import java.io.IOException;

public abstract class Location {
    public enum Direction { UP, DOWN, LEFT, RIGHT }

    private Point position;
    private Direction direction;

    public Location() {
        Application.printCall(this, "-->Location()");

        position = new Point(0, 0);
        direction = Direction.UP;
        Application.printCall(this, "<--");
    }

    public Point getPosition() {
        Application.printCall(this, "-->getPosition()");

        Application.printCall(this, "<--");
        return position;
    }
    public void setPosition(Point position) {
        Application.printCall(this, "-->setPosition()");
        this.position = position;
        Application.printCall(this, "<--");
    }

    public Direction getDirection() {
        Application.printCall(this, "-->getDirection()");
//        return direction;

        // TODO: TOROLNI
        if (Test.playerdir == null)
            Test.setPlayerDir();
        Application.printCall(this, "<--");
        return Test.playerdir;
    }
    public void setDirection(Direction direction) {
        Application.printCall(this, "-->setDirection()");

        this.direction = direction;
        Application.printCall(this, "<--");
    }

    /**
     * Ez hivodik meg ha szakadekba lep.
     */
    public abstract void destroy();
}
