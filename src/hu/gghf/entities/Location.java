package hu.gghf.entities;

import hu.gghf.model.Application;
import hu.gghf.model.Test;

import java.awt.*;
import java.io.IOException;
/*  +abstract class Location
 *  Absztrakt osztály mozgatható pályaelemek alaptulajdonságainak és
 *  alapfüggvényeinek csoportba foglalására. Az különbözteti meg a rögzített
 *  pályaelemtõl, hogy mozgatható, ebbõl kifolyólag rendelkezik iránnyal.
 *  Rendelkezik getter és setter függvényekkel, továbbá egy eseménykezelõvel
 *  is megsemmisülés esetére, mely utóbbit szigorúan a belõle leszármazó
 *  osztályok valósítanak meg.
 *  Tipikus Location elem a játékoskarakter és a doboz.
 */
public abstract class Location {
	/*	Attribútumlista:
	 * 	+Direction: felsorolt típus az irány beállítására.
	 * 	-position: a mozdítható pályaelem helyzete, koordinátapárban.
	 * 	-direction: a Direction egy példánya.
	 */
    public enum Direction { UP, DOWN, LEFT, RIGHT }

    private Point position;
    private Direction direction;
    /*  +Location(): konstruktor, ami példányosítja a mozgathatót.
     *  			 Ez rendszerint a pálya beolvasásakor hívódik meg, 
     *  			 amíg elhelyezésre és beállításra nem kerül.  			 
    */
    public Location() {
        Application.printCall(this, "-->Location()");

        position = new Point(0, 0);
        direction = Direction.UP;
        Application.printCall(this, "<--");
    }
    /*  +getPosition(): getter függvény, ami lekérdezi a mozgatható helyzetét.
     * 	@return: a mozgatható helyzete pontkoordinátában.
     */
    public Point getPosition() {
        Application.printCall(this, "-->getPosition()");

        Application.printCall(this, "<--");
        return position;
    }
    /*  +getPosition(Point): setter függvény, ami beállítja a mozgatható helyzetét.
     *  @param position: a beállítandó pont, pontkoordinátával.
     */
    public void setPosition(Point position) {
        Application.printCall(this, "-->setPosition()");
        this.position = position;
        Application.printCall(this, "<--");
    }
    /*  +getDirection(): getter függvény, ami lekérdezi a mozgatható pillanatnyi irányát.
     * 					 A szkeletonban ez megvalósítja a játékos iránybeállítását is.
     * 	@return: a mozgatható iránya a 4 égtáj valamelyikével.
     */
    public Direction getDirection() {
        Application.printCall(this, "-->getDirection()");
//        return direction;

        // TODO: TOROLNI
        if (Test.playerdir == null)
            Test.setPlayerDir();
        Application.printCall(this, "<--");
        return Test.playerdir;
    }
    /*  +setDirection(): setter függvény, ami beállítja a mozgatható irányát.
     * 	@param direction: a beállítandó irány.
     */
    public void setDirection(Direction direction) {
        Application.printCall(this, "-->setDirection()");

        this.direction = direction;
        Application.printCall(this, "<--");
    }

    /*  +abstract destroy(): eseménykezelõ függvény, ami akkor hívódik meg,
     * 						 ha a mozgatható megsemmisül; tipikusan akkor, 
     * 						 ha az szakadékba kerül.
    */
    public abstract void destroy();
}
