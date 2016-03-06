package hu.gghf.entities;

public class PressurePlate extends AbstractGameObject {
    Door door;

    public PressurePlate(Door door) {
        this.door = door;
    }

    @Override
    public boolean isStepable() {
        return childObject != null;
    }

    @Override
    public void stepOn(AbstractGameObject object) {
        super.stepOn(object);
        door.setOpen(true);
    }

    @Override
    public void stepOut() {
        super.stepOut();
        door.setOpen(false);
    }
}
