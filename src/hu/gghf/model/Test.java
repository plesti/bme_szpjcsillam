package hu.gghf.model;

import hu.gghf.entities.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {
    public static Wall wall;
    public static EmptyCell empty;
    public static Hole hole;
    public static PressurePlate pressurePlate;
    public static Door door;

    public static CellInterface selected = null;

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static Box carryBox;
    public static boolean iscarry = false;
    public static Location.Direction playerdir;

    public static void init() {
        wall = new Wall();
        empty = new EmptyCell();
        hole = new Hole();
        pressurePlate = new PressurePlate(door);
        door = new Door();

        carryBox = new Box();
        carryBox.setPosition(new Point(1,1));
        playerdir = null;
    }

    public static void setSelected() {
        System.out.println("(1 - Ures mezo, 2 - Fal, 3 - Nyomolap, 4 - Szakadék)");
        try {
            String c = br.readLine();
            if (c.equals("1")) {
                Test.selected = Test.empty;
            }
            else if (c.equals("2")) {
                Test.selected = Test.wall;
            }
            else if (c.equals("3")) {
                Test.selected = Test.pressurePlate;
            }
            else if (c.equals("4")) {
                Test.selected = Test.hole;
            }
            else {
                setSelected();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setPlayerDir() {
        System.out.println("?Merre fele nez a jatekos?");
        System.out.println("(1 - Fel, 2 - Le, 3 - Balra, 4 - Jobbra)");
        try {
            String c = br.readLine();
            if (c.equals("1")) {
                Test.playerdir = Location.Direction.UP;
            }
            else if (c.equals("2")) {
                Test.playerdir = Location.Direction.DOWN;
            }
            else if (c.equals("3")) {
                Test.playerdir = Location.Direction.LEFT;
            }
            else if (c.equals("4")) {
                Test.playerdir = Location.Direction.RIGHT;
            }
            else {
                setPlayerDir();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setIscarry() {
        System.out.println("?Visz a jatekos dobozt? i/n");
        try {
            iscarry = (Test.br.readLine().equals("i"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
