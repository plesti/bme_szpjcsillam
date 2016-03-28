package hu.gghf.model;

import hu.gghf.entities.CellInterface;
import hu.gghf.entities.Location;

import java.io.IOException;

/* +class Application
 * Alkalmazásosztály, amely a szkeletont valósítja meg. A program a main függvényben fut,
 * amely parancsokkal vezérelhetõ teszteseteket bocsát a felhasználó rendelkezésére. 
 * A nem egyértelmû eseteknél kérdéseket intéz a userhez, akinek ilyenkor
 * több lehetõsége nyílik egy-egy aleset vizsgálatához.
 * A tesztelés során kiíródik minden meghívott objektum függvényhívása és visszatérése.
 * --> jelöli a hívást, <-- a visszatérést.
 */

public class Application {
	/*  Attribútumlista:
	 *  -static debug: kapcsoló, amivel állítható a debug kiíratási üzemmód.
	 *  +static levelcounter: egész, amivel a kiíratás behúzási szintje van nyilvántartva.
	 */
	
    private static boolean debug = false;
    /*  +static main(String[]): a program fõ függvénye, ami meghívódik minden indulásnál.
     * 							Tartalmazza a parancssori kezelõfelületet és kiadja azok
     * 							egyes végrehajtási parancsait (megfelelõ esetben).
     *  @param args: a program parancssori argumentumai.
     */
    
    public static void main(String[] args) {
        Controller controller = null;
        
        // Változó a kilépés figyelésére. Igaz, ha kiadtak kilépési parancsot.
        boolean exit = false;
        
        /*  Ha nincs kilépés, a program felajánlja a lehetséges tesztesetek tesztelését.
         *  A zárójelben megadott sztringekkel vezérelhetõ a program, egyszerre csak
         *  egy parancs adható ki.
         *  A debug üzemmód inaktív lesz és létrejön egy tesztkörnyezet.
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
            /* Itt vár parancsra a program. A standard inputon érkezõ
             * szöveget értékeli ki az alábbi (többszörösen egymásba
             * ágyazott) feltételes elágazás.
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
    
    /*  +static printCall(Object, String): kiírató függvény, amit a szkeleton metódusai használnak 
     * 									   meghíváskor és visszatéréskor. Korlátozottan testre-
     * 									   szabott a kiíratás formátuma; a függvény ügyel a behú-
     * 									   zásokra is. Csak debug üzemmódban mûködik. 
     *  @param obj: az aktív objektum referenciája.
     *  @param msg: a kiírandó üzenet (szubrutinnév).
     */    
    public static int levelcounter = 0;
    public static void printCall(Object obj, String msg) {
    	/* A kiíratás alapelve, hogy mindig az interakcióban lévõ objektum és annak
    	 * függvénynéve íródik ki.
    	 * Ha új objektum hívása történik, akkor a behúzás szintje 1-gyel növekszik,
    	 * ha az visszatér, akkor a behúzás 1-gyel csökken.
    	 * A jobb oldali behúzás egy tabulátorkarakterrel történik.
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
