package hu.gghf.entities;

import hu.gghf.model.Game;
public class ShieldGenerator implements CellInterface {
    Game game;

    public ShieldGenerator(Game game) {
        this.game = game;
    }

    @Override
    public boolean isStepable() {
        return false;
    }

    @Override
    public void onStepIn(Location object) {
        if (game.getZpmCount() == 4) {
            game.win();
        }
    }

    @Override
    public void onStepOut() {
    }

    @Override
    public boolean isShootable() {
        return true;
    }

    @Override
    public void shoot(Player player, Color color) {
    }
}
