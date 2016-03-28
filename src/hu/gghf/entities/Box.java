package hu.gghf.entities;

import hu.gghf.model.Application;
/*  +class Box : Location
 *  Doboz p�lyaelem megval�s�t�sa. A doboz elhelyezhet� t�rgy,
 *  amit a j�t�kos tud mag�val cipelni �s od�bb vinni. Egy �tlagos
 *  mozgathat�t�l megsemmis�l�si esem�nye k�l�nb�zteti meg.
 */
public class Box extends Location {
	/*  +destroy(): fel�ldefini�lt f�ggv�ny. Megsemmis�l�skor a doboz
	 *  			sz�tesik; a szkeletonban egyedi reprezent�ci�ja
	 *  			nincs. 
	 */
    @Override
    public void destroy() {
//        System.out.println("Szetestem!");
        Application.printCall(this, "-->destroy()");
        Application.printCall(this, "<--");
    }
}