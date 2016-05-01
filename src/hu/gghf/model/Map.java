package hu.gghf.model;

import hu.gghf.entities.*;
import hu.gghf.interfaces.CellInterface;
import hu.gghf.interfaces.Location;
import hu.gghf.interfaces.Moveable;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Map {
    private CellInterface[][] map;
    private ArrayList<Box> boxes;
    private Player oneil;
    private Jaffa jaffa;


    protected Replicator replicator;
    // Ez majd a terkep merete
    public int maxsize = 6;

    public Map(int maxsize) {
        this.maxsize = maxsize;

        map = new CellInterface[maxsize][maxsize];
        boxes = new ArrayList<Box>();
    }

    public void setReplicator(Replicator replicator) {
        this.replicator = replicator;
    }
    public Replicator getReplicator() {
        return replicator;
    }

    public CellInterface getMapObject(Point point) {
        try {
            return map[point.y][point.x];
        } catch (ArrayIndexOutOfBoundsException e) {
            return new Wall();
        }
    }

    public void setMapObject(Point point, CellInterface cell) {
        map[point.y][point.x] = cell;
    }

    public Box getBox(Point point) {
        for (Box b : boxes) {
            if (b.getPosition().equals(point))
                return b;
        }
        return null;
    }

    public void addBox(Box box) {
        boxes.add(box);
    }

    public static Map load(String path) throws IOException {
        BufferedReader nf = new BufferedReader(new FileReader(path));
        Map map = null;

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
        
        while ((line = nf.readLine()) != null) {
            String[] params = line.split(";");
            if (map == null)
                map = new Map(params.length);

            for (int j = 0; j < map.maxsize; j++) {
                Point p = new Point(j,i);
                switch (params[j].charAt(0)) {
                    case 'x':
                        map.setMapObject(p, new Wall());
                        break;
                    case '0':
                        map.setMapObject(p, new EmptyCell());
                        break;
                    case 'p':
                        map.setMapObject(p, new PortalWall(p));
                        break;
                    case 'd':
                        map.setMapObject(p, new Door());
                        break;
                    case 'D':
                        Door d = new Door();
                        d.setOpen(true);
                        map.setMapObject(p, d);
                        break;
                    case 'n':
                        String mezo = params[j];
                        String mezo1 = mezo.replace("n[", "");
                        String mezo2 = mezo1.replace("]", "");
                        String[] coords = mezo2.split(",");
                        int x = Integer.parseInt(coords[0]);
                        int y = Integer.parseInt(coords[1]);
                        map.setMapObject(p, new PressurePlate(map, new Point(y,x)));
                        break;
                    case 'h':
                    	map.setMapObject(p, new Hole());
                        break;
                    case 'g':
                    	map.setMapObject(p, new ShieldGenerator());
                        break;
                    case 'b':
                        Box box = new Box(map);
                        box.setPosition(p);
                        map.addBox(box);
                        map.setMapObject(p, new EmptyCell());
                        break;
                    case 'z':
                        map.setMapObject(p, new ZPM(map));
                        break;
                    case 'a':
                    	map.oneil = new Player(map);
                    	map.oneil.setPosition(p);
                    	map.setMapObject(p, new EmptyCell());
                    	switch (params[j].charAt(1)) {
                            case '^':
                                map.oneil.setDirection(Location.Direction.UP);
                                break;
                            case 'V':
                                map.oneil.setDirection(Location.Direction.DOWN);
                                break;
                            case '<':
                                map.oneil.setDirection(Location.Direction.LEFT);
                                break;
                            case '>':
                                map.oneil.setDirection(Location.Direction.RIGHT);
                                break;
                            default: break;
                    	}
                    	break;
                    case 'f':
                    	map.jaffa = new Jaffa(map);
                    	map.jaffa.setPosition(p);
                    	map.setMapObject(p, new EmptyCell());
                    	switch (params[j].charAt(1)) {
                            case '^':
                                map.jaffa.setDirection(Location.Direction.UP);
                                break;
                            case 'V':
                                map.jaffa.setDirection(Location.Direction.DOWN);
                                break;
                            case '<':
                                map.jaffa.setDirection(Location.Direction.LEFT);
                                break;
                            case '>':
                                map.jaffa.setDirection(Location.Direction.RIGHT);
                                break;
                            default: break;
                    	}
                    	break;
                    case 'r':
                    	map.replicator = new Replicator(map);
                    	map.replicator.setPosition(p);
                    	map.setMapObject(p, new EmptyCell());
                    	switch (params[j].charAt(1)) {
                            case '^':
                                map.replicator.setDirection(Location.Direction.UP);
                                break;
                            case 'V':
                                map.replicator.setDirection(Location.Direction.DOWN);
                                break;
                            case '<':
                                map.replicator.setDirection(Location.Direction.LEFT);
                                break;
                            case '>':
                                map.replicator.setDirection(Location.Direction.RIGHT);
                                break;
                            default: break;
                    	}
                    	break;
                    default:
                        Application.printCall(map, "Nem ismert palyaelem a forrasfajlban! \"" + params[j] + "\"");
                        map.setMapObject(p, new EmptyCell());
                        break;
                }
                
            }
            i++;
        }
        nf.close();


        return map;
    }

    public Player getPlayer(String index) {
        if (index.equals("0"))
            return oneil;
        else if (index.equals("1"))
            return jaffa;
        return null;
    }
    
    public void deletePlayer(String index) {
        if (index.equals("0"))
            oneil = null;
        else if (index.equals("1"))
            jaffa = null;
    }
    

    public void removeBox(Box box) {
        boxes.remove(box);
    }
}
