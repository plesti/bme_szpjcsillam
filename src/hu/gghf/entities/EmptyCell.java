package hu.gghf.entities;

import hu.gghf.model.Application;
/*  +class EmptyCell : CellInterface
 *  Járólap pályaelem megvalósítása. A járólap egy
 *  közönséges padlólap, amire tetszõlegesen helyezhetõ
 *  játékos és doboz, szabad átjárást biztosít.
 *  Eseménykezelést nem végez.
 */
public class EmptyCell implements CellInterface {
	/*  +isStepable(): felüldefiniált függvény léphetõség lekérdezésére.
     *  			   Igaz értékkel tér vissza, mivel a járólap léphetõ.
     */
    @Override
    public boolean isStepable() {
        Application.printCall(this, "-->isStepable()");

        Application.printCall(this, "<--");
        return true;
    }
    /*  +onStepIn(Location): felüldefiniált függvény esemény kezelésére,
     * 					     amikor valami rákerül a járólapra.
     * 					     Nincs hatással a játékra.
     */
    @Override
    public void onStepIn(Location object) {
        Application.printCall(this, "-->onStepIn()");

        Application.printCall(this, "<--");
    }
    /*  +onStepOut(): felüldefiniált függvény, a járólapról lelépés kezelésére.
     * 				  Nincs hatással a játékra.
     */
    @Override
    public void onStepOut() {
        Application.printCall(this, "-->onStepOut()");

        Application.printCall(this, "<--");
    }
    /*  +isShootable(): felüldefiniált függvény. Visszaadja, hogy lõhetõ -e 
     * 					a járólap a felé kilõtt portállövedékkel. Mivel soha
     * 					nem lõhetõ, ezért hamis az értéke.
     */
    @Override
    public boolean isShootable() {
        Application.printCall(this, "-->isShootable()");
        Application.printCall(this, "<--");
        return false;
    }
    /*  +shoot(Player, Color): felüldefiniált függvény portálnyitás kezelésére.
     *  					   Nincs hatással a játékra, járólapon nem nyitható portál.
     */
    @Override
    public void shoot(Player player, Color color) {
        Application.printCall(this, "-->shoot()");
        Application.printCall(this, "<--");
    }
}
