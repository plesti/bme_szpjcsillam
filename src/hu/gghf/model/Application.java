package hu.gghf.model;

import hu.gghf.entities.CellInterface;
import hu.gghf.entities.Location;

import java.io.IOException;

/* +class Application
 * Alkalmaz�soszt�ly, amely a szkeletont val�s�tja meg. A program a main f�ggv�nyben fut,
 * amely parancsokkal vez�relhet� teszteseteket bocs�t a felhaszn�l� rendelkez�s�re. 
 * A nem egy�rtelm� esetekn�l k�rd�seket int�z a userhez, akinek ilyenkor
 * t�bb lehet�s�ge ny�lik egy-egy aleset vizsg�lat�hoz.
 * A tesztel�s sor�n ki�r�dik minden megh�vott objektum f�ggv�nyh�v�sa �s visszat�r�se.
 * --> jel�li a h�v�st, <-- a visszat�r�st.
 */

public class Application {
	/*  Attrib�tumlista:
	 *  -static debug: kapcsol�, amivel �ll�that� a debug ki�rat�si �zemm�d.
	 *  +static levelcounter: eg�sz, amivel a ki�rat�s beh�z�si szintje van nyilv�ntartva.
	 */
	
    private static boolean debug = false;
    /*  +static main(String[]): a program f� f�ggv�nye, ami megh�v�dik minden indul�sn�l.
     * 							Tartalmazza a parancssori kezel�fel�letet �s kiadja azok
     * 							egyes v�grehajt�si parancsait (megfelel� esetben).
     *  @param args: a program parancssori argumentumai.
     */
    
    public static void main(String[] args) {
        Controller controller = null;
        
        // V�ltoz� a kil�p�s figyel�s�re. Igaz, ha kiadtak kil�p�si parancsot.
        boolean exit = false;
        
        /*  Ha nincs kil�p�s, a program felaj�nlja a lehets�ges tesztesetek tesztel�s�t.
         *  A z�r�jelben megadott sztringekkel vez�relhet� a program, egyszerre csak
         *  egy parancs adhat� ki.
         *  A debug �zemm�d inakt�v lesz �s l�trej�n egy tesztk�rnyezet.
         */
        while (!exit) {
            System.out.println("_____________");
            System.out.println("Add meg interakcionak megfelelo parancsot!");
            System.out.println("Uj jatek (start)");
            System.out.println("Mozgas fel (u)");
            System.out.println("Mozgas Le (d)");
            System.out.println("Mozgas balra (l)");
            System.out.println("Mozgas jobbra (r)");
            System.out.println("Doboz felemeles (p)");
            System.out.println("Doboz letetel (pd)");
            System.out.println("Kilepes (q)");
        
            debug = false;
            Test.init();

            if (controller != null) {
//                controller.printmap();
            }
            /* Itt v�r parancsra a program. A standard inputon �rkez�
             * sz�veget �rt�keli ki az al�bbi (t�bbsz�r�sen egym�sba
             * �gyazott) felt�teles el�gaz�s.
             */
            
            debug = true;
            System.out.println("_____________");
            try {
                String s = Test.br.readLine();
                if (s.equals("start")) {
                    controller = new Controller();
                }
                else if (s.equals("u")) {
                    if (controller != null) {
                        controller.movePlayer(Location.Direction.UP);
                    }
                } else if (s.equals("d")) {
                    if (controller != null) {
                        controller.movePlayer(Location.Direction.DOWN);
                    }
                }
                else if (s.equals("l")) {
                    if (controller != null) {
                        controller.movePlayer(Location.Direction.LEFT);
                    }
                }
                else if (s.equals("r")) {
                    if (controller != null) {
                        controller.movePlayer(Location.Direction.RIGHT);
                    }
                }
                else if (s.equals("sb")) {
                    if (controller != null) {
                        controller.openPortal(CellInterface.Color.BLUE);
                    }
                }
                else if (s.equals("sy")) {
                    if (controller != null) {
                        controller.openPortal(CellInterface.Color.YELLOW);
                    }
                }
                else if (s.equals("p")) {
                    if (controller != null) {
                        controller.pickUpBox();
                    }
                }
                else if (s.equals("pd")) {
                    if (controller != null) {
                        controller.dropBox();
                    }
                }
                else if (s.equals("q")) {
                    exit = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /*  +static printCall(Object, String): ki�rat� f�ggv�ny, amit a szkeleton met�dusai haszn�lnak 
     * 									   megh�v�skor �s visszat�r�skor. Korl�tozottan testre-
     * 									   szabott a ki�rat�s form�tuma; a f�ggv�ny �gyel a beh�-
     * 									   z�sokra is. Csak debug �zemm�dban m�k�dik. 
     *  @param obj: az akt�v objektum referenci�ja.
     *  @param msg: a ki�rand� �zenet (szubrutinn�v).
     */    
    public static int levelcounter = 0;
    public static void printCall(Object obj, String msg) {
    	/* A ki�rat�s alapelve, hogy mindig az interakci�ban l�v� objektum �s annak
    	 * f�ggv�nyn�ve �r�dik ki.
    	 * Ha �j objektum h�v�sa t�rt�nik, akkor a beh�z�s szintje 1-gyel n�vekszik,
    	 * ha az visszat�r, akkor a beh�z�s 1-gyel cs�kken.
    	 * A jobb oldali beh�z�s egy tabul�torkarakterrel t�rt�nik.
    	 */
        if (debug) {
            String output = String.format(" %s (%s)", msg, obj.getClass().getSimpleName());

            if (msg.startsWith("<--")) {
                levelcounter--;
            }
            for (int i = 0; i < levelcounter; i++) {
                System.out.print("\t");
            }
            System.out.println(output);
            if (msg.startsWith("-->")) {
                levelcounter++;
            }
        }
    }
}
