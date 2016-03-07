package hu.gghf.entities;

public class Wall extends AbstractCell {
    @Override
    public boolean isStepable() {
        return false;
    }
}
