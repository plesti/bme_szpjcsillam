package hu.gghf.entities;

import hu.gghf.model.Application;
import hu.gghf.model.Game;

import java.awt.*;

public class Player extends Location {
    private Box carryObject;
    private Game game;

    public Player(Game game) {
        this.game = game;
    }

    @Override
    public void destroy() {
        Application.printCall(this, "destroy()");
    }

    public void setCarry(Box box) {
        Application.printCall(this, "setCarry()");

        carryObject = box;
    }
    public Box getCarry() { return carryObject; }
    public boolean isCarry() { return carryObject != null; }

    public Point posInDirection(Direction direction, int distance) {
        Application.printCall(this, "posInDirection()");

        Point pos = getPosition();
        int x = pos.x, y = pos.y;

        switch (direction) {
            case UP:
                y -= distance;
                break;
            case DOWN:
                y += distance;
                break;
            case LEFT:
                x -= distance;
                break;
            case RIGHT:
                x += distance;
                break;
        }
        return new Point(x, y);
    }

    public CellInterface findTarget() {
        Application.printCall(this, "findTarget()");

        Direction dir = getDirection();

        CellInterface target;

        // 8 egyseg tavolra lehet loni
        for (int i = 1; i < 8; i++) {
            Point pos = posInDirection(dir, i);
            target = game.getMapObject(pos);

            boolean isshootable = target.isShootable();
            if (isshootable) {
                return target;
            }
        }
        return null;
    }

    public void goForward() {
        Application.printCall(this, "goForward()");


        Location.Direction dir = getDirection();

        Point frontpos = posInDirection(dir, 1);
        Point currentpos = getPosition();
        CellInterface frontcell = game.getMapObject(frontpos);
        CellInterface currentcell = game.getMapObject(currentpos);

        boolean isstepable = frontcell.isStepable();

        if (isstepable) {
            boolean iscarry = isCarry();

            Box getbox = game.getBox(frontpos);

            if (iscarry) {
                Point frontpos2 = posInDirection(dir, 2);
                CellInterface frontcell2 = game.getMapObject(frontpos2);

                Box getbox2 = game.getBox(frontpos2);

                // Ha nincs elotte doboz es kettovel elotte ures
                if (frontcell2.isStepable() && getbox2 == null) {
                    currentcell.onStepOut();
                    frontcell.onStepOut();

                    // Player elore lep
                    setPosition(frontpos);
                    frontcell.onStepIn(this);

                    // Azert kell mert lehet h portal-ba lep es akkor a doboznake ele kell kerulnie
                    Point newfront = posInDirection(dir, 1);

                    Box box = getCarry();
                    box.setPosition(newfront);
                    frontcell2.onStepIn(box);
                }

            } else if (!iscarry && getbox == null) {
                currentcell.onStepOut();
                setPosition(frontpos);
                frontcell.onStepIn(this);
            }
        }
    }

    public void turn(Direction newdir) {
        Application.printCall(this, "turn()");

        boolean iscarry = isCarry();

        if (iscarry) {
            Direction dir = getDirection();

            Point newpos = posInDirection(newdir, 1);
            CellInterface newcell = game.getMapObject(newpos);

            Box box = getCarry();
            Point boxpos = box.getPosition();
            CellInterface boxcell = game.getMapObject(boxpos);

            boolean isbox = game.isBox(newpos);
            boolean isstepable = newcell.isStepable();

            if (!isbox && isstepable) {
                boxcell.onStepOut();
                setDirection(newdir);
                box.setPosition(newpos);
                newcell.onStepIn(box);
            }

        } else {
            setDirection(newdir);
        }
    }
}
