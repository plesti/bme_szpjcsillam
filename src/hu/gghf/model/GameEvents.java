package hu.gghf.model;

public interface GameEvents {
    void playerDies();
    void playerWins();
    void sendError(String message);
}
