package hu.gghf.entities;

/**
 * Created by QPAKSSD on 2016.03.06..
 */
public class Wall extends AbstractGameObject {
    @Override
    public boolean isStepable() {
        return false;
    }
}
