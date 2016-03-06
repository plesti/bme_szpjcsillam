package hu.gghf.entities;

public class EmptyCell extends AbstractGameObject {
    @Override
    public boolean isStepable() {
        return childObject == null;
    }
}
