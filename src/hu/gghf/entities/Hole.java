package hu.gghf.entities;

public class Hole implements CellInterface {
    @Override
    public boolean isStepable() {
        return true;
    }

    @Override
    public void onStepIn(Location object) {
        object.destroy();
    }
    @Override
    public void onStepOut() {
    }

    @Override
    public boolean isShootable() {
        return false;
    }

    @Override
    public void shoot(Player player, Color color) {

    }
}
