package hu.gghf.entities;

/**
 * Created by QPAKSSD on 2016.03.05..
 */
public class Door extends AbstractGameObject {
    private boolean open = false;

    @Override
    public boolean isStepable() {
        return open;
    }

    public void setOpen(boolean isopen) {
        open = isopen;
    }
}
