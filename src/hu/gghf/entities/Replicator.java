package hu.gghf.entities;

import hu.gghf.interfaces.CellInterface;
import hu.gghf.interfaces.Controllable;
import hu.gghf.interfaces.Moveable;
import hu.gghf.interfaces.Shootable;
import hu.gghf.model.Map;

import java.awt.*;

public class Replicator extends Moveable implements Controllable, Shootable {
    private boolean dead = false;
    protected Map map;

    public Replicator(Map map) {
        this.map = map;
    }

    @Override
    public void destroy() {
        dead = true;
        map.setMapObject(this.position, new EmptyCell());
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
       dead = true;
    }

    @Override
    public void move(Direction dir) {
        if (this.direction == dir) {
            Point frontpos = posInDirection(this.direction, 1);
            CellInterface frontcell = map.getMapObject(frontpos);

            if (frontcell.isStepable()) {
                setPosition(frontpos);
            }
        } else {
            setDirection(dir);
        }
    }

    @Override
    public void shoot(Color type) { }
}
