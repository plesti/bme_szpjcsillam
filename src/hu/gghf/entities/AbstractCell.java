package hu.gghf.entities;

public abstract class AbstractCell {
    public abstract boolean isStepable();
    public void onStepIn(Location object) {
    }
    public void onStepOut() {
    }

    public boolean isShootable() {
        return false;
    }
    public void shoot(Player player, int color) {
    }
}