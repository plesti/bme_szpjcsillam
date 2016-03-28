package hu.gghf.entities;

import hu.gghf.model.Application;
/*  +class Box : Location
 *  Doboz pályaelem megvalósítása. A doboz elhelyezhetõ tárgy,
 *  amit a játékos tud magával cipelni és odébb vinni. Egy átlagos
 *  mozgathatótól megsemmisülési eseménye különbözteti meg.
 */
public class Box extends Location {
	/*  +destroy(): felüldefiniált függvény. Megsemmisüléskor a doboz
	 *  			szétesik; a szkeletonban egyedi reprezentációja
	 *  			nincs. 
	 */
    @Override
    public void destroy() {
//        System.out.println("Szetestem!");
        Application.printCall(this, "-->destroy()");
        Application.printCall(this, "<--");
    }
}