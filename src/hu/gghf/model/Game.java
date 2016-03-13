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
    private int maxsize;

    public Game() throws IOException {
        BufferedReader nf = new BufferedReader(new FileReader("katyvasz.txt"));
        map = new CellInterface[maxsize][maxsize];
        this.boxes = new ArrayList<Box>();
        int y = 0;
        String line;
        while ((line = nf.readLine()) != null) {
        	String[] params = line.split(";");
        	int paramslength = params.length;
        	for (int i = 0; i<paramslength; i++) {
        		char c = params[i-1].charAt(0);
        		switch (c) {
        		case '0': 
        			this.setMapObject(new Point(y,i), new EmptyCell());
        			break;
        		case '1':
        			this.addBox(new Box());
        			break;
        		default:
        			break;
        		}
        	}
        	
        }
        
    }

    public CellInterface getMapObject(Point point) { return map[point.x][point.y]; }
    public void setMapObject(Point point, CellInterface cell) { map[point.x][point.y] = cell; }

    public boolean isBox(Point point) {
        return false;
    }
    public Box getBox(Point point) {
        return null;
    }
    public void addBox(Box box) {
        boxes.add(box);
    }

    public void addZPM() {
        zpm_counter += 1;
    }

    public int getZpmCount() {
        return zpm_counter;
    }
}
