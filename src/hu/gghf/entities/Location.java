package hu.gghf.entities;

import hu.gghf.model.Application;

import java.awt.*;
import java.io.IOException;

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
//        return direction;

        try {
            System.out.println("Melyik iranyba nez a jatekos? (1 - fel, 2 - le, 3 - jobbra, 4 - balra)");
            int response = System.in.read();

            switch (response) {
                case 49:
                    return Direction.UP;
                case 50:
                    return Direction.DOWN;
                case 51:
                    return Direction.RIGHT;
                case 52:
                    return Direction.LEFT;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Direction.UP;
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
