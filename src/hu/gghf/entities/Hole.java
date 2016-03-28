package hu.gghf.entities;

import hu.gghf.model.Application;
/*  +class Hole : CellInterface
 *  Szakad�k p�lyaelem megval�s�t�sa. A szakad�k olyan
 *  r�gz�tett elem, ami a j�r�lap tulajdons�gaival b�r,
 *  kiv�ve, hogy a r�helyezett j�t�kos vagy doboz
 *  megsemmis�l. A l�ved�k �thatolhat rajta.
 */

public class Hole implements CellInterface {
	/*  +isStepable(): fel�ldefini�lt f�ggv�ny l�phet�s�g lek�rdez�s�re.
     *  			   Igaz �rt�kkel t�r vissza, mivel a szakad�kba
     *  			   lehet l�pni.
    */
    @Override
    public boolean isStepable() {
        Application.printCall(this, "-->isStepable()");

        Application.printCall(this, "<--");
        return true;
    }
    /*  +onStepIn(Location): fel�ldefini�lt f�ggv�ny esem�ny kezel�s�re,
     * 					     amikor valami a szakad�kba ker�l. Az ide
     * 						 ker�l� t�rgy megsemmis�l. 					     
     */
    @Override
    public void onStepIn(Location object) {
        Application.printCall(this, "-->onStepIn()");

        object.destroy();
        Application.printCall(this, "<--");
    }
    /*  +onStepOut(): fel�ldefini�lt f�ggv�ny, a szakad�kb�l kiker�l�s 
     * 				  kezel�s�re. Nincs hat�ssal a j�t�kra.
     */
    @Override
    public void onStepOut() {
        Application.printCall(this, "-->onStepOut()");
        Application.printCall(this, "<--");
    }
    /*  +isShootable(): fel�ldefini�lt f�ggv�ny. Visszaadja, hogy l�het� -e 
     * 					a szakad�k a fel� kil�tt port�ll�ved�kkel. Mivel soha
     * 					nem l�het�, ez�rt hamis az �rt�ke.
     */
    @Override
    public boolean isShootable() {
        Application.printCall(this, "-->isShootable()");

        Application.printCall(this, "<--");
        return false;
    }
    /*  +shoot(Player, Color): fel�ldefini�lt f�ggv�ny port�lnyit�s kezel�s�re.
     *  					   Nincs hat�ssal a j�t�kra, mert szakad�kon
     *  					   sehogy nem nyithat� port�l.
     */
    @Override
    public void shoot(Player player, Color color) {
        Application.printCall(this, "-->shoot()");

        Application.printCall(this, "<--");
    }
}
