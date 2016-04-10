package hu.gghf.model;

import hu.gghf.entities.*;
import hu.gghf.interfaces.CellInterface;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Map {
    private CellInterface[][] map;
    private ArrayList<Box> boxes;
    protected Player oneil, jaffa;
    private Replicator replicator;
    // Ez majd a terkep merete
    public int maxsize = 6;

    public Map(int maxsize) {
        this.maxsize = maxsize;

        map = new CellInterface[maxsize][maxsize];
        boxes = new ArrayList<Box>();
    }

    public CellInterface getMapObject(Point point) {
        return map[point.y][point.x];
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
        //görgetve növelt ciklusváltozó, ami egyben az elem sorkoordinátája is.

        String line;
        //szöveges változó egy pályasor tárolásához.

        /*  Fájlbeolvasás rutinja:
         *  - egy sort beolvasunk a line változóba;
         *  - a beolvasott sort határolók mentén széttördeljük elemekre;
         *  - egy oszlopváltozót folyamosan iterálva a maximum méretig,
         *    megvizsgáljuk a tömbelem elsõ karakterét.
         *  - az elsõ karakter értékétõl függõen:
         *     - ha 'x': falat olvastunk be;
         *     - ha '0': üres padlólapot olvastunk be;
         *     - ha 'p': portálfalat olvastunk be;
         *     - ha 'b': dobozt olvastunk be.
         *  - a rögzített pályaelemeket betesszük a map-ba. Doboz esetében
         *    (mivel nem önállóan pályaelem), egy üres lapot teszünk be,
         *    miközben magát a dobozt is regisztráljuk a dobozok tömbjében.
         */
        while ((line = nf.readLine()) != null) {
            String[] params = line.split(";");
            // TODO: paster tutira
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
                    case 'b':
                        Box box = new Box();
                        box.setPosition(p);
                        map.addBox(box);
                        map.setMapObject(p, new EmptyCell());
                        break;
                    default: break;
                }
            }
            i++;
        }
        nf.close();

        Player p1 = new Player(map);
        p1.setPosition(new Point(2,2));
        map.oneil = p1;

        return map;
    }

    public Player getPlayer(String index) {
        if (index.equals("0"))
            return oneil;
        else if (index.equals("1"))
            return jaffa;
        return null;
    }
}
