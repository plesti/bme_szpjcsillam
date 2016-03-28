package hu.gghf.entities;

import hu.gghf.model.Application;
import hu.gghf.model.Game;
/*  +class ZPM : CellInterface
 *  ZPM pályaelem megvalósítása. Különleges
 *  járható pályaelem, amelyre lépve a játékos felszedi azt,
 *  1-gyel növelve a ZPM-számlálót. Minden más esetben
 *  közönséges járólapként viselkedik.
 */
public class ZPM implements CellInterface {
	/*  Attribútumlista:
	 *  -discovered: logikai érték, ami igazzá válik, ha a ZPM-et felszedték.
	 *  -game: referencia az adott játékmenetre.
	 */
    private boolean discovered = false;
    private Game game;
    /*  +ZPM(Game): konstruktor, ami példányosítja az eltárolt játék-
     * 				objektumot. Ez rendszerint a pálya beolvasásakor 
     * 				hívódik meg.
     *  @param game: az eltárolandó játékmenet referenciája.
    */
    public ZPM(Game game) {
        Application.printCall(this, "-->ZPM()");
        this.game = game;
        Application.printCall(this, "<--");
    }
    /*  +isStepable(): felüldefiniált függvény léphetõség lekérdezésére.
     *  			   Igaz értékkel tér vissza, mivel a ZPM-re lehet 
     *  			   lépni, akár felfedték, akár nem.
    */
    @Override
    public boolean isStepable() {
        Application.printCall(this, "-->isStepable()");
        Application.printCall(this, "<--");
        return true;
    }
    /*  +onStepIn(Location): felüldefiniált függvény esemény kezelésére,
     * 					     amikor rálépnek a ZPM-re. Ha a játékos idelép,
     * 						 akkor kap egy ZPM-et és a ZPM felfedett állapotba
     * 						 kerül.		     
     */
    @Override
    public void onStepIn(Location obj) {
        Application.printCall(this, "-->onStepIn()");

        if (!discovered) {
            game.addZPM();
            discovered = true;
        }
        Application.printCall(this, "<--");
    }
    /*  +onStepOut(): felüldefiniált függvény, a ZPM-rõl lelépés 
     * 				  kezelésére. Nincs hatással a játékra.
     */
    @Override
    public void onStepOut() {
        Application.printCall(this, "-->onStepOut()");
        Application.printCall(this, "<--");
    }
    /*  +isShootable(): felüldefiniált függvény. Visszaadja, hogy lõhetõ -e 
     * 					a ZPM a felé kilõtt portállövedékkel. Mivel nem lõhetõ, 
     * 					ezért hamisat ad vissza.
     */
    @Override
    public boolean isShootable() {
        Application.printCall(this, "-->isShootable()");
        Application.printCall(this, "<--");
        return false;
    }
    /*  +shoot(Player, Color): felüldefiniált függvény portálnyitás kezelésére.
     *  					   Nincs hatással a játékra, mert ZPM-en nem nyitható 
     *  					   portál.
     */
    @Override
    public void shoot(Player player, Color color) {
        Application.printCall(this, "-->shoot()");
        Application.printCall(this, "<--");
    }
}
