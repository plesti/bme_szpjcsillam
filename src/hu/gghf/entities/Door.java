package hu.gghf.entities;

public class Door implements CellInterface {
    private boolean open = false;

    public void setOpen(boolean isopen) { open = isopen; }

    @Override
    public boolean isStepable() {
        return open;
    }

    @Override
    public void onStepIn(Location object) { }

    @Override
    public void onStepOut() { }

    @Override
    public boolean isShootable() { return !open; }

    @Override
    public void shoot(Player player, Color color) {
    }
}
