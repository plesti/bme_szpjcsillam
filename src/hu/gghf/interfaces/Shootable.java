package hu.gghf.interfaces;

import hu.gghf.entities.Player;

public interface Shootable {
    enum Color { BLUE, YELLOW, RED, GREEN }

    boolean isShootable();
    void shot(Player player, Color color);
}
