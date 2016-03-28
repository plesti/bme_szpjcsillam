package hu.gghf.entities;

import hu.gghf.model.Application;
/*  +class Wall : CellInterface
 *  Fal pályaelem megvalósítása. Átjárhatatlan pályaelem, 
 *  amelybe nem léphet se játékos, se egyéb dolog. A belé 
 *  lõtt portállövedék megakad.
 */
public class Wall implements CellInterface {
	/*  +isStepable(): felüldefiniált függvény léphetõség lekérdezésére.
     *  			   Hamis értékkel tér vissza, mivel a falba nem lehet 
     *  			   lépni.
    */
    @Override
    public boolean isStepable() {
        Application.printCall(this, "-->isStepable()");
        Application.printCall(this, "<--");
        return false;
    }
    /*  +onStepIn(Location): felüldefiniált függvény esemény kezelésére,
     * 					     amikor valamit a falra tesznek. Nincs hatással
     * 						 a játékra, mert nem helyezhetõ rá tárgy.		     
     */
    @Override
    public void onStepIn(Location object) {
        Application.printCall(this, "-->onStepIn()");

        Application.printCall(this, "<--");
    }
    /*  +onStepOut(): felüldefiniált függvény, a falból kilépés 
     * 				  kezelésére. Nincs hatással a játékra.
     */
    @Override
    public void onStepOut() {
        Application.printCall(this, "-->onStepOut()");
        Application.printCall(this, "<--");
    }
    /*  +isShootable(): felüldefiniált függvény. Visszaadja, hogy lõhetõ -e 
     * 					a fal a felé kilõtt portállövedékkel. Mivel lõhetõ, 
     * 					ezért igazat ad vissza.
     */
    @Override
    public boolean isShootable() {
        Application.printCall(this, "-->isShootable()");
        Application.printCall(this, "<--");
        return true;
    }
    /*  +shoot(Player, Color): felüldefiniált függvény portálnyitás kezelésére.
     *  					   Nincs hatással a játékra, mert bár lõhetõ a fal,
     *  					   nem nyitható portál rajta.
     */
    @Override
    public void shoot(Player player, Color color) {
        Application.printCall(this, "-->shoot()");

        Application.printCall(this, "<--");
    }
}
