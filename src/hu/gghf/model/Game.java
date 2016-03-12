package hu.gghf.model;

import hu.gghf.entities.*;

import java.awt.*;
import java.util.ArrayList;


public class Game {
    public static final int DIRECTION_UP = 0;
    public static final int DIRECTION_DOWN = 1;
    public static final int DIRECTION_LEFT = 2;
    public static final int DIRECTION_RIGHT = 3;
    public static final int PORTAL_BLUE = 0;
    public static final int PORTAL_YELLOW = 1;

    private Player player;
    private AbstractCell[][] map;
    private ArrayList<Box> boxes;

    public void movePlayer(int direction) {
    }

    private void goForward() {
    }
    private void turn(int direction) {
    }

    public AbstractCell getMapObject(Point point) { return map[point.x][point.y]; }
    public void setMapObject(Point point, AbstractCell cell) { map[point.x][point.y] = cell; }

    public boolean isBox(Point point) {
        return false;
    }
    public Box getBox(Point point) {
        return null;
    }
    public void setBox(Point point) {
    }

    private Point posInDirection(int direction, int distance) {
//        switch (dir) {
//            case DIRECTION_UP:
//                return new Point(0, 1);
//            case DIRECTION_DOWN:
//                return new Point(0, -1);
//            case DIRECTION_LEFT:
//                return new Point(-1, 0);
//            case DIRECTION_RIGHT:
//                return new Point(1, 0);
//        }
        return null;
    }

    public void openPortal(int type) {
        AbstractCell target = findTarget();
    }

    private AbstractCell findTarget() {
        return null;
    }

    public void pickUpBox() {

    }
    public void dropBox() {
        player.setCarry(null);
    }

    public int invertDirection(int direction) {
        return 0;
    }

}
