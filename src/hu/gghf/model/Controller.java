package hu.gghf.model;

import hu.gghf.entities.*;

import java.awt.*;
import java.io.IOException;

/* +class Controller
 * Létfontosságú osztály, ami a játék lebonyolításáért felelõs. Rálátása van mind
 * a játéktérre, mind a játékosra; ez utóbbit aktívan vezérli is.
 * Létrehozáskor elhelyezi a játékost a pályán, míg egyébként vezérli õt parancsokkal.
 */
public class Controller {
	/*  Attribútumlista:
	 *  -player: referencia a játékoskarakterre.
	 *  -game: referencia a játéktérre. 
	 */
    private Player player;
    private Game game;
    
    /*  +Controller(): konstruktor. Példányosítja a játékot és a játékost, mikor is
     * 				   egy új játék indul és a játékos is elfoglalja kezdõpozícióját a pályán.
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
    
    /*  +printmap(): segédfüggvény, ami kirajzolja a játékteret. Célja az
     * 				 önellenõrzés, amivel a tesztelõ és a felhasználó is
     * 				 részletes képet kaphat a játéktér állapotáról.
     */
    public void printmap() {
        Point playerpos = player.getPosition();
    /*  A beolvasás menete:
     *  - végigmegyünk a játéktér összes elemén két ciklussal, és sorban lekérdezzük
     *    az adott helyen álló objektumokat.
     *  - egy "sor" karakterfüzérbe folyamatosan addjuk hozzá a beolvasott elemeket, majd
     *    az egész sort kiíratjuk minden sorvéggel.
     *  - az egyes beolvasott objektumok jelölése:
     *     '^','V','>','<': a játékos (négy irányba);
     *     '0': üres járólap;
     *     'x': fal;
     *     'b': doboz;
     *     'p': portálfal;
     *     'u': egyéb, ismeretlen objektum.
     */
        for (int i = 0; i < game.maxsize; i++) {
            String row = "";
            Point point;
            for (int k = 0; k < game.maxsize; k++) {
                point = new Point(k, i);
                CellInterface obj = game.getMapObject(point);

                if (playerpos.equals(point)) {
                    switch (player.getDirection()) {
                        case UP:
                            row += "^";
                            break;
                        case DOWN:
                            row += "V";
                            break;
                        case LEFT:
                            row += "<";
                            break;
                        case RIGHT:
                            row += ">";
                            break;
                        default:
                            row += "_";
                            break;
                    }
                }
                else if (game.isBox(point)) {
                    row += "b";
                }
                else if (obj.getClass() == Wall.class) {
                    row += "x";
                }
                else if (obj.getClass() == EmptyCell.class) {
                    row += "0";
                }
                else if (obj.getClass() == PortalWall.class) {
                    row += "p";
                }
                else {
                    row += "u";
                }
            }
            System.out.println(row);
        }
    }

    /*  +openPortal(CellInterface.Color): kilõ egy portállövedéket megadott színnel, a játékos irányából.
     * 									  Megkeresi a landolás helyét, majd az ott lévõ objektum "lövés"-
     * 									  -interakcióját meghívja.
     *  @param type: a kilõtt portál színe (sárga vagy kék). 
     */
    public void openPortal(CellInterface.Color type) {
        Application.printCall(this, String.format("-->openportal(%s)", type));

        CellInterface target = player.findTarget();

        if (target != null)
            target.shoot(player, type);

        Application.printCall(this, "<--");
    }
    
    /*  +dropBox(): a játékost a nála lévõ doboz elejtésére kényszeríti.
     * 				Egyszerûen kinullázza a doboz referenciáját. 
     */
    public void dropBox() {
        Application.printCall(this, "-->dropBox()");
        System.out.println();
        player.setCarry(null);
        Application.printCall(this, "<--");

    }

    /*  +pickUpBox(): megnézi, milyen tárgy van a játékos elõtt, majd felveszi,
     * 				  ha az doboz.  
     */
    public void pickUpBox() {
        Application.printCall(this, "-->pickUpBox()");

        Location.Direction dir = player.getDirection();
        Point frontpos = player.posInDirection(dir, 1);

        boolean isbox = game.isBox(frontpos);

        if (isbox) {
            Box box = game.getBox(frontpos);
            player.setCarry(box);
        }
        Application.printCall(this, "<--");
    }
    
    /*  +movePlayer(Location.Direction): a játékost mozgató függvény. Ha a játékos eredetileg nem 
     * 									 a megadott irányba néz, akkor elfordul az új irányba,
     * 									 egyébként elmozdul egy mezõt saját irányába.
     *  @param direction: az irány, amerre a játékos elfordul.
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
