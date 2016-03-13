package hu.gghf.entities;

public interface CellInterface {
    /**
     * Azért van itt a Color enum, mert a shoot metódus második paramétere.
     */
    enum Color { BLUE, YELLOW }

    boolean isStepable();
    void onStepIn(Location object);
    void onStepOut();
    boolean isShootable();
    void shoot(Player player, Color color);
}