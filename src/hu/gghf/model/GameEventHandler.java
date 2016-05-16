package hu.gghf.model;

import hu.gghf.entities.Player;
import hu.gghf.entities.Replicator;
import hu.gghf.interfaces.GameEventListener;

/**
 * Created by Peter on 2016.05.16..
 */
public class GameEventHandler {
    private static GameEventListener handler;

    public static void setHandler(GameEventListener handler) {
        GameEventHandler.handler = handler;
    }

    public static void printCall(String msg) {
        if (handler != null)
            handler.printCall(msg);
    }

    public static void playerDied(Player player) {
        if (handler != null)
            handler.playerDied(player);
    }

    public static void playerWins(Player player) {
        if (handler != null)
            handler.playerWins(player);
    }

    public static void replicatorDied(Replicator replicator) {
        if (handler != null)
            handler.replicatorDied(replicator);
    }
}
