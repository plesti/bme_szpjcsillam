package hu.gghf.model;

import hu.gghf.Application;

import java.util.ArrayList;

public class Game {
    public static int DIRECTION_UP = 0;
    public static int DIRECTION_DOWN = 1;
    public static int DIRECTION_LEFT = 2;
    public static int DIRECTION_RIGHT = 3;
    public static int PORTAL_BLUE = 0;
    public static int PORTAL_YELLOW = 1;

    Player player;
    ArrayList<GameObject> gameObjects;
    GameEvents events;

    public Game(Application app) {

    }

    public void loadMap(String filepath) {

    }

    public void movePlayer() {

    }
    public void pickUpBox() {

    }
    public void dropBox() {

    }
    public void openPortal(int type) {

    }
}
