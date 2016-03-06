package hu.gghf;

import hu.gghf.model.Game;

public class Controller {
    private final Game game;

    public Controller(Game game) {
        this.game = game;
    }
    public void startGame() {
        this.game.loadMap(null);
    }
    public void closeGame() {
        System.exit(0);
    }
    public void movePlayerUp() {
        this.game.movePlayer(Game.DIRECTION_UP);
    }
    public void movePlayerDown() {
        this.game.movePlayer(Game.DIRECTION_DOWN);
    }
    public void movePlayerLeft() {
        this.game.movePlayer(Game.DIRECTION_LEFT);
    }
    public void movePlayerRight() {
        this.game.movePlayer(Game.DIRECTION_RIGHT);
    }
    public void pickUpBox() {
        // TODO
    }
    public void dropBox() {
        // TODO
    }
    public void shootPortalBlue() {
        game.openPortal(Game.PORTAL_BLUE);
    }
    public void shootPortalYellow() {
        game.openPortal(Game.PORTAL_YELLOW);
    }
}
