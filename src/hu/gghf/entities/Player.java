package hu.gghf.entities;

import hu.gghf.model.Application;
import hu.gghf.model.Game;
import hu.gghf.model.Test;

import java.awt.*;
import java.io.IOException;
/*  +class Player : CellInterface
 *  J�t�kos p�lyaelem megval�s�t�sa, az egyik legf�bb oszt�ly.
 *  Olyan objektum, ami k�pes doboz cipel�s�re �s ZPM-ek �ssze-
 *  gy�jt�s�re, tov�bb� �n�ll�an forogni �s mozogni a j�t�kt�ren.
 *  A j�t�kos 4 ZPM �sszegy�jt�s�vel tudja megnyerni a j�t�kot,
 *  de ha szakad�kba ker�l, akkor meghal �s a j�t�knak veres�ggel
 *  v�ge szakad. 
 */
public class Player extends Location {
	/*  Attrib�tumlista:
	 * 	-carryObject: doboz objektum refenci�ja, amit a j�t�kos mag�n�l tart.
	 *  -game: bels� referencia a j�t�kmenetre.
	 */
    private Box carryObject;
    private Game game;
    /* +Player(Game): konstruktor, ami elv�gzi a bels� j�t�kreferencia
     * 				  p�ld�nyos�t�s�t.
     * @param game: a p�ld�nyos�t� j�t�kmenet referenci�ja.
    */
    public Player(Game game) {
        this.game = game;
        this.carryObject = null;
    }
    /*  +destroy(): fel�ldefini�lt f�ggv�ny. Megsemmis�l�skor a j�t�kos
	 *  			meghal; a szkeletonban egyedi reprezent�ci�ja
	 *  			nincs. 
	 */
    @Override
    public void destroy() {
        Application.printCall(this, "-->destroy()");
        Application.printCall(this, "<--");
    }
    /*  +setCarry(Box): setter f�ggv�ny, ami a j�t�koshoz "l�ncolja" a
     * 					megadott dobozt, mi�ltal a j�t�kos cipelni �s
     * 					vele egy�tt forogni tud.
     *  @param box: a j�t�koshoz hozz�rendelt doboz referenci�ja.
	 */
    public void setCarry(Box box) {
        Application.printCall(this, "-->setCarry()");
        carryObject = box;
        Application.printCall(this, "<--");
    }
    /*  +getCarry(Box): getter f�ggv�ny, ami a j�t�kosn�l l�v� dobozt
     * 					k�rdezi le.
     *  @return: a j�t�koshoz hozz�rendelt doboz referenci�ja.
	 */
    public Box getCarry() {
        Application.printCall(this, "-->getCarry()");
//        return carryObject;

//        TODO: TOROLNI
        Application.printCall(this, "<--");
        return Test.carryBox;
    }
    /*  +isCarry(): lek�rdez� f�ggv�ny, ami visszaadja, hogy a j�t�kosn�l
     * 				   tal�lhat� -e doboz a lek�rdez�s pillanat�ban.
     *  @return: igaz, ha tal�lhat� n�la doboz, egy�bk�nt hamis.
	 */
    public boolean isCarry() {
        Application.printCall(this, "-->isCarry()");
//        return carryObject != null;

//        TODO: TOROLNI
        Application.printCall(this, "<--");
        return Test.iscarry;
    }
    /*  +posInDirection(Direction,int): seg�df�ggv�ny, amivel lek�rdezhet� egy adott pontt�l
     * 									megadott t�vols�gra l�v� pont koordin�t�ja, megadott
     * 									ir�nyban (a 4 �gt�j valamelyik�ben).
     *  @return: a k�rd�ses helyen l�v� pont koordin�t�ja.
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
    /*  +findTarget(): ezzel a f�ggv�nnyel der�thet� ki, hogy egy port�ll�ved�k
     * 				   c�lpontja milyen, �s hol tal�lhat�.
     *  @return: a megtal�lt r�gz�tett p�lyaelem referenci�ja.
	 */
    public CellInterface findTarget() {
        Application.printCall(this, "-->findTarget()");

        //A j�t�kos ir�ny�nak megfelel�en kell utat keresni.
        Direction dir = getDirection();
        //Az aktu�lis c�lpont referenci�ja.
        CellInterface target;

        /*  A c�lkeres� folyamat elindul a j�t�kost�l, a saj�t ir�ny�ban,
         *  �s folyamatosan n�veli a j�t�kost�l val� t�vols�g�t, mik�zben
         *  lek�rdezi az ott tal�lhat� t�rgyak l�het�s�g�t.
         *  Amint l�het� t�rgyat tal�l, visszat�r annak referenci�j�val,
         *  egy�bk�nt nullal. 
         */ 
        for (int i = 1; i < 8; i++) {
            Point pos = posInDirection(dir, i);
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
    /*  +goForward(): mozgat� f�ggv�ny, a j�t�kost egy l�p�ssel el�refele
     * 				  mozd�tja. Ha a j�t�kos szab�lyosan nem tud mozogni,
     * 				  akkor helyben marad.  
	 */
    public void goForward() {
        Application.printCall(this, "-->goForward()");
        
        //Ismerni kell a j�t�kos aktu�lis �s 1-gyel el�tte lev� poz�ci�j�t.
        Location.Direction dir = getDirection();
        Point frontpos = posInDirection(dir, 1);
        Point currentpos = getPosition();

//        TODO: KITOROLNI PROTOBAN
        //Fontos, hogy milyen objektum van a j�t�kos el�tt.
        System.out.println("?Milyen objektum legyen a jatekos elott?");
        Test.setSelected();

        CellInterface frontcell = game.getMapObject(frontpos);

        // TODO: ez is debug kod->TOROLNI! ures mezot adjon vissza a getMapObject
        Test.selected = Test.empty;

        CellInterface currentcell = game.getMapObject(currentpos);

        boolean isstepable = frontcell.isStepable();
        /*  Ebben az el�gaz�sban t�rt�nik a l�phet�s�g ki�rt�kel�se.
         *  A l�p�sek egyes esetei:
         *  - ha a j�t�kos vagy az el�tte lev� doboz nem tud el�rel�pni,
         *    akkor a j�t�kos helyben marad;
         *  - ha a j�t�kos el�tt doboz van �s azt l�phet�be tudja tolni,
         *    akkor azzal egy�tt egy l�p�st el�remegy;
         *  - a port�lba helyezett t�rgy/j�t�kos automatikusan �thelyez�dik.
         *  Mindig megh�v�dik a r�gz. p�lyaelemre l�p�s �s arr�l lel�p�s esem�nye
         *  is a megfelel� cell�kn�l.
         */
        
        if (isstepable) {

            // TODO: Torolni! A teszt resze
            Test.setIscarry();

            boolean iscarry = isCarry();

            Box getbox = game.getBox(frontpos);

            if (iscarry) {
                Point frontpos2 = posInDirection(dir, 2);

                // TODO: Torolni! A teszt resze
                System.out.println("?Milyen objektum legyen a kettovel jatekos elott?");
                Test.setSelected();

                CellInterface frontcell2 = game.getMapObject(frontpos2);
                Box getbox2 = game.getBox(frontpos2);

                // Ha nincs el�tte doboz �s kett�vel el�tte �res:
                if (frontcell2.isStepable() && getbox2 == null) {
                    currentcell.onStepOut();
                    frontcell.onStepOut();

                    // Player el�rel�p:
                    setPosition(frontpos);
                    frontcell.onStepIn(this);

                    // Ha port�lba l�p a j�t�kos, akkor a doboznak el� kell kerulnie:
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
    /*  +turn(Direction): forgat� f�ggv�ny, a j�t�kost egy �j ir�nyba forgatja el.
     * 				  	  Ha a j�t�kos cipelt dobozt, akkor azzal egy�tt elfordul,
     * 				  	  ha a doboz beleforgathat� egy l�phet� r�gz�tett p�lyaelembe.
	 */
    public void turn(Direction newdir) {
        Application.printCall(this, "-->turn()");

        // TODO: Torolni! A teszt resze
        System.out.println("?Visz a jatekos dobozt?\ni/n");
        boolean iscarry = false;
        try {
            iscarry = (Test.br.readLine().equals("i"));
        } catch (IOException e) {
            e.printStackTrace();
        }
//      boolean iscarry = isCarry();
        /* A j�t�kosn�l fontos, hogy ha visz dobozt, akkor azzal
         * egy�tt el tudjon fordulni. Emiatt sz�m�t, hogy a forg�s
         * ir�ny�ban milyen objektum helyezkedik el, mert nyilv�n
         * falba nem lehet dobozt tenni.
         * Ha a doboz �thelyez�d�tt, akkor meg kell h�vni az esed�kes
         * onStepIn �s onStepOut f�ggv�nyeket is.
         */
        if (iscarry) {
            // TODO itt volt egy getDirection ami nem kell

            Point newpos = posInDirection(newdir, 1);

            // TODO: torolni
            System.out.println("?Milyen objektum legyen a doboz jovobeli helyen?");
            Test.setSelected();

            CellInterface newcell = game.getMapObject(newpos);
            Box box = getCarry();
            Point boxpos = box.getPosition();

            // TODO: ez is debug kod->TOROLNI! ures mezot adjon vissza a getMapObject
            Test.selected = Test.empty;

            CellInterface boxcell = game.getMapObject(boxpos);

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
