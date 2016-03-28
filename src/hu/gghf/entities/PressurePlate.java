package hu.gghf.entities;

import hu.gghf.model.Application;
/*  +class PressurePlate : CellInterface
 *  M�rleg p�lyaelem megval�s�t�sa. A m�rleg olyan
 *  j�r�lap, aminek van egy lenyomott �s egy felengedett
 *  �llapota. Ha r�l�pnek, a hozz� tartoz� ajt� kiny�lik,
 *  egy�bk�nt az z�rva marad. Port�l rajta nem nyithat�.
 */
public class PressurePlate implements CellInterface {
	//A m�rleghez tartozik egy ajt�referencia, amit lenyom�skor vez�rel.
    private Door door;
    /*  +PressurePlate(Door): konstruktor, ami p�ld�nyos�tja az ajt�objektumot.
     *  			          Ez rendszerint a p�lya beolvas�sakor h�v�dik meg.
     *  @param door: az elt�roland� ajt� referenci�ja.
    */
    public PressurePlate(Door door) {
        Application.printCall(this, "-->PressurePlate()");
        this.door = door;
        Application.printCall(this, "<--");
    }
    /*  +isStepable(): fel�ldefini�lt f�ggv�ny l�phet�s�g lek�rdez�s�re.
     *  			   Igaz �rt�kkel t�r vissza, mivel a m�rlegre lehet 
     *  			   l�pni, b�rmilyen �llapotban is legyen.
    */
    @Override
    public boolean isStepable() {
        Application.printCall(this, "-->isStepable()");
        Application.printCall(this, "<--");
        return true;
    }
    /*  +onStepIn(Location): fel�ldefini�lt f�ggv�ny esem�ny kezel�s�re,
     * 					     amikor valamit a m�rlegre helyeznek. Ha a m�rlegre
     * 						 r�l�pnek, akkor a hozz� tartoz� ajt�t kinyitja.		     
     */
    @Override
    public void onStepIn(Location obj) {
        Application.printCall(this, "-->onStepIn()");
        door.setOpen(true);
        Application.printCall(this, "<--");
    }
    /*  +onStepOut(): fel�ldefini�lt f�ggv�ny, a m�rlegr�l lel�p�s kezel�s�re.
     * 				  Ellent�tes m�k�d�s� onStepIn()-nel: bez�rja a hozz�
     * 				  tartoz� ajt�t, ha leker�l r�la a rajta l�v� t�rgy.
     */
    @Override
    public void onStepOut() {
        Application.printCall(this, "-->onStepOut()");
        door.setOpen(false);
        Application.printCall(this, "<--");
    }
    /*  +isShootable(): fel�ldefini�lt f�ggv�ny. Visszaadja, hogy l�het� -e 
     * 					a m�rleg a fel� kil�tt port�ll�ved�kkel. Mivel soha
     * 					nem l�het�, ez�rt hamis az �rt�ke.
     */
    @Override
    public boolean isShootable() {
        Application.printCall(this, "-->isShootable()");
        Application.printCall(this, "<--");
        return false;
    }
    /*  +shoot(Player, Color): fel�ldefini�lt f�ggv�ny port�lnyit�s kezel�s�re.
     *  					   Nincs hat�ssal a j�t�kra, mert m�rlegen soha nem 
     *  					   nyithat� port�l.
     */
    @Override
    public void shoot(Player player, Color color) {
        Application.printCall(this, "-->shoot()");
        Application.printCall(this, "<--");
    }
}
