package hu.gghf.entities;

import hu.gghf.model.Application;
/*  +class Hole : CellInterface
 *  Szakadék pályaelem megvalósítása. A szakadék olyan
 *  rögzített elem, ami a járólap tulajdonságaival bír,
 *  kivéve, hogy a ráhelyezett játékos vagy doboz
 *  megsemmisül. A lövedék áthatolhat rajta.
 */

public class Hole implements CellInterface {
	/*  +isStepable(): felüldefiniált függvény léphetõség lekérdezésére.
     *  			   Igaz értékkel tér vissza, mivel a szakadékba
     *  			   lehet lépni.
    */
    @Override
    public boolean isStepable() {
        Application.printCall(this, "-->isStepable()");

        Application.printCall(this, "<--");
        return true;
    }
    /*  +onStepIn(Location): felüldefiniált függvény esemény kezelésére,
     * 					     amikor valami a szakadékba kerül. Az ide
     * 						 kerülõ tárgy megsemmisül. 					     
     */
    @Override
    public void onStepIn(Location object) {
        Application.printCall(this, "-->onStepIn()");

        object.destroy();
        Application.printCall(this, "<--");
    }
    /*  +onStepOut(): felüldefiniált függvény, a szakadékból kikerülés 
     * 				  kezelésére. Nincs hatással a játékra.
     */
    @Override
    public void onStepOut() {
        Application.printCall(this, "-->onStepOut()");
        Application.printCall(this, "<--");
    }
    /*  +isShootable(): felüldefiniált függvény. Visszaadja, hogy lõhetõ -e 
     * 					a szakadék a felé kilõtt portállövedékkel. Mivel soha
     * 					nem lõhetõ, ezért hamis az értéke.
     */
    @Override
    public boolean isShootable() {
        Application.printCall(this, "-->isShootable()");

        Application.printCall(this, "<--");
        return false;
    }
    /*  +shoot(Player, Color): felüldefiniált függvény portálnyitás kezelésére.
     *  					   Nincs hatással a játékra, mert szakadékon
     *  					   sehogy nem nyitható portál.
     */
    @Override
    public void shoot(Player player, Color color) {
        Application.printCall(this, "-->shoot()");

        Application.printCall(this, "<--");
    }
}
