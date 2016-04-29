package hu.gghf.entities;

import hu.gghf.model.Images;
import hu.gghf.model.Map;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * Created by plesti on 2016.04.24..
 */
public class Jaffa extends Player {
    public Jaffa(Map map) {
        super(map);
    }

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
        transform.rotate(radians, Images.jaffa.getWidth()/2, Images.jaffa.getHeight()/2);
        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
        return op.filter(Images.jaffa, null);
    }
}
