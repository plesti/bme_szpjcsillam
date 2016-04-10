package hu.gghf.interfaces;

public interface CellInterface extends Shootable {
    /**
     * Az�rt van itt a Color enum, mert a shot met�dus m�sodik param�tere.
     */
    boolean isStepable();
    void onStepIn(Moveable object);
    void onStepOut();
}