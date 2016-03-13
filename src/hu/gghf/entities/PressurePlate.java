package hu.gghf.entities;

public class PressurePlate implements CellInterface {
    private Door door;

    public PressurePlate(Door door) {
        this.door = door;
    }

    @Override
    public boolean isStepable() {
        return true;
    }

    @Override
    public void onStepIn(Location obj) {
        door.setOpen(true);
    }

    @Override
    public void onStepOut() {
        door.setOpen(false);
    }

    @Override
    public boolean isShootable() { return false; }
    @Override
    public void shoot(Player player, Color color) { }
}
