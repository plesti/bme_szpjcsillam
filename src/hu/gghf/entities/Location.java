package hu.gghf.entities;

import hu.gghf.model.Application;
import hu.gghf.model.Test;

import java.awt.*;
import java.io.IOException;
/*  +abstract class Location
 *  Absztrakt oszt�ly mozgathat� p�lyaelemek alaptulajdons�gainak �s
 *  alapf�ggv�nyeinek csoportba foglal�s�ra. Az k�l�nb�zteti meg a r�gz�tett
 *  p�lyaelemt�l, hogy mozgathat�, ebb�l kifoly�lag rendelkezik ir�nnyal.
 *  Rendelkezik getter �s setter f�ggv�nyekkel, tov�bb� egy esem�nykezel�vel
 *  is megsemmis�l�s eset�re, mely ut�bbit szigor�an a bel�le lesz�rmaz�
 *  oszt�lyok val�s�tanak meg.
 *  Tipikus Location elem a j�t�koskarakter �s a doboz.
 */
public abstract class Location {
	/*	Attrib�tumlista:
	 * 	+Direction: felsorolt t�pus az ir�ny be�ll�t�s�ra.
	 * 	-position: a mozd�that� p�lyaelem helyzete, koordin�tap�rban.
	 * 	-direction: a Direction egy p�ld�nya.
	 */
    public enum Direction { UP, DOWN, LEFT, RIGHT }

    private Point position;
    private Direction direction;
    /*  +Location(): konstruktor, ami p�ld�nyos�tja a mozgathat�t.
     *  			 Ez rendszerint a p�lya beolvas�sakor h�v�dik meg, 
     *  			 am�g elhelyez�sre �s be�ll�t�sra nem ker�l.  			 
    */
    public Location() {
        Application.printCall(this, "-->Location()");

        position = new Point(0, 0);
        direction = Direction.UP;
        Application.printCall(this, "<--");
    }
    /*  +getPosition(): getter f�ggv�ny, ami lek�rdezi a mozgathat� helyzet�t.
     * 	@return: a mozgathat� helyzete pontkoordin�t�ban.
     */
    public Point getPosition() {
        Application.printCall(this, "-->getPosition()");

        Application.printCall(this, "<--");
        return position;
    }
    /*  +getPosition(Point): setter f�ggv�ny, ami be�ll�tja a mozgathat� helyzet�t.
     *  @param position: a be�ll�tand� pont, pontkoordin�t�val.
     */
    public void setPosition(Point position) {
        Application.printCall(this, "-->setPosition()");
        this.position = position;
        Application.printCall(this, "<--");
    }
    /*  +getDirection(): getter f�ggv�ny, ami lek�rdezi a mozgathat� pillanatnyi ir�ny�t.
     * 					 A szkeletonban ez megval�s�tja a j�t�kos ir�nybe�ll�t�s�t is.
     * 	@return: a mozgathat� ir�nya a 4 �gt�j valamelyik�vel.
     */
    public Direction getDirection() {
        Application.printCall(this, "-->getDirection()");
//        return direction;

        // TODO: TOROLNI
        if (Test.playerdir == null)
            Test.setPlayerDir();
        Application.printCall(this, "<--");
        return Test.playerdir;
    }
    /*  +setDirection(): setter f�ggv�ny, ami be�ll�tja a mozgathat� ir�ny�t.
     * 	@param direction: a be�ll�tand� ir�ny.
     */
    public void setDirection(Direction direction) {
        Application.printCall(this, "-->setDirection()");

        this.direction = direction;
        Application.printCall(this, "<--");
    }

    /*  +abstract destroy(): esem�nykezel� f�ggv�ny, ami akkor h�v�dik meg,
     * 						 ha a mozgathat� megsemmis�l; tipikusan akkor, 
     * 						 ha az szakad�kba ker�l.
    */
    public abstract void destroy();
}
