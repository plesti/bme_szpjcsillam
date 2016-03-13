package hu.gghf.model;

import hu.gghf.entities.*;

import java.awt.*;
import java.io.IOException;

public class Controller {
    private Player player;
    private Game game;

    public Controller() throws IOException {
        game = new Game();
        player = new Player(game);
    }

    public void openPortal(CellInterface.Color type) {
        CellInterface target = player.findTarget();

        if (target != null)
            target.shoot(player, type);
    }

    public void dropBox() {
        player.setCarry(null);
    }

    public void pickUpBox() {
        Location.Direction dir = player.getDirection();
        Point frontpos = player.posInDirection(dir, 1);

        boolean isbox = game.isBox(frontpos);

        if (isbox) {
            Box box = game.getBox(frontpos);
            player.setCarry(box);
        }
    }

    public void movePlayer(Location.Direction direction) {
        Location.Direction playerDir = player.getDirection();

        if (playerDir == direction) {
            player.goForward();
        } else {
            player.turn(direction);
        }
    }

//    public void goForward() {
//        Location.Direction dir = player.getDirection();
//
//        Point frontpos = player.posInDirection(dir, 1);
//        Point currentpos = player.getPosition();
//        CellInterface frontcell = game.getMapObject(frontpos);
//        CellInterface currentcell = game.getMapObject(currentpos);
//
//        boolean isstepable = frontcell.isStepable();
//
//        if (isstepable) {
//            boolean iscarry = player.isCarry();
//
//            Box getbox = game.getBox(frontpos);
//
//            if (iscarry) {
//                Point frontpos2 = player.posInDirection(dir, 2);
//                CellInterface front2 = game.getMapObject(frontpos2);
//
//                Box getbox2 = game.getBox(frontpos2);
//
//                // Ha nincs elotte doboz es kettovel elotte ures
//                if (front2.isStepable() && getbox2 == null) {
//                    currentcell.onStepOut();
//                    frontcell.onStepOut();
//
//                    // Player elore lep
//                    player.setPosition(frontpos);
//                    frontcell.onStepIn(player);
//
//                    // Azert kell mert lehet h portal-ba lep es akkor a doboznake ele kell kerulnie
//                    Point newfront = player.posInDirection(dir, 1);
//
//                    Box box = player.getCarry();
//                    box.setPosition(newfront);
//                    front2.onStepIn(box);
//                }
//
//            } else if (!iscarry && getbox == null) {
//                currentcell.onStepOut();
//                player.setPosition(frontpos);
//                frontcell.onStepIn(player);
//            }
//        }
//    }
}
