package hu.gghf.entities;

import hu.gghf.model.Application;
import hu.gghf.model.Game;
/*  +class ShieldGenerator : CellInterface
 *  Pajzsgener�tor p�lyaelem megval�s�t�sa. K�l�nleges
 *  j�rhat� p�lyaelem, amelyre l�pve a j�t�kos megnyeri a
 *  j�t�kot, ha van n�la 4 ZPM. Egy�bk�nt, a pajzsgener�tor
 *  k�z�ns�ges j�r�lapk�nt viselkedik, annyi k�l�nbs�ggel,
 *  hogy a bel� l�tt port�ll�ved�k megakad.
 */
public class ShieldGenerator implements CellInterface {
	//Referencia a j�t�kobjektumra, ahol a ZPM-ek sz�ma t�rol�dik.
    Game game;
    /*  +ShieldGenerator(Game): konstruktor, ami p�ld�nyos�tja az elt�rolt j�t�k-
     * 						    objektumot. Ez rendszerint a p�lya beolvas�sakor 
     * 					   	    h�v�dik meg.
     *  @param game: az elt�roland� j�t�kmenet referenci�ja.
    */
    public ShieldGenerator(Game game) {
        Application.printCall(this, "-->ShieldGenerator()");
        this.game = game;
        Application.printCall(this, "<--");
    }
    /*  +isStepable(): fel�ldefini�lt f�ggv�ny l�phet�s�g lek�rdez�s�re.
     *  			   Igaz �rt�kkel t�r vissza, mivel a pajzsgener�torra lehet 
     *  			   l�pni.
    */
    @Override
    public boolean isStepable() {
        Application.printCall(this, "-->isStepable()");
        Application.printCall(this, "<--");
        return true;
    }
    /*  +onStepIn(Location): fel�ldefini�lt f�ggv�ny esem�ny kezel�s�re,
     * 					     amikor valamit a pajzsgener�torra helyeznek.
     * 						 Ha a j�t�kos vagy a hozz�tartoz� doboz ide ker�l,
     * 						 �s megvan 4 ZPM, akkor a j�t�kot megnyert�k.		     
     */
    @Override
    public void onStepIn(Location object) {
        Application.printCall(this, "-->onStepIn()");

        if (game.getZpmCount() == 4) {
            System.out.println("Nyertel!");
        }
        Application.printCall(this, "<--");
    }
    /*  +onStepOut(): fel�ldefini�lt f�ggv�ny, a pajzsgener�torr�l lel�p�s 
     * 				  kezel�s�re. Nincs hat�ssal a j�t�kra.
     */
    @Override
    public void onStepOut() {
        Application.printCall(this, "-->onStepOut()");
        Application.printCall(this, "<--");
    }
    /*  +isShootable(): fel�ldefini�lt f�ggv�ny. Visszaadja, hogy l�het� -e 
     * 					a m�rleg a fel� kil�tt port�ll�ved�kkel. Mivel l�het�, 
     * 					ez�rt igazat ad vissza.
     */
    @Override
    public boolean isShootable() {
        Application.printCall(this, "-->isShootable()");
        Application.printCall(this, "<--");
        return true;
    }
    /*  +shoot(Player, Color): fel�ldefini�lt f�ggv�ny port�lnyit�s kezel�s�re.
     *  					   Nincs hat�ssal a j�t�kra, mert b�r l�het� a
     *  					   gener�tor, nem nyithat� port�l rajta.
     */
    @Override
    public void shoot(Player player, Color color) {
        Application.printCall(this, "-->shoot()");
        Application.printCall(this, "<--");
    }
}
