package hu.gghf.model;

import hu.gghf.entities.*;

import java.awt.*;
import java.io.IOException;

/* +class Controller
 * L�tfontoss�g� oszt�ly, ami a j�t�k lebonyol�t�s��rt felel�s. R�l�t�sa van mind
 * a j�t�kt�rre, mind a j�t�kosra; ez ut�bbit akt�van vez�rli is.
 * L�trehoz�skor elhelyezi a j�t�kost a p�ly�n, m�g egy�bk�nt vez�rli �t parancsokkal.
 */
public class Controller {
	/*  Attrib�tumlista:
	 *  -player: referencia a j�t�koskarakterre.
	 *  -game: referencia a j�t�kt�rre. 
	 */
    private Player player;
    private Game game;
    
    /*  +Controller(): konstruktor. P�ld�nyos�tja a j�t�kot �s a j�t�kost, mikor is
     * 				   egy �j j�t�k indul �s a j�t�kos is elfoglalja kezd�poz�ci�j�t a p�ly�n.
     */
    public Controller() {
        Application.printCall(this, "-->Controller()");

        try {
            game = new Game();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player = new Player(game);
        player.setPosition(new Point(2,2));

        Application.printCall(this, "<--");
    }

    /*  +openPortal(CellInterface.Color): kil� egy port�ll�ved�ket megadott sz�nnel, a j�t�kos ir�ny�b�l.
     * 									  Megkeresi a landol�s hely�t, majd az ott l�v� objektum "l�v�s"-
     * 									  -interakci�j�t megh�vja.
     *  @param type: a kil�tt port�l sz�ne (s�rga vagy k�k). 
     */
    public void openPortal(CellInterface.Color type) {
        Application.printCall(this, String.format("-->openportal(%s)", type));

        CellInterface target = player.findTarget();

        if (target != null)
            target.shoot(player, type);

        Application.printCall(this, "<--");
    }
    
    /*  +dropBox(): a j�t�kost a n�la l�v� doboz elejt�s�re k�nyszer�ti.
     * 				Egyszer�en kinull�zza a doboz referenci�j�t. 
     */
    public void dropBox() {
        Application.printCall(this, "-->dropBox()");
        player.setCarry(null);
        Application.printCall(this, "<--");
    }

    /*  +pickUpBox(): megn�zi, milyen t�rgy van a j�t�kos el�tt, majd felveszi,
     * 				  ha az doboz.  
     */
    public void pickUpBox() {
        Application.printCall(this, "-->pickUpBox()");

        Location.Direction dir = player.getDirection();
        Point frontpos = player.posInDirection(dir, 1);

        System.out.println("?Van elottunk doboz?");
        boolean isbox = game.isBox(frontpos);

        if (isbox) {
            System.out.println("?Adjunk referenciat az elottunk levo dobozrol (nem eseten null)?");
            Box box = game.getBox(frontpos);
            player.setCarry(box);
        }
        Application.printCall(this, "<--");
    }
    
    /*  +movePlayer(Location.Direction): a j�t�kost mozgat� f�ggv�ny. Ha a j�t�kos eredetileg nem 
     * 									 a megadott ir�nyba n�z, akkor elfordul az �j ir�nyba,
     * 									 egy�bk�nt elmozdul egy mez�t saj�t ir�ny�ba.
     *  @param direction: az ir�ny, amerre a j�t�kos elfordul.
     */
    public void movePlayer(Location.Direction direction) {
        Application.printCall(this, String.format("-->movePlayer(%s)", direction));

        Location.Direction playerDir = player.getDirection();

        if (playerDir == direction) {
            player.goForward();
        } else {
            player.turn(direction);
        }
        Application.printCall(this, "<--");
    }
}
