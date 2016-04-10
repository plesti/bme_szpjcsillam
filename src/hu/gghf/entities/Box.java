package hu.gghf.entities;

import hu.gghf.interfaces.Moveable;
import hu.gghf.interfaces.Shootable;

public class Box extends Moveable implements Shootable {
    @Override
    public boolean isShootable() {
        return true;
    }

    @Override
    public void shot(Player player, Color color) {
    }

    @Override
    public void destroy() {
        System.out.println("Szetestem!");
    }

    @Override
    public float getWeight() {
        return 0.5f;
    }
}