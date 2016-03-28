package hu.gghf.entities;

import hu.gghf.model.Application;
/*  +class Door : CellInterface
 *  Ajtó pályaelem megvalósítása. Az ajtó egy különleges
 *  fal, amelynek lehet nyitott és zárt állapota.
 *  Nyitott állapotban az ajtó szabad átjárást biztosít,
 *  zárva viszont átjárhatatlan.
 *  Az ajtó rendszerint csak akkor nyílik ki, ha a hozzá
 *  tartozó nyomólap le van nyomva.
 */
public class Door implements CellInterface {
	//Boolean változó az ajtó nyitott állapotának jelzésére.
    private boolean open = false;
    
    /*  +setOpen(boolean): setter függvény, ami kinyitja vagy bezárja
     * 					   az ajtót.
     *  @param isopen: logikai igaz/hamis, ami az ajtó jövendõbeli
     *  			   nyitott/zárt állapotát jelzi. 
     */
    public void setOpen(boolean isopen) {
        Application.printCall(this, "-->setOpen()");

        open = isopen;
        Application.printCall(this, "<--");
    }

    /*  +isStepable(): felüldefiniált függvény, az ajtó léphetõségére
     * 				   vonatkozóan. Az ajtó átjárható, ha nyitva van, 
     * 				   egyébként nem.
     *  @return: az ajtó nyílt állapotának értéke. 
     */
    @Override
    public boolean isStepable() {
        Application.printCall(this, "-->isStepable()");

        Application.printCall(this, "<--");
        return open;
    }

    /*  +onStepIn(Location): felüldefiniált függvény, az ajtóra lépés kezelésére.
     * 				 	     Nincs hatással a játékra.
     */
    @Override
    public void onStepIn(Location object) {
        Application.printCall(this, "-->onStepIn()");
        Application.printCall(this, "<--");
    }
    /*  +onStepOut(): felüldefiniált függvény, az ajtóról lelépés kezelésére.
     * 				 Nincs hatással a játékra.
     */
    @Override
    public void onStepOut() {
        Application.printCall(this, "-->onStepOut()");
        Application.printCall(this, "<--");
    }
    /*  +isShootable(): felüldefiniált függvény. Visszaadja, hogy megakad -e 
     * 					az ajtón a felé kilõtt portállövedék. Ha zárva van, 
     * 					megakad, egyébként nem.
     *  @return: az ajtó nyílt állapotának ellentétes értéke. 
     */
    @Override
    public boolean isShootable() {
        Application.printCall(this, "-->isShootable()");
        Application.printCall(this, "<--");
        return !open;
    }
    /*  +shoot(Player, Color): felüldefiniált függvény portálnyitás kezelésére.
     *  					   Nincs hatással a játékra, ajtón nem nyitható portál.
     */
    @Override
    public void shoot(Player player, Color color) {
        Application.printCall(this, "-->shoot()");
        Application.printCall(this, "<--");
    }
}
