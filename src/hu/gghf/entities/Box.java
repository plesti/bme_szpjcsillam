package hu.gghf.entities;

import java.awt.image.BufferedImage;

import hu.gghf.interfaces.Moveable;
import hu.gghf.interfaces.Shootable;
import hu.gghf.model.Map;
import hu.gghf.view.Images;

public class Box extends Moveable implements Shootable {
    Map map;
    public Box(Map map) {
        this.map = map;
    }

    @Override
    public boolean isShootable() {
        return true;
    }

    @Override
    public void shot(Player player, Color color) {
    }

    @Override
    public void destroy() {
        map.removeBox(this);
        System.out.println("Szetestem!");
    }

    @Override
    public float getWeight() {
        return 0.5f;
    }
    
    @Override
    public BufferedImage getImage() {
    	return Images.box;
    }
}