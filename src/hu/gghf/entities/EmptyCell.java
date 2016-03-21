package hu.gghf.entities;

import hu.gghf.model.Application;

public class EmptyCell implements CellInterface {
    @Override
    public boolean isStepable() {
        Application.printCall(this, "-->isStepable()");

        Application.printCall(this, "<--");
        return true;
    }

    @Override
    public void onStepIn(Location object) {
        Application.printCall(this, "-->onStepIn()");

        Application.printCall(this, "<--");
    }

    @Override
    public void onStepOut() {
        Application.printCall(this, "-->onStepOut()");

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
