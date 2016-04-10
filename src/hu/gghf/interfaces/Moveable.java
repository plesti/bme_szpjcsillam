package hu.gghf.interfaces;

import hu.gghf.model.Map;

public abstract class Moveable extends Location {
    public abstract void destroy();
    public abstract float getWeight();
}
