package hu.gghf.entities;

import hu.gghf.model.Application;
import hu.gghf.model.Game;

public class ShieldGenerator implements CellInterface {
    Game game;

    public ShieldGenerator(Game game) {
        Application.printCall(this, "-->ShieldGenerator()");
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
    public void onStepIn(Location object) {
        Application.printCall(this, "-->onStepIn()");

        if (game.getZpmCount() == 4) {
            System.out.println("Nyertel!");
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
        return true;
    }

    @Override
    public void shoot(Player player, Color color) {
        Application.printCall(this, "-->shoot()");
        Application.printCall(this, "<--");
    }
}
