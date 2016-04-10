package hu.gghf.interfaces;

public interface CellInterface extends Shootable {
    /**
     * Azért van itt a Color enum, mert a shot metódus második paramétere.
     */
    boolean isStepable();
    void onStepIn(Moveable object);
    void onStepOut();
}