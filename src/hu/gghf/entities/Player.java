package hu.gghf.entities;

import hu.gghf.interfaces.*;
import hu.gghf.model.Application;
import hu.gghf.model.Images;
import hu.gghf.model.Map;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Player extends Moveable implements Controllable, Graphic {
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
        Application.printCall(this, "Jatekos meghalt!");
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

    private Shootable findTarget() {
        Shootable target = null;

        for (int i = 1; i < 8; i++) {
            Point loc = posInDirection(this.direction, i);
            Replicator replicator = map.getReplicator();

            if (replicator != null && replicator.getPosition().equals(loc)) {
                target = map.getReplicator();
            } else {
                target = map.getMapObject(loc);
            }

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
                    currentcell.onStepOut(this);
                    frontcell.onStepOut(this);

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
                currentcell.onStepOut(this);
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

                boxcell.onStepOut(this);
                setDirection(newdir);
                box.setPosition(newpos);
                newcell.onStepIn(box);
            }
        } else {
            setDirection(newdir);
        }
    }

    @Override
    public BufferedImage getImage() {
        double radians = 0.0;

        switch (getDirection()) {
            case UP:
                radians = 0.0;
                break;
            case LEFT:
//                radians = -2*Math.PI/4;
                radians = -Math.PI/2;
                break;
            case RIGHT:
//                radians = 2*Math.PI/4;
                radians = Math.PI/2;
                break;
            case DOWN:
//                radians = 2*Math.PI/2;
                radians = Math.PI;
                break;
        }
        AffineTransform transform = new AffineTransform();
        transform.rotate(radians, Images.oneil.getWidth()/2, Images.oneil.getHeight()/2);
        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
        return op.filter(Images.oneil, null);
    }
}
