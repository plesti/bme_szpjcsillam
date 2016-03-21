package hu.gghf.entities;

import hu.gghf.model.Application;

public class Box extends Location {

    @Override
    public void destroy() {
//        System.out.println("Szetestem!");
        Application.printCall(this, "-->destroy()");
        Application.printCall(this, "<--");
    }
}