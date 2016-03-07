package hu.gghf.entities;

public class ZPM extends AbstractGameObject {

    @Override
    public boolean isStepable() {
        return true;
    }

    @Override
    public void stepOn(AbstractGameObject object) {
        super.stepOn(object);
    }

    @Override
    public void stepOut() {
        super.stepOut();
    }
}
