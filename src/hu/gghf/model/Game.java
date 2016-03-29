package hu.gghf.model;

import hu.gghf.entities.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/* +class Game
 * Standard osztály, ami a játéktér elemeit tartalmazza és közvetlen vezérlésükre képes.
 * Ezek közé tartozik: pályaelem elhelyezése, mozgatása, lekérdezése.
 * Vezérlõfüggvényei kívülrõl hívódnak meg, rendszerint az Application osztályból.
 */
public class Game {
	/*  Attribútumlista:
	 *  -zpm_counter: tárolja a már megszerzett ZPM-modulok számát.
	 *  -map: 2 dimenziós tömb, a pályán található rögzített pályaelemeket tárolja.
	 *        Az egyes elemek helyzetét tömbkoordinátájával azonosítjuk.
	 *  -boxes: dinamikus tömb dobozok tárolására.
	 *  +maxsize: segédkonstans, ami lefixálja a pálya maximális méretét. A pályabeolvasást
	 *  		  segíti a feltöltõ Game() metódusnál. 
	 */ 
    private static int zpm_counter = 0;
    private CellInterface[][] map;
    private ArrayList<Box> boxes;
    public final int maxsize = 6;
    
    /*  #Game(): konstruktor. Példányosítja a tömbattribútumokat, majd feltölti adatokkal,
     * 			 a palya.csv fájl soronkénti kiolvasásával. A fájlt egy FileReaderen, azon
     * 			 túl is egy BufferedReaderen keresztül olvassa be. 
     */

    public Game() throws IOException {
    	Application.printCall(this, "Game()");
        map = new CellInterface[maxsize][maxsize];
        Application.printCall(this, "MAP()");
        boxes = new ArrayList<Box>();
        Application.printCall(this, "BOXES()");

        BufferedReader nf = new BufferedReader(new FileReader("palya.csv"));
       
        int i = 0; 
        //görgetve növelt ciklusváltozó, ami egyben az elem sorkoordinátája is.

        String line; 
        //szöveges változó egy pályasor tárolásához.
        
        /*  Fájlbeolvasás rutinja:
         *  - egy sort beolvasunk a line változóba;
         *  - a beolvasott sort határolók mentén széttördeljük elemekre;
         *  - egy oszlopváltozót folyamosan iterálva a maximum méretig,
         *    megvizsgáljuk a tömbelem elsõ karakterét.
         *  - az elsõ karakter értékétõl függõen:
         *     - ha 'x': falat olvastunk be;
         *     - ha '0': üres padlólapot olvastunk be;
         *     - ha 'p': portálfalat olvastunk be;
         *     - ha 'b': dobozt olvastunk be.
         *  - a rögzített pályaelemeket betesszük a map-ba. Doboz esetében 
         *    (mivel nem önállóan pályaelem), egy üres lapot teszünk be,
         *    miközben magát a dobozt is regisztráljuk a dobozok tömbjében.
         */
        while (((line = nf.readLine()) != null) || (i != maxsize)) {
            String[] params = line.split(";");
            for (int j = 0; j < maxsize; j++) {
                Point p = new Point(j,i);
                switch (params[j].charAt(0)) {
                    case 'x':
                        setMapObject(p, new Wall());
                        break;
                    case '0':
                        setMapObject(p, new EmptyCell());
                        break;
                    case 'p':
                        setMapObject(p, new PortalWall(p));
                        break;
                    case 'b':
                        Box box = new Box();
                        box.setPosition(p);
                        addBox(box);
                        setMapObject(p, new EmptyCell());
                        break;
                    default: break;
                }
            }
            i++;
        }
        nf.close();
    }
    
    /*  +getMapObject(Point): getter függvény. Lekérdezi a térkép egy adott pontján lévõ elem
     * 					 	  típusát.
     *  @param point: a lekérdezni kívánt pont koordinátapárja.
     *  @return CellInterface: a lekérdezett pont típusa.  
     */
    public CellInterface getMapObject(Point point) {
        Application.printCall(this, "-->getMapObject()");

        Application.printCall(this, "<--");
        return Test.selected;
    }
    
    /*  +setMapObject(Point,CellInterface): setter függvény. Elhelyez a térkép egy megadott pontján egy
     * 					 					tetszõleges rögzített pályaelemet.
     *  @param point: a módosítandó pont koordinátapárja.
     *  @param cell: az elhelyezendõ pályaelem típusa.
     */
    public void setMapObject(Point point, CellInterface cell) {
        Application.printCall(this, String.format("-->setMapObject(%s,%s)", point, cell.getClass().getSimpleName()));
        map[point.y][point.x] = cell;
        Application.printCall(this, "<--");
    }
    
    /*  +isBox(Point): getter függvény. Megnézi, hogy a térkép egy adott pontján doboz áll -e.
     *  A szkeletonban nem jarja be a dobozokat, mert a doboz a valasz alapjan adodik vissza.
     *  @param point: a lekérdezni kívánt pont koordinátapárja.
     *  @return boolean: igaz, ha doboz áll a lekérdezett ponton, egyébként hamis.
     */
    public boolean isBox(Point point) {
        System.out.println("i/n");
        boolean ret = false;
        try {
            ret = (Test.br.readLine().equals("i"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Application.printCall(this, "-->isBox()");
        Test.carryBox.getPosition();
        Application.printCall(this, "<--");
        return ret;
    }
    
    /*  +getBox(Point): getter függvény. Lekérdezi és visszaadja a pálya megadott pontján lévõ doboz referenciáját.
     * 		            Hasonlít isBox-hoz, viszont itt a visszatérés referencia.
     *  @param point: a lekérdezni kívánt pont koordinátapárja.
     *  @return Box: a megtalált doboz referenciája. Ha nincs doboz, akkor null.
     */
    public Box getBox(Point point) {
        System.out.println("i/n");
        boolean ih = false;
        try {
            ih = Test.br.readLine().equals("i");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Application.printCall(this, "-->getBox()");
        if (ih) {
            Test.carryBox.getPosition();
            Application.printCall(this, "<--");
            return Test.carryBox;
        }
        Application.printCall(this, "<--");
        return null;
    }
    
    /*  +addBox(Box): hozzáad egy tetszõleges dobozt a dobozok listájához.
     *  @param box: a hozzáadandó doboz referenciája.
     */
    public void addBox(Box box) {
        Application.printCall(this, "-->addBox()");

        boxes.add(box);
        Application.printCall(this, "<--");
    }
    
    /*  +addZPM(Box): növeli a megtalált ZPM-ek számát 1-gyel.
     */    
    public void addZPM() {
        Application.printCall(this, "-->addZPM()");
        zpm_counter += 1;
        Application.printCall(this, "<--");
    }
    
    /*  +getZpmCount(): lekérdezi a játékosnál lévõ ZPM-ek számát.
     *  @return int: a játékos ZPM-jeinek száma.
     */
    public int getZpmCount() {
        Application.printCall(this, "-->getZpmCount()");
        Application.printCall(this, "<--");
        return zpm_counter;
    }
}
