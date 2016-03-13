package hu.gghf.model;

import hu.gghf.entities.*;

import java.awt.*;
import java.util.ArrayList;


public class Game {
    private static int zpm_counter = 0;
    private CellInterface[][] map;
    private ArrayList<Box> boxes;
    // Ez majd a terkep merete
    private int maxsize;

    public Game() {
        this.boxes = new ArrayList<Box>();
        // TODO Zsolti
    }

    public CellInterface getMapObject(Point point) { return map[point.x][point.y]; }
    public void setMapObject(Point point, CellInterface cell) { map[point.x][point.y] = cell; }

    public boolean isBox(Point point) {
        return false;
    }
    public Box getBox(Point point) {
        return null;
    }
    public void setBox(Box box) {
        boxes.add(box);
    }

    public void addZPM() {
        zpm_counter += 1;
    }

    public int getZpmCount() {
        return zpm_counter;
    }
}
