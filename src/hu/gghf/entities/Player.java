package hu.gghf.entities;

import hu.gghf.model.Application;
import hu.gghf.model.Game;
import hu.gghf.model.Test;

import java.awt.*;
import java.io.IOException;
/*  +class Player : CellInterface
 *  Játékos pályaelem megvalósítása, az egyik legfõbb osztály.
 *  Olyan objektum, ami képes doboz cipelésére és ZPM-ek össze-
 *  gyûjtésére, továbbá önállóan forogni és mozogni a játéktéren.
 *  A játékos 4 ZPM összegyûjtésével tudja megnyerni a játékot,
 *  de ha szakadékba kerül, akkor meghal és a játéknak vereséggel
 *  vége szakad. 
 */
public class Player extends Location {
	/*  Attribútumlista:
	 * 	-carryObject: doboz objektum refenciája, amit a játékos magánál tart.
	 *  -game: belsõ referencia a játékmenetre.
	 */
    private Box carryObject;
    private Game game;
    /* +Player(Game): konstruktor, ami elvégzi a belsõ játékreferencia
     * 				  példányosítását.
     * @param game: a példányosító játékmenet referenciája.
    */
    public Player(Game game) {
        this.game = game;
        this.carryObject = null;
    }
    /*  +destroy(): felüldefiniált függvény. Megsemmisüléskor a játékos
	 *  			meghal; a szkeletonban egyedi reprezentációja
	 *  			nincs. 
	 */
    @Override
    public void destroy() {
        Application.printCall(this, "-->destroy()");
        Application.printCall(this, "<--");
    }
    /*  +setCarry(Box): setter függvény, ami a játékoshoz "láncolja" a
     * 					megadott dobozt, miáltal a játékos cipelni és
     * 					vele együtt forogni tud.
     *  @param box: a játékoshoz hozzárendelt doboz referenciája.
	 */
    public void setCarry(Box box) {
        Application.printCall(this, "-->setCarry()");
        carryObject = box;
        Application.printCall(this, "<--");
    }
    /*  +getCarry(Box): getter függvény, ami a játékosnál lévõ dobozt
     * 					kérdezi le.
     *  @return: a játékoshoz hozzárendelt doboz referenciája.
	 */
    public Box getCarry() {
        Application.printCall(this, "-->getCarry()");

        Application.printCall(this, "<--");
        return Test.carryBox;
    }
    /*  +isCarry(): lekérdezõ függvény, ami visszaadja, hogy a játékosnál
     * 				   található -e doboz a lekérdezés pillanatában.
     *  @return: igaz, ha található nála doboz, egyébként hamis.
	 */
    public boolean isCarry() {
        Application.printCall(this, "-->isCarry()");

        Application.printCall(this, "<--");
        return Test.iscarry;
    }
    /*  +posInDirection(Direction,int): segédfüggvény, amivel lekérdezhetõ egy adott ponttól
     * 									megadott távolságra lévõ pont koordinátája, megadott
     * 									irányban (a 4 égtáj valamelyikében).
     *  @return: a kérdéses helyen lévõ pont koordinátája.
	 */

