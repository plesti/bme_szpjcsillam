package hu.gghf.model;

import hu.gghf.entities.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/* +class Game
 * Standard oszt�ly, ami a j�t�kt�r elemeit tartalmazza �s k�zvetlen vez�rl�s�kre k�pes.
 * Ezek k�z� tartozik: p�lyaelem elhelyez�se, mozgat�sa, lek�rdez�se.
 * Vez�rl�f�ggv�nyei k�v�lr�l h�v�dnak meg, rendszerint az Application oszt�lyb�l.
 */
public class Game {
	/*  Attrib�tumlista:
	 *  -zpm_counter: t�rolja a m�r megszerzett ZPM-modulok sz�m�t.
	 *  -map: 2 dimenzi�s t�mb, a p�ly�n tal�lhat� r�gz�tett p�lyaelemeket t�rolja.
	 *        Az egyes elemek helyzet�t t�mbkoordin�t�j�val azonos�tjuk.
	 *  -boxes: dinamikus t�mb dobozok t�rol�s�ra.
	 *  +maxsize: seg�dkonstans, ami lefix�lja a p�lya maxim�lis m�ret�t. A p�lyabeolvas�st
	 *  		  seg�ti a felt�lt� Game() met�dusn�l. 
	 */ 
    private static int zpm_counter = 0;
    private CellInterface[][] map;
    private ArrayList<Box> boxes;
    public final int maxsize = 6;
    
    /*  #Game(): konstruktor. P�ld�nyos�tja a t�mbattrib�tumokat, majd felt�lti adatokkal,
     * 			 a palya.csv f�jl soronk�nti kiolvas�s�val. A f�jlt egy FileReaderen, azon
     * 			 t�l is egy BufferedReaderen kereszt�l olvassa be. 
     */

    public Game() throws IOException {
    	Application.printCall(this, "Game()");
        map = new CellInterface[maxsize][maxsize];
        Application.printCall(this, "MAP()");
        boxes = new ArrayList<Box>();
        Application.printCall(this, "BOXES()");

        BufferedReader nf = new BufferedReader(new FileReader("palya.csv"));
       
        int i = 0; 
        //g�rgetve n�velt ciklusv�ltoz�, ami egyben az elem sorkoordin�t�ja is.

        String line; 
        //sz�veges v�ltoz� egy p�lyasor t�rol�s�hoz.
        
        /*  F�jlbeolvas�s rutinja:
         *  - egy sort beolvasunk a line v�ltoz�ba;
         *  - a beolvasott sort hat�rol�k ment�n sz�tt�rdelj�k elemekre;
         *  - egy oszlopv�ltoz�t folyamosan iter�lva a maximum m�retig,
         *    megvizsg�ljuk a t�mbelem els� karakter�t.
         *  - az els� karakter �rt�k�t�l f�gg�en:
         *     - ha 'x': falat olvastunk be;
         *     - ha '0': �res padl�lapot olvastunk be;
         *     - ha 'p': port�lfalat olvastunk be;
         *     - ha 'b': dobozt olvastunk be.
         *  - a r�gz�tett p�lyaelemeket betessz�k a map-ba. Doboz eset�ben 
         *    (mivel nem �n�ll�an p�lyaelem), egy �res lapot tesz�nk be,
         *    mik�zben mag�t a dobozt is regisztr�ljuk a dobozok t�mbj�ben.
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
    
    /*  +getMapObject(Point): getter f�ggv�ny. Lek�rdezi a t�rk�p egy adott pontj�n l�v� elem
     * 					 	  t�pus�t.
     *  @param point: a lek�rdezni k�v�nt pont koordin�tap�rja.
     *  @return CellInterface: a lek�rdezett pont t�pusa.  
     */
    public CellInterface getMapObject(Point point) {
        Application.printCall(this, "-->getMapObject()");

        Application.printCall(this, "<--");
        return Test.selected;
    }
    
    /*  +setMapObject(Point,CellInterface): setter f�ggv�ny. Elhelyez a t�rk�p egy megadott pontj�n egy
     * 					 					tetsz�leges r�gz�tett p�lyaelemet.
     *  @param point: a m�dos�tand� pont koordin�tap�rja.
     *  @param cell: az elhelyezend� p�lyaelem t�pusa.
     */
    public void setMapObject(Point point, CellInterface cell) {
        Application.printCall(this, String.format("-->setMapObject(%s,%s)", point, cell.getClass().getSimpleName()));
        map[point.y][point.x] = cell;
        Application.printCall(this, "<--");
    }
    
    /*  +isBox(Point): getter f�ggv�ny. Megn�zi, hogy a t�rk�p egy adott pontj�n doboz �ll -e.
     *  A szkeletonban nem jarja be a dobozokat, mert a doboz a valasz alapjan adodik vissza.
     *  @param point: a lek�rdezni k�v�nt pont koordin�tap�rja.
     *  @return boolean: igaz, ha doboz �ll a lek�rdezett ponton, egy�bk�nt hamis.
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
    
    /*  +getBox(Point): getter f�ggv�ny. Lek�rdezi �s visszaadja a p�lya megadott pontj�n l�v� doboz referenci�j�t.
     * 		            Hasonl�t isBox-hoz, viszont itt a visszat�r�s referencia.
     *  @param point: a lek�rdezni k�v�nt pont koordin�tap�rja.
     *  @return Box: a megtal�lt doboz referenci�ja. Ha nincs doboz, akkor null.
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
    
    /*  +addBox(Box): hozz�ad egy tetsz�leges dobozt a dobozok list�j�hoz.
     *  @param box: a hozz�adand� doboz referenci�ja.
     */
    public void addBox(Box box) {
        Application.printCall(this, "-->addBox()");

        boxes.add(box);
        Application.printCall(this, "<--");
    }
    
    /*  +addZPM(Box): n�veli a megtal�lt ZPM-ek sz�m�t 1-gyel.
     */    
    public void addZPM() {
        Application.printCall(this, "-->addZPM()");
        zpm_counter += 1;
        Application.printCall(this, "<--");
    }
    
    /*  +getZpmCount(): lek�rdezi a j�t�kosn�l l�v� ZPM-ek sz�m�t.
     *  @return int: a j�t�kos ZPM-jeinek sz�ma.
     */
    public int getZpmCount() {
        Application.printCall(this, "-->getZpmCount()");
        Application.printCall(this, "<--");
        return zpm_counter;
    }
}
