package hu.gghf.entities;

public class PortalWall extends AbstractGameObject {
    public static PortalWall blue = null;
    public static PortalWall yellow = null;
    private boolean open = false;

    @Override
    public boolean isStepable() {
        return blue != null && yellow != null && open;
    }

    public void setOpen(boolean isopen) {
        open = isopen;
    }
}