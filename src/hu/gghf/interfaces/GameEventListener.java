package hu.gghf.interfaces;

import hu.gghf.entities.Player;
import hu.gghf.entities.Replicator;

public interface GameEventListener {
    void printCall(String msg);
    void playerDied(Player player);
    void playerWins(Player player);
    void replicatorDied(Replicator replicator);
}
