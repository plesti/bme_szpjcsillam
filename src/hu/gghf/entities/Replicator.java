package hu.gghf.entities;

import hu.gghf.interfaces.CellInterface;
import hu.gghf.interfaces.Controllable;
import hu.gghf.interfaces.Moveable;
import hu.gghf.interfaces.Shootable;
import hu.gghf.model.Application;
import hu.gghf.model.Map;

import java.awt.*;

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
}
