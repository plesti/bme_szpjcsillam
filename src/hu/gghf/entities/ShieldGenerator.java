package hu.gghf.entities;

import hu.gghf.model.Application;
import hu.gghf.model.Game;
/*  +class ShieldGenerator : CellInterface
 *  Pajzsgenerátor pályaelem megvalósítása. Különleges
 *  járható pályaelem, amelyre lépve a játékos megnyeri a
 *  játékot, ha van nála 4 ZPM. Egyébként, a pajzsgenerátor
 *  közönséges járólapként viselkedik, annyi különbséggel,
 *  hogy a belé lõtt portállövedék megakad.
 */
public class ShieldGenerator implements CellInterface {
	//Referencia a játékobjektumra, ahol a ZPM-ek száma tárolódik.
    Game game;
    /*  +ShieldGenerator(Game): konstruktor, ami példányosítja az eltárolt játék-
     * 						    objektumot. Ez rendszerint a pálya beolvasásakor 
     * 					   	    hívódik meg.
     *  @param game: az eltárolandó játékmenet referenciája.
    */
    public ShieldGenerator(Game game) {
        Application.printCall(this, "-->ShieldGenerator()");
        this.game = game;
        Application.printCall(this, "<--");
    }
    /*  +isStepable(): felüldefiniált függvény léphetõség lekérdezésére.
     *  			   Igaz értékkel tér vissza, mivel a pajzsgenerátorra lehet 
     *  			   lépni.
    */
    @Override
    public boolean isStepable() {
        Application.printCall(this, "-->isStepable()");
        Application.printCall(this, "<--");
        return true;
    }
    /*  +onStepIn(Location): felüldefiniált függvény esemény kezelésére,
     * 					     amikor valamit a pajzsgenerátorra helyeznek.
     * 						 Ha a játékos vagy a hozzátartozó doboz ide kerül,
     * 						 és megvan 4 ZPM, akkor a játékot megnyerték.		     
     */
    @Override
    public void onStepIn(Location object) {
        Application.printCall(this, "-->onStepIn()");

        if (game.getZpmCount() == 4) {
            System.out.println("Nyertel!");
        }
        Application.printCall(this, "<--");
    }
    /*  +onStepOut(): felüldefiniált függvény, a pajzsgenerátorról lelépés 
     * 				  kezelésére. Nincs hatással a játékra.
     */
    @Override
    public void onStepOut() {
        Application.printCall(this, "-->onStepOut()");
        Application.printCall(this, "<--");
    }
    /*  +isShootable(): felüldefiniált függvény. Visszaadja, hogy lõhetõ -e 
     * 					a mérleg a felé kilõtt portállövedékkel. Mivel lõhetõ, 
     * 					ezért igazat ad vissza.
     */
    @Override
    public boolean isShootable() {
        Application.printCall(this, "-->isShootable()");
        Application.printCall(this, "<--");
        return true;
    }
    /*  +shoot(Player, Color): felüldefiniált függvény portálnyitás kezelésére.
     *  					   Nincs hatással a játékra, mert bár lõhetõ a
     *  					   generátor, nem nyitható portál rajta.
     */
    @Override
    public void shoot(Player player, Color color) {
        Application.printCall(this, "-->shoot()");
        Application.printCall(this, "<--");
    }
}
