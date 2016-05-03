package hu.gghf.entities;

import hu.gghf.interfaces.*;
import hu.gghf.model.Application;
import hu.gghf.model.Map;
import hu.gghf.view.Images;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Replicator extends Moveable implements Controllable, Shootable {
    protected Map map;

    public Replicator(Map map) {
        this.map = map;
    }

    @Override
    public void destroy() {
        map.setReplicator(null);
        map.setMapObject(this.position, new EmptyCell());
        Application.printCall(this, "Replikator meghalt a szakadekban!");
    }

    @Override
    public float getWeight() {
        return 0.0f;
    }

    @Override
    public boolean isShootable() {
        return true;
    }

    @Override
    public void shot(Player player, Color color) {
        map.setReplicator(null);
        Application.printCall(this, "Replikator meghalt!");
    }

    @Override
    public void move(Direction dir) {
        if (this.direction == dir) {
            Point frontpos = posInDirection(this.direction, 1);
            CellInterface frontcell = map.getMapObject(frontpos);

            if (frontcell.isStepable()) {
                setPosition(frontpos);

                if (frontcell.getClass() == Hole.class) {
                    frontcell.onStepIn(this);
                }
            }
        } else {
            setDirection(dir);
        }
    }

    @Override
    public void shoot(Color type) { }

    @Override
    public BufferedImage getImage() {
        double radians = 0.0;

        switch (getDirection()) {
            case UP:
                radians = 0.0;
                break;
            case LEFT:
                radians = -Math.PI/2;
                break;
            case RIGHT:
                radians = Math.PI/2;
                break;
            case DOWN:
                radians = Math.PI;
                break;
        }
        AffineTransform transform = new AffineTransform();
        transform.rotate(radians, Images.replicator.getWidth()/2, Images.replicator.getHeight()/2);
        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
        return op.filter(Images.replicator, null);
    }
}
