package hu.gghf.entities;

public class Door extends AbstractCell {
    private boolean open = false;

    @Override
    public boolean isStepable() {
        return open;
    }

    public void setOpen(boolean isopen) {
        open = isopen;
    }

    @Override
    public boolean isShootable() {
        return !open;
    }
}
