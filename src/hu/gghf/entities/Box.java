package hu.gghf.entities;

public class Box extends Location {

    @Override
    public void destroy() {
        System.out.println("Szetestem!");
    }
}