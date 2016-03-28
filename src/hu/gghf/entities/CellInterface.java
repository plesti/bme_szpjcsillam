package hu.gghf.entities;

/*  +interface CellInterface
 *  Interfészosztály rögzített pályaelemek alaptulajdonságainak és
 *  alapfüggvényeinek felsorolására. A játéktér alapegysége,
 *  rendelkezik eseményfüggvényekkel és egyéb saját tulajdonságokkal.
 *  A leszármazó osztályok egyedileg valósítják majd meg az interfész
 *  metódusait, itt csak egy összegzõ leírás található meg róluk.
 */
public interface CellInterface {
	
	//Felsorolt típus az egyes portálfalszínek beállításához. 
    enum Color { BLUE, YELLOW }

    /* isStepable(): lekérdezi, hogy helyezhetõ -e doboz/játékos a rögzített pályaelemre.
     * @return: igaz, ha léphetõ, hamis, ha nem.
     */
    boolean isStepable();
    /* isShootable(): lekérdezi, hogy az adott rögz. pályaelem lõhetõ -e, 
     * 				  azaz beléütközik portállövedék.
     * @return: igaz, ha megáll benne, hamis, ha igen.
     */
    boolean isShootable();
    /* onStepIn(Location): eseménykezelõ függvény arra az esetre, amikor egy tárgy 
     * 			   		   a rögzített pályaelemre kerül.
     * @param object: az az objektum, ami interakcióba lép a pályaelemmel.
     */    
    void onStepIn(Location object);
    /* onStepOut(): eseménykezelõ függvény arra az esetre, amikor egy tárgy 
     * 				a rögzített pályaelemrõl lekerül.
     */
    void onStepOut();
    /* shoot(Player, Color): lövésfüggvény, ami portált nyit a pályaelemen, a játékos
     * 						 irányától függõen. Érdemben csak a portálfal valósítja meg.
     * @param player: a játékos referenciája.
     * @param color: a nyitandó portál színe (kék vagy sárga).
     */
    void shoot(Player player, Color color);
}