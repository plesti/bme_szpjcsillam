package hu.gghf.entities;

import hu.gghf.model.Application;
/*  +class PressurePlate : CellInterface
 *  Mérleg pályaelem megvalósítása. A mérleg olyan
 *  járólap, aminek van egy lenyomott és egy felengedett
 *  állapota. Ha rálépnek, a hozzá tartozó ajtó kinyílik,
 *  egyébként az zárva marad. Portál rajta nem nyitható.
 */
public class PressurePlate implements CellInterface {
	//A mérleghez tartozik egy ajtóreferencia, amit lenyomáskor vezérel.
    private Door door;
    /*  +PressurePlate(Door): konstruktor, ami példányosítja az ajtóobjektumot.
     *  			          Ez rendszerint a pálya beolvasásakor hívódik meg.
     *  @param door: az eltárolandó ajtó referenciája.
    */
    public PressurePlate(Door door) {
        Application.printCall(this, "-->PressurePlate()");
        this.door = door;
        Application.printCall(this, "<--");
    }
    /*  +isStepable(): felüldefiniált függvény léphetõség lekérdezésére.
     *  			   Igaz értékkel tér vissza, mivel a mérlegre lehet 
     *  			   lépni, bármilyen állapotban is legyen.
    */
    @Override
    public boolean isStepable() {
        Application.printCall(this, "-->isStepable()");
        Application.printCall(this, "<--");
        return true;
    }
    /*  +onStepIn(Location): felüldefiniált függvény esemény kezelésére,
     * 					     amikor valamit a mérlegre helyeznek. Ha a mérlegre
     * 						 rálépnek, akkor a hozzá tartozó ajtót kinyitja.		     
     */
    @Override
    public void onStepIn(Location obj) {
        Application.printCall(this, "-->onStepIn()");
        door.setOpen(true);
        Application.printCall(this, "<--");
    }
    /*  +onStepOut(): felüldefiniált függvény, a mérlegrõl lelépés kezelésére.
     * 				  Ellentétes mûködésû onStepIn()-nel: bezárja a hozzá
     * 				  tartozó ajtót, ha lekerül róla a rajta lévõ tárgy.
     */
    @Override
    public void onStepOut() {
        Application.printCall(this, "-->onStepOut()");
        door.setOpen(false);
        Application.printCall(this, "<--");
    }
    /*  +isShootable(): felüldefiniált függvény. Visszaadja, hogy lõhetõ -e 
     * 					a mérleg a felé kilõtt portállövedékkel. Mivel soha
     * 					nem lõhetõ, ezért hamis az értéke.
     */
    @Override
    public boolean isShootable() {
        Application.printCall(this, "-->isShootable()");
        Application.printCall(this, "<--");
        return false;
    }
    /*  +shoot(Player, Color): felüldefiniált függvény portálnyitás kezelésére.
     *  					   Nincs hatással a játékra, mert mérlegen soha nem 
     *  					   nyitható portál.
     */
    @Override
    public void shoot(Player player, Color color) {
        Application.printCall(this, "-->shoot()");
        Application.printCall(this, "<--");
    }
}