    public Point posInDirection(Direction direction, int distance) {
        Application.printCall(this, "-->posInDirection()");

        Point pos = getPosition();
        int x = pos.x, y = pos.y;

        switch (direction) {
            case UP:
                y -= distance;
                break;
            case DOWN:
                y += distance;
                break;
            case LEFT:
                x -= distance;
                break;
            case RIGHT:
                x += distance;
                break;
        }
        Application.printCall(this, "<--");
        return new Point(x, y);
    }
    /*  +findTarget(): ezzel a függvénnyel deríthetõ ki, hogy egy portállövedék
     * 				   célpontja milyen, és hol található.
     *  @return: a megtalált rögzített pályaelem referenciája.
	 */
    public CellInterface findTarget() {
        Application.printCall(this, "-->findTarget()");

        //A játékos irányának megfelelõen kell utat keresni.
        Direction dir = getDirection();
        //Az aktuális célpont referenciája.
        CellInterface target;

        /*  A célkeresõ folyamat elindul a játékostól, a saját irányában,
         *  és folyamatosan növeli a játékostól való távolságát, miközben
         *  lekérdezi az ott található tárgyak lõhetõségét.
         *  Amint lõhetõ tárgyat talál, visszatér annak referenciájával,
         *  egyébként nullal. 
         */ 
        for (int i = 1; i < 8; i++) {
            Point pos = posInDirection(dir, i);

            System.out.println(String.format("?Milyen objektum legyen a jatekos elott %s tavolsagban?", i));
            Test.setSelected();
            target = game.getMapObject(pos);

            boolean isshootable = target.isShootable();
            if (isshootable) {
                Application.printCall(this, "<-- target");
                return target;
            }
        }
        Application.printCall(this, "<--");
        return null;
    }
    /*  +goForward(): mozgató függvény, a játékost egy lépéssel elõrefele
     * 				  mozdítja. Ha a játékos szabályosan nem tud mozogni,
     * 				  akkor helyben marad.  
	 */
    public void goForward() {
        Application.printCall(this, "-->goForward()");
        
        //Ismerni kell a játékos aktuális és 1-gyel elõtte levõ pozícióját.
        Location.Direction dir = getDirection();
        Point frontpos = posInDirection(dir, 1);
        Point currentpos = getPosition();

        //Fontos, hogy milyen objektum van a játékos elõtt.
        System.out.println("?Milyen objektum legyen a jatekos elott?");
        Test.setSelected();
        CellInterface frontcell = game.getMapObject(frontpos);

        Test.selected = Test.empty;
        CellInterface currentcell = game.getMapObject(currentpos);

        boolean isstepable = frontcell.isStepable();
        /*  Ebben az elágazásban történik a léphetõség kiértékelése.
         *  A lépések egyes esetei:
         *  - ha a játékos vagy az elõtte levõ doboz nem tud elõrelépni,
         *    akkor a játékos helyben marad;
         *  - ha a játékos elõtt doboz van és azt léphetõbe tudja tolni,
         *    akkor azzal együtt egy lépést elõremegy;
         *  - a portálba helyezett tárgy/játékos automatikusan áthelyezõdik.
         *  Mindig meghívódik a rögz. pályaelemre lépés és arról lelépés eseménye
         *  is a megfelelõ celláknál.
         */
        
        if (isstepable) {
            Test.setIscarry();
            boolean iscarry = isCarry();

            System.out.println("?Adjunk referenciat az elottunk levo dobozrol (nem eseten null)?");
            Box getbox = game.getBox(frontpos);

            if (iscarry) {
                Point frontpos2 = posInDirection(dir, 2);

                System.out.println("?Milyen objektum legyen a kettovel jatekos elott?");
                Test.setSelected();

                CellInterface frontcell2 = game.getMapObject(frontpos2);

                System.out.println("?Adjunk referenciat az kettovel elottunk levo dobozrol (nem eseten null)?");
                Box getbox2 = game.getBox(frontpos2);

                // Ha nincs elõtte doboz és kettõvel elõtte üres:
                if (frontcell2.isStepable() && getbox2 == null) {
                    currentcell.onStepOut();
                    frontcell.onStepOut();

                    // Player elõrelép:
                    setPosition(frontpos);
                    frontcell.onStepIn(this);

                    // Ha portálba lép a játékos, akkor a doboznak elé kell kerulnie:
                    Point newfront = posInDirection(dir, 1);

                    Box box = getCarry();
                    box.setPosition(newfront);
                    frontcell2.onStepIn(box);
                }

            } else if (!iscarry && getbox == null) {
                currentcell.onStepOut();
                setPosition(frontpos);
                frontcell.onStepIn(this);
            }
        }

        Application.printCall(this, "<--");
    }
    /*  +turn(Direction): forgató függvény, a játékost egy új irányba forgatja el.
     * 				  	  Ha a játékos cipelt dobozt, akkor azzal együtt elfordul,
     * 				  	  ha a doboz beleforgatható egy léphetõ rögzített pályaelembe.
	 */
    public void turn(Direction newdir) {
        Application.printCall(this, "-->turn()");

        System.out.println("?Visz a jatekos dobozt?\ni/n");
        boolean iscarry = false;
        try {
            iscarry = (Test.br.readLine().equals("i"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* A játékosnál fontos, hogy ha visz dobozt, akkor azzal
         * együtt el tudjon fordulni. Emiatt számít, hogy a forgás
         * irányában milyen objektum helyezkedik el, mert nyilván
         * falba nem lehet dobozt tenni.
         * Ha a doboz áthelyezõdött, akkor meg kell hívni az esedékes
         * onStepIn és onStepOut függvényeket is.
         */
        if (iscarry) {
            Point newpos = posInDirection(newdir, 1);

            System.out.println("?Milyen objektum legyen a doboz jovobeli helyen?");
            Test.setSelected();

            CellInterface newcell = game.getMapObject(newpos);
            Box box = getCarry();
            Point boxpos = box.getPosition();

            Test.selected = Test.empty;

            CellInterface boxcell = game.getMapObject(boxpos);

            System.out.println("?Van doboz a cipelt doboz uj helyen?");
            boolean isbox = game.isBox(newpos);
            boolean isstepable = newcell.isStepable();

            if (!isbox && isstepable) {
                boxcell.onStepOut();
                setDirection(newdir);
                box.setPosition(newpos);
                newcell.onStepIn(box);
            }
        } else {
            setDirection(newdir);
        }
        Application.printCall(this, "<--");
    }
}
