package hu.gghf.entities;

import hu.gghf.model.Application;

public class PressurePlate implements CellInterface {
    private Door door;

    public PressurePlate(Door door) {
        Application.printCall(this, "-->PressurePlate()");
        this.door = door;
        Application.printCall(this, "<--");
    }

    @Override
    public boolean isStepable() {
        Application.printCall(this, "-->isStepable()");
        Application.printCall(this, "<--");
        return true;
    }

    @Override
    public void onStepIn(Location obj) {
        Application.printCall(this, "-->onStepIn()");
        door.setOpen(true);
        Application.printCall(this, "<--");
    }

    @Override
    public void onStepOut() {
        Application.printCall(this, "-->onStepOut()");
        door.setOpen(false);
        Application.printCall(this, "<--");
    }

    @Override
    public boolean isShootable() {
        Application.printCall(this, "-->isShootable()");
        Application.printCall(this, "<--");
        return false;
    }

    @Override
    public void shoot(Player player, Color color) {
        Application.printCall(this, "-->shoot()");
        Application.printCall(this, "<--");
    }
}
