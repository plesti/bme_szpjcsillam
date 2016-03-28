package hu.gghf.entities;

import hu.gghf.model.Application;
import hu.gghf.model.Game;
/*  +class ZPM : CellInterface
 *  ZPM p�lyaelem megval�s�t�sa. K�l�nleges
 *  j�rhat� p�lyaelem, amelyre l�pve a j�t�kos felszedi azt,
 *  1-gyel n�velve a ZPM-sz�ml�l�t. Minden m�s esetben
 *  k�z�ns�ges j�r�lapk�nt viselkedik.
 */
public class ZPM implements CellInterface {
	/*  Attrib�tumlista:
	 *  -discovered: logikai �rt�k, ami igazz� v�lik, ha a ZPM-et felszedt�k.
	 *  -game: referencia az adott j�t�kmenetre.
	 */
    private boolean discovered = false;
    private Game game;
    /*  +ZPM(Game): konstruktor, ami p�ld�nyos�tja az elt�rolt j�t�k-
     * 				objektumot. Ez rendszerint a p�lya beolvas�sakor 
     * 				h�v�dik meg.
     *  @param game: az elt�roland� j�t�kmenet referenci�ja.
    */
    public ZPM(Game game) {
        Application.printCall(this, "-->ZPM()");
        this.game = game;
        Application.printCall(this, "<--");
    }
    /*  +isStepable(): fel�ldefini�lt f�ggv�ny l�phet�s�g lek�rdez�s�re.
     *  			   Igaz �rt�kkel t�r vissza, mivel a ZPM-re lehet 
     *  			   l�pni, ak�r felfedt�k, ak�r nem.
    */
    @Override
    public boolean isStepable() {
        Application.printCall(this, "-->isStepable()");
        Application.printCall(this, "<--");
        return true;
    }
    /*  +onStepIn(Location): fel�ldefini�lt f�ggv�ny esem�ny kezel�s�re,
     * 					     amikor r�l�pnek a ZPM-re. Ha a j�t�kos idel�p,
     * 						 akkor kap egy ZPM-et �s a ZPM felfedett �llapotba
     * 						 ker�l.		     
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
    /*  +onStepOut(): fel�ldefini�lt f�ggv�ny, a ZPM-r�l lel�p�s 
     * 				  kezel�s�re. Nincs hat�ssal a j�t�kra.
     */
    @Override
    public void onStepOut() {
        Application.printCall(this, "-->onStepOut()");
        Application.printCall(this, "<--");
    }
    /*  +isShootable(): fel�ldefini�lt f�ggv�ny. Visszaadja, hogy l�het� -e 
     * 					a ZPM a fel� kil�tt port�ll�ved�kkel. Mivel nem l�het�, 
     * 					ez�rt hamisat ad vissza.
     */
    @Override
    public boolean isShootable() {
        Application.printCall(this, "-->isShootable()");
        Application.printCall(this, "<--");
        return false;
    }
    /*  +shoot(Player, Color): fel�ldefini�lt f�ggv�ny port�lnyit�s kezel�s�re.
     *  					   Nincs hat�ssal a j�t�kra, mert ZPM-en nem nyithat� 
     *  					   port�l.
     */
    @Override
    public void shoot(Player player, Color color) {
        Application.printCall(this, "-->shoot()");
        Application.printCall(this, "<--");
    }
}
