package hu.gghf.model;

import hu.gghf.entities.*;

import java.awt.*;
import java.io.IOException;

public class Controller {
    private Player player;
    private Game game;

    public Controller() {
        Application.printCall(this, "-->Controller()");

        try {
            game = new Game();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player = new Player(game);
        player.setPosition(new Point(2,2));

        Application.printCall(this, "<--");
    }

    public void printmap() {
        Point playerpos = player.getPosition();

        for (int i = 0; i < game.maxsize; i++) {
            String row = "";
            Point point;
            for (int k = 0; k < game.maxsize; k++) {
                point = new Point(k, i);
                CellInterface obj = game.getMapObject(point);

                if (playerpos.equals(point)) {
                    switch (player.getDirection()) {
                        case UP:
                            row += "^";
                            break;
                        case DOWN:
                            row += "V";
                            break;
                        case LEFT:
                            row += "<";
                            break;
                        case RIGHT:
                            row += ">";
                            break;
                        default:
                            row += "_";
                            break;
                    }
                }
                else if (game.isBox(point)) {
                    row += "b";
                }
                else if (obj.getClass() == Wall.class) {
                    row += "x";
                }
                else if (obj.getClass() == EmptyCell.class) {
                    row += "0";
                }
                else if (obj.getClass() == PortalWall.class) {
                    row += "p";
                }
                else {
                    row += "u";
                }
            }
            System.out.println(row);
        }
    }

    public void openPortal(CellInterface.Color type) {
        Application.printCall(this, String.format("-->openportal(%s)", type));

        CellInterface target = player.findTarget();

        if (target != null)
            target.shoot(player, type);

        Application.printCall(this, "<--");
    }

    public void dropBox() {
        Application.printCall(this, "-->dropBox()");

        System.out.println();

        player.setCarry(null);
        Application.printCall(this, "<--");

    }

    public void pickUpBox() {
        Application.printCall(this, "-->pickUpBox()");

        Location.Direction dir = player.getDirection();
        Point frontpos = player.posInDirection(dir, 1);

        boolean isbox = game.isBox(frontpos);

        if (isbox) {
            Box box = game.getBox(frontpos);
            player.setCarry(box);
        }
        Application.printCall(this, "<--");
    }

    public void movePlayer(Location.Direction direction) {
        Application.printCall(this, String.format("-->movePlayer(%s)", direction));

        Location.Direction playerDir = player.getDirection();

        if (playerDir == direction) {
            player.goForward();
        } else {
            player.turn(direction);
        }
        Application.printCall(this, "<--");
    }
}
