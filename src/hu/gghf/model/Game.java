package hu.gghf.model;

import hu.gghf.entities.*;

import java.awt.*;
import java.util.ArrayList;


public class Game {
    public static int zpm_counter = 0;

    private Player player;
    private CellInterface[][] map;
    private ArrayList<Box> boxes;

    public void movePlayer(int direction) {
    }

    private void goForward() {
    }
    private void turn(int direction) {
    }

    public CellInterface getMapObject(Point point) { return map[point.x][point.y]; }
    public void setMapObject(Point point, CellInterface cell) { map[point.x][point.y] = cell; }

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
        CellInterface target = findTarget();
    }

    private CellInterface findTarget() {
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

    public void win() {
        System.out.println("Nyertel genyo!");
    }

    public void addZPM() {
        zpm_counter = zpm_counter + 1;
    }

    public int getZpmCount() {
        return zpm_counter;
    }
}
