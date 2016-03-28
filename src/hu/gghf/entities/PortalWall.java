package hu.gghf.entities;

import hu.gghf.model.Application;
import java.awt.*;

/*  +class PortalWall : CellInterface, Location 
 *  Port�lfal p�lyaelem megval�s�t�sa. A port�lfal egy speci�lis
 *  fal, amelyen port�l nyithat� megadott sz�n� port�ll�ved�kkel. 
 *  Ha a t�rk�pen van egy-egy nyitott k�k �s s�rga port�l, akkor 
 *  az egyik port�lfalon �tmen� t�rgy vagy j�t�kos a m�sik
 *  port�lfalba ker�l (teleport�l).
 *  A port�lfalon l�v� nyitott port�l megsemmis�l, ha kil�nek egy
 *  az � sz�n�vel megegyez� port�lt egy m�sik port�lfalra.
 */

public class PortalWall extends Location implements CellInterface {
	// Port�lfalhoz tartoz� k�k, illetve s�rga port�l referenci�i.
    private static PortalWall blue = null;
    private static PortalWall yellow = null;
    
    /* +PortalWall(Point): konstruktor, ami p�ld�nyos�tja a port�lfalat, �s
     * 				  	   be�ll�tja annak kezd�helyzet�t. Ez rendszerint a 
     * 					   p�lya beolvas�sakor h�v�dik meg.
    */
    public PortalWall(Point position) {
        this.setPosition(position);
    }
    /*  +isStepable(): fel�ldefini�lt f�ggv�ny l�phet�s�g lek�rdez�s�re.
     *  			   A port�l akkor l�phet�, ha nyitva van, egy�bk�nt nem
     *  			   enged �t semmit.
    */
    @Override
    public boolean isStepable() {
        Application.printCall(this, "-->isStepable()");

        if (blue == this && yellow != null) {
            Application.printCall(this, "<--");
            return true;
        } else if (yellow == this && blue != null) {
            Application.printCall(this, "<--");
            return true;
        }
        Application.printCall(this, "<--");
        return false;
    }
    /*  +onStepIn(Location): fel�ldefini�lt f�ggv�ny esem�ny kezel�s�re,
     * 					     amikor valami a port�lfalba ker�l. Ha a port�lba
     * 						 bel�pnek, �s van nyitva m�sik k�l�nb�z� sz�n�
     * 						 port�l is, akkor a t�rgy �tker�l a m�sik port�l
     * 						 bej�rat�hoz �s "kifel�" n�z.		     
     */
    @Override
    public void onStepIn(Location object) {
        Application.printCall(this, "-->onStepIn()");

        if (blue == this && yellow != null) {
            object.setPosition(yellow.getPosition());
            object.setDirection(yellow.getDirection());
        } else if (yellow == this && blue != null) {
            object.setPosition(blue.getPosition());
            object.setDirection(blue.getDirection());
        }
        Application.printCall(this, "<--");
    }
    /*  +onStepOut(): fel�ldefini�lt f�ggv�ny, a port�lb�l kil�p�s kezel�s�re.
     * 				  Nincs hat�ssal a j�t�kra, mivel a port�lba bel�p�skor
     * 				  egy�ttal lekezel�sre ker�l a kil�p�s is. 
     */
    @Override
    public void onStepOut() {
        Application.printCall(this, "-->onStepOut()");

        Application.printCall(this, "<--");
    }
    /*  +isShootable(): fel�ldefini�lt f�ggv�ny. Visszaadja, hogy l�het� -e 
     * 					a port�lfal a fel� kil�tt port�ll�ved�kkel. Term�szetesen
     * 					l�het� a port�lfal, teh�t a f�ggv�ny igazzal t�r vissza.
     */
    @Override
    public boolean isShootable() {
        Application.printCall(this, "-->isShootable()");

        Application.printCall(this, "<--");
        return true;
    }
    /*  +shoot(Player, Color): fel�ldefini�lt f�ggv�ny port�lnyit�s kezel�s�re.
     *  					   Nyit egy megfelel� sz�n� port�lt a falon, az el�z�
     *  					   azonos sz�n� port�lt meg is sz�nteti. Ezut�n lek�rdezi
     *  					   a j�t�kos �llapot�t �s beforgatja a kimeneti ir�nyba,
     *  					   hogy "j�fel�" n�zzen.
     */
    @Override
    public void shoot(Player player, Color color) {
        Application.printCall(this, "-->shoot()");

        if (color == Color.BLUE) {
            blue = this;
            System.out.println("Kekvagyok");
//            System.out.println(String.format("Kekvagyok: %s,%s", getPosition().x, getPosition().y));
        } else {
            yellow = this;
            System.out.println("Sargavagyok");
//            System.out.println(String.format("Sargavagyok: %s,%s", getPosition().x, getPosition().y));
        }

        Direction pdir = player.getDirection();

        switch (pdir) {
            case UP:
                setDirection(Direction.DOWN);
                break;
            case DOWN:
                setDirection(Direction.UP);
                break;
            case LEFT:
                setDirection(Direction.RIGHT);
                break;
            case RIGHT:
                setDirection(Direction.LEFT);
                break;
        }
        Application.printCall(this, "<--");
    }
    /*  +destroy(): fel�ldefini�lt f�ggv�ny a megsemmis�l�s lekezel�s�re.
     * 				Nincs hat�ssal a j�t�kra, mert a port�l "nem v�sz el,
     * 				csak �talakul".
     */
    @Override
    public void destroy() {
        Application.printCall(this, "-->destroy()");
        Application.printCall(this, "<--");
    }
}