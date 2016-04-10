package hu.gghf.interfaces;

/**
 * Created by QPAKSSD on 2016.04.10..
 */
public interface Controllable {
    void move(Location.Direction direction);
    void shoot(Shootable.Color type);
}