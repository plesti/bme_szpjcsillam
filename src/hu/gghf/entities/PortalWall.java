package hu.gghf.entities;

import hu.gghf.model.Application;
import java.awt.*;

/*  +class PortalWall : CellInterface, Location 
 *  Portálfal pályaelem megvalósítása. A portálfal egy speciális
 *  fal, amelyen portál nyitható megadott színû portállövedékkel. 
 *  Ha a térképen van egy-egy nyitott kék és sárga portál, akkor 
 *  az egyik portálfalon átmenõ tárgy vagy játékos a másik
 *  portálfalba kerül (teleportál).
 *  A portálfalon lévõ nyitott portál megsemmisül, ha kilõnek egy
 *  az õ színével megegyezõ portált egy másik portálfalra.
 */

public class PortalWall extends Location implements CellInterface {
	// Portálfalhoz tartozó kék, illetve sárga portál referenciái.
    private static PortalWall blue = null;
    private static PortalWall yellow = null;
    
    /* +PortalWall(Point): konstruktor, ami példányosítja a portálfalat, és
     * 				  	   beállítja annak kezdõhelyzetét. Ez rendszerint a 
     * 					   pálya beolvasásakor hívódik meg.
    */
    public PortalWall(Point position) {
        this.setPosition(position);
    }
    /*  +isStepable(): felüldefiniált függvény léphetõség lekérdezésére.
     *  			   A portál akkor léphetõ, ha nyitva van, egyébként nem
     *  			   enged át semmit.
    */
    @Override
    public boolean isStepable() {
        Application.printCall(this, "-->isStepable()");

        if (blue == this && yellow != null) {
            Application.printCall(this, "<--");
            return true;
        } else if (yellow == this && blue != null) {
            Application.printCall(this, "<--");
            return true;
        }
        Application.printCall(this, "<--");
        return false;
    }
    /*  +onStepIn(Location): felüldefiniált függvény esemény kezelésére,
     * 					     amikor valami a portálfalba kerül. Ha a portálba
     * 						 belépnek, és van nyitva másik különbözõ színû
     * 						 portál is, akkor a tárgy átkerül a másik portál
     * 						 bejáratához és "kifelé" néz.		     
     */
    @Override
    public void onStepIn(Location object) {
        Application.printCall(this, "-->onStepIn()");

        if (blue == this && yellow != null) {
            object.setPosition(yellow.getPosition());
            object.setDirection(yellow.getDirection());
        } else if (yellow == this && blue != null) {
            object.setPosition(blue.getPosition());
            object.setDirection(blue.getDirection());
        }
        Application.printCall(this, "<--");
    }
    /*  +onStepOut(): felüldefiniált függvény, a portálból kilépés kezelésére.
     * 				  Nincs hatással a játékra, mivel a portálba belépéskor
     * 				  egyúttal lekezelésre kerül a kilépés is. 
     */
    @Override
    public void onStepOut() {
        Application.printCall(this, "-->onStepOut()");

        Application.printCall(this, "<--");
    }
    /*  +isShootable(): felüldefiniált függvény. Visszaadja, hogy lõhetõ -e 
     * 					a portálfal a felé kilõtt portállövedékkel. Természetesen
     * 					lõhetõ a portálfal, tehát a függvény igazzal tér vissza.
     */
    @Override
    public boolean isShootable() {
        Application.printCall(this, "-->isShootable()");

        Application.printCall(this, "<--");
        return true;
    }
    /*  +shoot(Player, Color): felüldefiniált függvény portálnyitás kezelésére.
     *  					   Nyit egy megfelelõ színû portált a falon, az elõzõ
     *  					   azonos színû portált meg is szünteti. Ezután lekérdezi
     *  					   a játékos állapotát és beforgatja a kimeneti irányba,
     *  					   hogy "jófelé" nézzen.
     */
    @Override
    public void shoot(Player player, Color color) {
        Application.printCall(this, "-->shoot()");

        if (color == Color.BLUE) {
            blue = this;
            System.out.println("Kekvagyok");
//            System.out.println(String.format("Kekvagyok: %s,%s", getPosition().x, getPosition().y));
        } else {
            yellow = this;
            System.out.println("Sargavagyok");
//            System.out.println(String.format("Sargavagyok: %s,%s", getPosition().x, getPosition().y));
        }

        Direction pdir = player.getDirection();

        switch (pdir) {
            case UP:
                setDirection(Direction.DOWN);
                break;
            case DOWN:
                setDirection(Direction.UP);
                break;
            case LEFT:
                setDirection(Direction.RIGHT);
                break;
            case RIGHT:
                setDirection(Direction.LEFT);
                break;
        }
        Application.printCall(this, "<--");
    }
    /*  +destroy(): felüldefiniált függvény a megsemmisülés lekezelésére.
     * 				Nincs hatással a játékra, mert a portál "nem vész el,
     * 				csak átalakul".
     */
    @Override
    public void destroy() {
        Application.printCall(this, "-->destroy()");
        Application.printCall(this, "<--");
    }
}