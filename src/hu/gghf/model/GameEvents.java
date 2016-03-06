package hu.gghf.model;

import hu.gghf.entities.Player;

public interface GameEvents {
    void playerDies(Player player);
    void playerWins(Player player);
    void sendError(String message);
    void mapLoaded();
}
