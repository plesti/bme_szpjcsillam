package hu.gghf.entities;

public class EmptyCell implements CellInterface {
    @Override
    public boolean isStepable() { return true; }
    @Override
    public void onStepIn(Location object) { }
    @Override
    public void onStepOut() { }
    @Override
    public boolean isShootable() { return false; }

    @Override
    public void shoot(Player player, Color color) {

    }
}
