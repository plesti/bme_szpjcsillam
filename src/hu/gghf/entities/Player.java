package hu.gghf.entities;

public class Player extends Location {
    private Box carryObject;
    private int zpmCounter;

    public void setCarry(Box box) {
        carryObject = box;
    }
    public Box getCarry() { return carryObject; }
    public boolean isCarry() { return carryObject != null; }

    public void addZPM() {
        zpmCounter += 1;
    }
}
