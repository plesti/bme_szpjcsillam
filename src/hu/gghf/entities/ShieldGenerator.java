package hu.gghf.entities;

public class ShieldGenerator extends AbstractCell {
    @Override
    public boolean isStepable() {
        return false;
    }

    @Override
    public boolean isShootable() {
        return true;
    }
}
