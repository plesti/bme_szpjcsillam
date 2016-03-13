package hu.gghf.entities;

import hu.gghf.model.Game;

public class ZPM implements CellInterface {
    private boolean discovered = false;
    private Game game;

    public ZPM(Game game) {
        this.game = game;
    }

    @Override
    public boolean isStepable() {
        return true;
    }

    @Override
    public void onStepIn(Location obj) {
        if (!discovered) {
            game.addZPM();
            discovered = true;
        }
    }

    @Override
    public void onStepOut() {
    }

    @Override
    public boolean isShootable() {
        return false;
    }

    @Override
    public void shoot(Player player, Color color) {

    }
}
