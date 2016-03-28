package hu.gghf.entities;

import hu.gghf.model.Application;
/*  +class Wall : CellInterface
 *  Fal p�lyaelem megval�s�t�sa. �tj�rhatatlan p�lyaelem, 
 *  amelybe nem l�phet se j�t�kos, se egy�b dolog. A bel� 
 *  l�tt port�ll�ved�k megakad.
 */
public class Wall implements CellInterface {
	/*  +isStepable(): fel�ldefini�lt f�ggv�ny l�phet�s�g lek�rdez�s�re.
     *  			   Hamis �rt�kkel t�r vissza, mivel a falba nem lehet 
     *  			   l�pni.
    */
    @Override
    public boolean isStepable() {
        Application.printCall(this, "-->isStepable()");
        Application.printCall(this, "<--");
        return false;
    }
    /*  +onStepIn(Location): fel�ldefini�lt f�ggv�ny esem�ny kezel�s�re,
     * 					     amikor valamit a falra tesznek. Nincs hat�ssal
     * 						 a j�t�kra, mert nem helyezhet� r� t�rgy.		     
     */
    @Override
    public void onStepIn(Location object) {
        Application.printCall(this, "-->onStepIn()");

        Application.printCall(this, "<--");
    }
    /*  +onStepOut(): fel�ldefini�lt f�ggv�ny, a falb�l kil�p�s 
     * 				  kezel�s�re. Nincs hat�ssal a j�t�kra.
     */
    @Override
    public void onStepOut() {
        Application.printCall(this, "-->onStepOut()");
        Application.printCall(this, "<--");
    }
    /*  +isShootable(): fel�ldefini�lt f�ggv�ny. Visszaadja, hogy l�het� -e 
     * 					a fal a fel� kil�tt port�ll�ved�kkel. Mivel l�het�, 
     * 					ez�rt igazat ad vissza.
     */
    @Override
    public boolean isShootable() {
        Application.printCall(this, "-->isShootable()");
        Application.printCall(this, "<--");
        return true;
    }
    /*  +shoot(Player, Color): fel�ldefini�lt f�ggv�ny port�lnyit�s kezel�s�re.
     *  					   Nincs hat�ssal a j�t�kra, mert b�r l�het� a fal,
     *  					   nem nyithat� port�l rajta.
     */
    @Override
    public void shoot(Player player, Color color) {
        Application.printCall(this, "-->shoot()");

        Application.printCall(this, "<--");
    }
}
