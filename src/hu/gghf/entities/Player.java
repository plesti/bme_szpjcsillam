package hu.gghf.entities;

import hu.gghf.interfaces.CellInterface;
import hu.gghf.interfaces.Controllable;
import hu.gghf.interfaces.Moveable;
import hu.gghf.interfaces.Shootable;
import hu.gghf.model.Application;
import hu.gghf.model.Map;

import java.awt.*;

public class Player extends Moveable implements Controllable {
    private Box carryObject;
    protected Map map;

    public Player(Map map) {
        this.map = map;
    }

    @Override
    public void move(Direction direction) {
        if (this.direction == direction) {
            goForward();
        } else {
            turn(direction);
        }
    }

    @Override
    public void shoot(CellInterface.Color type) {
        Shootable target = findTarget();

        if (target != null)
            target.shot(this, type);
    }

    @Override
    public void destroy() {
        // TODO
        Application.printCall(this, "Meghaltam");
    }

    @Override
    public float getWeight() {
        return 1.0f;
    }

    public void pickUpBox() {
        Point frontpos = posInDirection(this.direction, 1);

        Box box = map.getBox(frontpos);
        if (box != null) {
            setCarry(box);
        }
    }

    public void dropBox() {
        setCarry(null);
    }

    public void setCarry(Box box) {
        carryObject = box;
    }
    public Box getCarry() { return carryObject; }

    public Point posInDirection(Direction direction, int distance) {
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

    private Shootable findTarget() {
        Shootable target = null;

        for (int i = 1; i < 8; i++) {
            target = map.getMapObject(posInDirection(this.direction, i));

            if (target.isShootable()) {
                return target;
            }
        }
        return null;
    }

    private void goForward() {
        Point frontpos = posInDirection(this.direction, 1);
        CellInterface frontcell = map.getMapObject(frontpos);
        CellInterface currentcell = map.getMapObject(this.position);

        if (frontcell.isStepable()) {
            Box getbox = map.getBox(frontpos);

            if (carryObject != null) {
                Point frontpos2 = posInDirection(this.direction, 2);
                CellInterface frontcell2 = map.getMapObject(frontpos2);

                if (frontcell2.isStepable()) {
                    currentcell.onStepOut();
                    frontcell.onStepOut();

                    // Player elore lep
                    setPosition(frontpos);
                    frontcell.onStepIn(this);

                    // Azert kell mert lehet h portal-ba lep es akkor a doboznake ele kell kerulnie
                    Point newfront = posInDirection(this.direction, 1);

                    Box box = getCarry();
                    box.setPosition(newfront);
                    frontcell2.onStepIn(box);
                }

            } else if (getbox == null) {
                currentcell.onStepOut();
                setPosition(frontpos);
                frontcell.onStepIn(this);
            }
        }
    }

    private void turn(Direction newdir) {
        if (carryObject != null) {
            Point newpos = posInDirection(newdir, 1);
            CellInterface newcell = map.getMapObject(newpos);

            if (newcell.isStepable()) {
                Box box = getCarry();
                Point boxpos = box.getPosition();
                CellInterface boxcell = map.getMapObject(boxpos);

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
