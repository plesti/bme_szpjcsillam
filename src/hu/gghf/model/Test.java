package hu.gghf.model;

import hu.gghf.entities.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* +class Test
 * Önálló osztály tesztelési célokra, amelyet az Application használ fel.
 * Segédfüggvényeket tartalmaz az egyes tesztesetek lebonyolításához,
 * illetve kérdéseket is intéz a felhasználóhoz, ha szükséges.
 */
public class Test {
	/*  Attribútumlista:
	 */
	
	//Rögzített pályaelemek egy-egy példánya (fal, üres járólap, stb.).
    public static Wall wall;
    public static EmptyCell empty;
    public static Hole hole;
    public static PressurePlate pressurePlate;
    public static PortalWall portalWall;
    public static Door door;
    
    //Az aktuális pályaelem egy referenciája, ami meghivatkozható.
    public static CellInterface selected = null;

    //Csõvezeték olvasáshoz.
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    //Játékos attribútumainak reprezentációi (cipelt doboz, irány).
    public static Box carryBox;
    public static boolean iscarry = false;
    public static Location.Direction playerdir;

    /*  +static init(): inicializáló függvény. Példányosítja az egyes
     * 					reprezentációs objektumokat és elhelyez egy új
     * 					dobozt is.
    */
    public static void init() {
        wall = new Wall();
        empty = new EmptyCell();
        hole = new Hole();
        pressurePlate = new PressurePlate(door);
        portalWall = new PortalWall(new Point(1,2));
        door = new Door();

        carryBox = new Box();
        carryBox.setPosition(new Point(1,1));
        playerdir = null;
    }
    
    /*  +static setSelected(): az aktuális objektum beállítására szolgáló függvény.
     * 						   A felhasználótól kapja a beállítandó objektum típusát;
     * 						   alább látható a beolvasás formátuma.
    */
    public static void setSelected() {
        System.out.println("(1 - Ures mezo, 2 - Fal, 3 - Nyomolap, 4 - Szakadék, 5 - Portal)");
        try {
            String c = br.readLine();
            if (c.equals("1")) {
                Test.selected = Test.empty;
            }
            else if (c.equals("2")) {
                Test.selected = Test.wall;
            }
            else if (c.equals("3")) {
                Test.selected = Test.pressurePlate;
            }
            else if (c.equals("4")) {
                Test.selected = Test.hole;
            }
            else if (c.equals("5")) {
                Test.selected = Test.portalWall;
            }
            else {
                setSelected();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*  +static setPlayerDir(): a játékos irányát beállító függvény. Hasonlóan
     * 							setSelected()-hez, bekéri a felhasználótól a
     * 							megfelelõ irányt és a játékosnál beállítja azt.
    */
    public static void setPlayerDir() {
        System.out.println("?Merrefele nez a jatekos?");
        System.out.println("(1 - Fel, 2 - Le, 3 - Balra, 4 - Jobbra)");
        try {
            String c = br.readLine();
            if (c.equals("1")) {
                Test.playerdir = Location.Direction.UP;
            }
            else if (c.equals("2")) {
                Test.playerdir = Location.Direction.DOWN;
            }
            else if (c.equals("3")) {
                Test.playerdir = Location.Direction.LEFT;
            }
            else if (c.equals("4")) {
                Test.playerdir = Location.Direction.RIGHT;
            }
            else {
                setPlayerDir();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*  +static setIscarry(): beállítja, hogy a játékosnál legyen -e doboz.
     * 						  Kérdést intéz a felhasználóhoz, aki az alább
     * 						  látható módon válaszol a kérdésre, és ennek
     * 						  függvényében beállítja a cipelés változóját.
    */
    public static void setIscarry() {
        System.out.println("?Visz a jatekos dobozt? i/n");
        try {
            iscarry = (Test.br.readLine().equals("i"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
