package hu.gghf.entities;

import hu.gghf.model.Application;
import hu.gghf.model.Game;

public class ZPM implements CellInterface {
    private boolean discovered = false;
    private Game game;

    public ZPM(Game game) {
        Application.printCall(this, "-->ZPM()");
        this.game = game;
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

        if (!discovered) {
            game.addZPM();
            discovered = true;
        }
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
