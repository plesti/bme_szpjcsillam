package hu.gghf.entities;

/*  +interface CellInterface
 *  Interf�szoszt�ly r�gz�tett p�lyaelemek alaptulajdons�gainak �s
 *  alapf�ggv�nyeinek felsorol�s�ra. A j�t�kt�r alapegys�ge,
 *  rendelkezik esem�nyf�ggv�nyekkel �s egy�b saj�t tulajdons�gokkal.
 *  A lesz�rmaz� oszt�lyok egyedileg val�s�tj�k majd meg az interf�sz
 *  met�dusait, itt csak egy �sszegz� le�r�s tal�lhat� meg r�luk.
 */
public interface CellInterface {
	
	//Felsorolt t�pus az egyes port�lfalsz�nek be�ll�t�s�hoz. 
    enum Color { BLUE, YELLOW }

    /* isStepable(): lek�rdezi, hogy helyezhet� -e doboz/j�t�kos a r�gz�tett p�lyaelemre.
     * @return: igaz, ha l�phet�, hamis, ha nem.
     */
    boolean isStepable();
    /* isShootable(): lek�rdezi, hogy az adott r�gz. p�lyaelem l�het� -e, 
     * 				  azaz bel��tk�zik port�ll�ved�k.
     * @return: igaz, ha meg�ll benne, hamis, ha igen.
     */
    boolean isShootable();
    /* onStepIn(Location): esem�nykezel� f�ggv�ny arra az esetre, amikor egy t�rgy 
     * 			   		   a r�gz�tett p�lyaelemre ker�l.
     * @param object: az az objektum, ami interakci�ba l�p a p�lyaelemmel.
     */    
    void onStepIn(Location object);
    /* onStepOut(): esem�nykezel� f�ggv�ny arra az esetre, amikor egy t�rgy 
     * 				a r�gz�tett p�lyaelemr�l leker�l.
     */
    void onStepOut();
    /* shoot(Player, Color): l�v�sf�ggv�ny, ami port�lt nyit a p�lyaelemen, a j�t�kos
     * 						 ir�ny�t�l f�gg�en. �rdemben csak a port�lfal val�s�tja meg.
     * @param player: a j�t�kos referenci�ja.
     * @param color: a nyitand� port�l sz�ne (k�k vagy s�rga).
     */
    void shoot(Player player, Color color);
}