package hu.gghf.model;

import hu.gghf.entities.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class Game {
    private static int zpm_counter = 0;
    private CellInterface[][] map;
    private ArrayList<Box> boxes;
    // Ez majd a terkep merete
    public int maxsize = 6;

    public Game() throws IOException {
        Application.printCall(this, "Game()");

        map = new CellInterface[maxsize][maxsize];
        boxes = new ArrayList<Box>();

        for (int i = 0; i < maxsize; i++) {
            for (int k = 0; k < maxsize; k++) {
                if (k == (maxsize-1) || k == 0 || i == (maxsize-1) || i == 0) {
                    map[i][k] = new Wall();
                } else {
                    map[i][k] = new EmptyCell();
                }
            }
        }

        Point pw1 = new Point(1, 3);
        Point pw3 = new Point(4, 4);
        setMapObject(pw1, new PortalWall(pw1));
        setMapObject(pw3, new PortalWall(pw3));

        Point pb1 = new Point(4, 3);
        Box box = new Box();
        box.setPosition(pb1);
        addBox(box);

//        BufferedReader nf = new BufferedReader(new FileReader("katyvasz.txt"));
//        map = new CellInterface[maxsize][maxsize];
//        this.boxes = new ArrayList<Box>();
//        int y = 0;
//        String line;
//        while ((line = nf.readLine()) != null) {
//        	String[] params = line.split(";");
//        	int paramslength = params.length;
//        	for (int i = 0; i<paramslength; i++) {
//        		char c = params[i].charAt(0);
//        		switch (c) {
//        		case '0':
//        			this.setMapObject(new Point(y,i), new EmptyCell());
//        			break;
//        		case '1':
//        			this.addBox(new Box());
//        			break;
//        		default:
//        			break;
//        		}
//        	}
//        }
    }

    public CellInterface getMapObject(Point point) {
        Application.printCall(this, "getMapObject()");

        return map[point.y][point.x];
    }
    public void setMapObject(Point point, CellInterface cell) {
        Application.printCall(this, "setMapObject()");

        map[point.y][point.x] = cell;
    }

    public boolean isBox(Point point) {
        Application.printCall(this, "isBox()");

        for (Box b : boxes) {
            if (b.getPosition().equals(point))
                return true;
        }
        return false;
    }
    public Box getBox(Point point) {
        Application.printCall(this, "getBox()");

        for (Box b : boxes) {
            if (b.getPosition().equals(point))
                return b;
        }
        return null;
    }
    public void addBox(Box box) {
        Application.printCall(this, "addBox()");

        boxes.add(box);
    }

    public void addZPM() {
        Application.printCall(this, "addZPM()");

        zpm_counter += 1;
    }

    public int getZpmCount() {
        Application.printCall(this, "getZpmCount()");

        return zpm_counter;
    }
}
