package hu.gghf.entities;

import hu.gghf.model.Application;
/*  +class EmptyCell : CellInterface
 *  J�r�lap p�lyaelem megval�s�t�sa. A j�r�lap egy
 *  k�z�ns�ges padl�lap, amire tetsz�legesen helyezhet�
 *  j�t�kos �s doboz, szabad �tj�r�st biztos�t.
 *  Esem�nykezel�st nem v�gez.
 */
public class EmptyCell implements CellInterface {
	/*  +isStepable(): fel�ldefini�lt f�ggv�ny l�phet�s�g lek�rdez�s�re.
     *  			   Igaz �rt�kkel t�r vissza, mivel a j�r�lap l�phet�.
     */
    @Override
    public boolean isStepable() {
        Application.printCall(this, "-->isStepable()");

        Application.printCall(this, "<--");
        return true;
    }
    /*  +onStepIn(Location): fel�ldefini�lt f�ggv�ny esem�ny kezel�s�re,
     * 					     amikor valami r�ker�l a j�r�lapra.
     * 					     Nincs hat�ssal a j�t�kra.
     */
    @Override
    public void onStepIn(Location object) {
        Application.printCall(this, "-->onStepIn()");

        Application.printCall(this, "<--");
    }
    /*  +onStepOut(): fel�ldefini�lt f�ggv�ny, a j�r�lapr�l lel�p�s kezel�s�re.
     * 				  Nincs hat�ssal a j�t�kra.
     */
    @Override
    public void onStepOut() {
        Application.printCall(this, "-->onStepOut()");

        Application.printCall(this, "<--");
    }
    /*  +isShootable(): fel�ldefini�lt f�ggv�ny. Visszaadja, hogy l�het� -e 
     * 					a j�r�lap a fel� kil�tt port�ll�ved�kkel. Mivel soha
     * 					nem l�het�, ez�rt hamis az �rt�ke.
     */
    @Override
    public boolean isShootable() {
        Application.printCall(this, "-->isShootable()");
        Application.printCall(this, "<--");
        return false;
    }
    /*  +shoot(Player, Color): fel�ldefini�lt f�ggv�ny port�lnyit�s kezel�s�re.
     *  					   Nincs hat�ssal a j�t�kra, j�r�lapon nem nyithat� port�l.
     */
    @Override
    public void shoot(Player player, Color color) {
        Application.printCall(this, "-->shoot()");
        Application.printCall(this, "<--");
    }
}
