package hu.gghf.model;

import hu.gghf.entities.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* +class Test
 * �n�ll� oszt�ly tesztel�si c�lokra, amelyet az Application haszn�l fel.
 * Seg�df�ggv�nyeket tartalmaz az egyes tesztesetek lebonyol�t�s�hoz,
 * illetve k�rd�seket is int�z a felhaszn�l�hoz, ha sz�ks�ges.
 */
public class Test {
	/*  Attrib�tumlista:
	 */
	
	//R�gz�tett p�lyaelemek egy-egy p�ld�nya (fal, �res j�r�lap, stb.).
    public static Wall wall;
    public static EmptyCell empty;
    public static Hole hole;
    public static PressurePlate pressurePlate;
    public static PortalWall portalWall;
    public static Door door;
    
    //Az aktu�lis p�lyaelem egy referenci�ja, ami meghivatkozhat�.
    public static CellInterface selected = null;

    //Cs�vezet�k olvas�shoz.
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    //J�t�kos attrib�tumainak reprezent�ci�i (cipelt doboz, ir�ny).
    public static Box carryBox;
    public static boolean iscarry = false;
    public static Location.Direction playerdir;

    /*  +static init(): inicializ�l� f�ggv�ny. P�ld�nyos�tja az egyes
     * 					reprezent�ci�s objektumokat �s elhelyez egy �j
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
    
    /*  +static setSelected(): az aktu�lis objektum be�ll�t�s�ra szolg�l� f�ggv�ny.
     * 						   A felhaszn�l�t�l kapja a be�ll�tand� objektum t�pus�t;
     * 						   al�bb l�that� a beolvas�s form�tuma.
    */
    public static void setSelected() {
        System.out.println("(1 - Ures mezo, 2 - Fal, 3 - Nyomolap, 4 - Szakad�k, 5 - Portal)");
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
    /*  +static setPlayerDir(): a j�t�kos ir�ny�t be�ll�t� f�ggv�ny. Hasonl�an
     * 							setSelected()-hez, bek�ri a felhaszn�l�t�l a
     * 							megfelel� ir�nyt �s a j�t�kosn�l be�ll�tja azt.
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

    /*  +static setIscarry(): be�ll�tja, hogy a j�t�kosn�l legyen -e doboz.
     * 						  K�rd�st int�z a felhaszn�l�hoz, aki az al�bb
     * 						  l�that� m�don v�laszol a k�rd�sre, �s ennek
     * 						  f�ggv�ny�ben be�ll�tja a cipel�s v�ltoz�j�t.
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
