package hu.gghf.model;

import hu.gghf.entities.*;

import java.awt.*;


public class Game {
    public static final int DIRECTION_UP = 0;
    public static final int DIRECTION_DOWN = 1;
    public static final int DIRECTION_LEFT = 2;
    public static final int DIRECTION_RIGHT = 3;
    public static final int PORTAL_BLUE = 0;
    public static final int PORTAL_YELLOW = 1;
    private final Point start = new Point(10, 10);
    private Player player;
    private AbstractGameObject[][] map;
    private GameEvents events;

    public Game(GameEvents app) {
        events = app;
    }

    public void loadMap(String filepath) {
        player = new Player(DIRECTION_UP ,start);

        // TODO: forloop fajlban

        events.mapLoaded();
    }

    public void movePlayer(int dir) {
        Point addvector = directionToVector(dir);
        Point playerPos = player.getPosition();

        Point newPos = new Point(playerPos.x + addvector.x, playerPos.y + addvector.y);
        AbstractGameObject targetObject = map[newPos.x][newPos.y];

        if (!map[newPos.x][newPos.y].isStepable())
            return;

        if (targetObject.getClass() == Hole.class) {
            events.playerDies(player);
            return;
        }

        if (player.isCarry()) {
            AbstractGameObject carryObject = player.getBindObject();
            Point carryNewPos = new Point(playerPos.x + 2*addvector.x, playerPos.y + 2*addvector.y);

            if (!moveObject(carryObject, carryNewPos)) {
                return;
            }
        }
        player.setPosition(newPos);
    }

    private boolean moveObject(AbstractGameObject object, Point newpoint) {
        AbstractGameObject placeTo = map[newpoint.x][newpoint.y];
        if (placeTo != null && placeTo.isStepable()) {
            if (placeTo.getClass() == PortalWall.class) {
                if (PortalWall.blue == placeTo) {
                    newpoint = PortalWall.yellow.getPosition();
                } else {
                    newpoint = PortalWall.blue.getPosition();
                }
            }
            map[object.getPosition().x][object.getPosition().y].setChildObject(null);
            map[newpoint.x][newpoint.y].setChildObject(object);

            return true;
        } else {
            return false;
        }
    }

    public void bindBox(Box box) {
        player.setBindObject(box);
    }

    public void openPortal(int type) {
        AbstractGameObject target = findTarget();
    }

    private Point directionToVector(int dir) {
        switch (dir) {
            case DIRECTION_UP:
                return new Point(0, 1);
            case DIRECTION_DOWN:
                return new Point(0, -1);
            case DIRECTION_LEFT:
                return new Point(-1, 0);
            case DIRECTION_RIGHT:
                return new Point(1, 0);
        }
        return null;
    }

    /** Megkeresi a szemkozti falat
     * @return
     */
    private AbstractGameObject findTarget() {
        // TODO: player directiont kell lekerdezni es a mapot bejarni elorefele addig, amig nem talal isStepable-t, vagy
        // kier a palyarol

        return null;
    }
}
