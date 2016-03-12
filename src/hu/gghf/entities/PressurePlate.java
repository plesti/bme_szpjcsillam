package hu.gghf.entities;

public class PressurePlate extends AbstractCell {
    private Door door;

    public PressurePlate(Door door) {
        this.door = door;
    }

    @Override
    public boolean isStepable() {
        return true;
    }

    @Override
    public void onStepIn(Moveable obj) {
        super.onStepIn(obj);
        door.setOpen(true);
    }

    @Override
    public void onStepOut() {
        super.onStepOut();
        door.setOpen(false);
    }
}
