package hu.gghf.entities;

import hu.gghf.model.Application;
/*  +class Door : CellInterface
 *  Ajt� p�lyaelem megval�s�t�sa. Az ajt� egy k�l�nleges
 *  fal, amelynek lehet nyitott �s z�rt �llapota.
 *  Nyitott �llapotban az ajt� szabad �tj�r�st biztos�t,
 *  z�rva viszont �tj�rhatatlan.
 *  Az ajt� rendszerint csak akkor ny�lik ki, ha a hozz�
 *  tartoz� nyom�lap le van nyomva.
 */
public class Door implements CellInterface {
	//Boolean v�ltoz� az ajt� nyitott �llapot�nak jelz�s�re.
    private boolean open = false;
    
    /*  +setOpen(boolean): setter f�ggv�ny, ami kinyitja vagy bez�rja
     * 					   az ajt�t.
     *  @param isopen: logikai igaz/hamis, ami az ajt� j�vend�beli
     *  			   nyitott/z�rt �llapot�t jelzi. 
     */
    public void setOpen(boolean isopen) {
        Application.printCall(this, "-->setOpen()");

        open = isopen;
        Application.printCall(this, "<--");
    }

    /*  +isStepable(): fel�ldefini�lt f�ggv�ny, az ajt� l�phet�s�g�re
     * 				   vonatkoz�an. Az ajt� �tj�rhat�, ha nyitva van, 
     * 				   egy�bk�nt nem.
     *  @return: az ajt� ny�lt �llapot�nak �rt�ke. 
     */
    @Override
    public boolean isStepable() {
        Application.printCall(this, "-->isStepable()");

        Application.printCall(this, "<--");
        return open;
    }

    /*  +onStepIn(Location): fel�ldefini�lt f�ggv�ny, az ajt�ra l�p�s kezel�s�re.
     * 				 	     Nincs hat�ssal a j�t�kra.
     */
    @Override
    public void onStepIn(Location object) {
        Application.printCall(this, "-->onStepIn()");
        Application.printCall(this, "<--");
    }
    /*  +onStepOut(): fel�ldefini�lt f�ggv�ny, az ajt�r�l lel�p�s kezel�s�re.
     * 				 Nincs hat�ssal a j�t�kra.
     */
    @Override
    public void onStepOut() {
        Application.printCall(this, "-->onStepOut()");
        Application.printCall(this, "<--");
    }
    /*  +isShootable(): fel�ldefini�lt f�ggv�ny. Visszaadja, hogy megakad -e 
     * 					az ajt�n a fel� kil�tt port�ll�ved�k. Ha z�rva van, 
     * 					megakad, egy�bk�nt nem.
     *  @return: az ajt� ny�lt �llapot�nak ellent�tes �rt�ke. 
     */
    @Override
    public boolean isShootable() {
        Application.printCall(this, "-->isShootable()");
        Application.printCall(this, "<--");
        return !open;
    }
    /*  +shoot(Player, Color): fel�ldefini�lt f�ggv�ny port�lnyit�s kezel�s�re.
     *  					   Nincs hat�ssal a j�t�kra, ajt�n nem nyithat� port�l.
     */
    @Override
    public void shoot(Player player, Color color) {
        Application.printCall(this, "-->shoot()");
        Application.printCall(this, "<--");
    }
}
