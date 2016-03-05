package hu.gghf.model;

public interface GameEvents {
    void playerDies(Player player);
    void playerWins(Player player);
    void sendError(String message);
    void mapLoaded();
}
