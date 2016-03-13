package hu.gghf.entities;

public class Player extends Location {
    private Box carryObject;

    public void setCarry(Box box) {
        carryObject = box;
    }
    public Box getCarry() { return carryObject; }
    public boolean isCarry() { return carryObject != null; }

    @Override
    public void destroy() {
        System.out.println("Megoltel te csicska!!!!!");
    }
}
