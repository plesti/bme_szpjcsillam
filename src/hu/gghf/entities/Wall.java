package hu.gghf.entities;

public class Wall implements CellInterface {
    @Override
    public boolean isStepable() {
        return false;
    }

    @Override
    public void onStepIn(Location object) {

    }

    @Override
    public void onStepOut() {

    }

    @Override
    public boolean isShootable() {
        return true;
    }

    @Override
    public void shoot(Player player, Color color) {

    }
}
