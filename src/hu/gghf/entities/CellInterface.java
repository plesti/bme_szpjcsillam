package hu.gghf.entities;

public interface CellInterface {
    /**
     * Az�rt van itt a Color enum, mert a shoot met�dus m�sodik param�tere.
     */
    enum Color { BLUE, YELLOW }

    boolean isStepable();
    void onStepIn(Location object);
    void onStepOut();
    boolean isShootable();
    void shoot(Player player, Color color);
}