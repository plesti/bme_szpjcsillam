package hu.gghf;

import hu.gghf.model.Game;
import hu.gghf.model.GameEvents;
import hu.gghf.model.Player;

public class Application implements GameEvents {
    private static Game game;
    private static Controller controller;

    public static void main(String[] args) {
	    // write your code here
        Application app = new Application();

        game = new Game(app);
        controller = new Controller(game);

    }

    @Override
    public void playerDies(Player player) {

    }

    @Override
    public void playerWins(Player player) {

    }

    @Override
    public void sendError(String message) {

    }

    @Override
    public void mapLoaded() {

    }
}
