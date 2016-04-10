package hu.gghf.model;

import hu.gghf.entities.*;
import hu.gghf.interfaces.CellInterface;
import hu.gghf.interfaces.Location;
import hu.gghf.interfaces.Shootable;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
    public static BufferedReader br = null;
    private Map map = null;
    boolean exit = false;

    public static void main(String[] args) {
        br = new BufferedReader(new InputStreamReader(System.in));
        Application app = new Application();

        while (!app.exit) {
            try {
                String s = br.readLine();
                app.sendCommand(s);
                app.printmap();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param command
     */
    public void sendCommand(String command) {
        String[] parts = command.split(" ");

        if (parts[0].equals("loadmap")) {
            try {
                map = Map.load(parts[1]);
            } catch (IOException e) {
                e.printStackTrace();
                printCall(this, "Palya betoltese sikertelen: " + parts[1]);
            }
        }
        else if (parts[0].equals("move")) {
            if (map != null) {
                Player p = map.getPlayer(parts[1]);
                String dir = parts[2];

                if (dir.equals("up"))
                    p.move(Location.Direction.UP);
                else if (dir.equals("down"))
                    p.move(Location.Direction.DOWN);
                else if (dir.equals("left"))
                    p.move(Location.Direction.LEFT);
                else if (dir.equals("right"))
                    p.move(Location.Direction.RIGHT);
                printCall(this, String.format("Uj pozicio [%s]: [%s,%s] %s",
                            parts[1],
                            p.getPosition().x,
                            p.getPosition().y,
                            p.getDirection()));
            }
        }
        else if (parts[0].equals("shootportal")) {
            if (map != null) {
                // TODO szinek?
                map.getPlayer(parts[1]).shoot(Shootable.Color.BLUE);
            }
        }
        else if (parts[0].equals("pickbox")) {
            if (map != null)
                map.getPlayer(parts[1]).pickUpBox();
        }
        else if (parts[0].equals("dropbox")) {
            if (map != null)
                map.getPlayer(parts[1]).dropBox();
        }
        else if (parts[0].equals("showmap")) {
            if (map != null)
                printmap();
        }
        else if (parts[0].equals("testmode")) {
            // TODO: autostep
        }
        else if (parts[0].equals("quit")) {
            if (map != null)
                exit = true;
        }
    }

    public static void printCall(Object obj, String msg) {
        System.out.println(String.format("[%s] %s", obj.getClass().getSimpleName(), msg));
    }

    public void printmap() {
        if (map == null) {
            printCall(this, "Map is not initialized");
            return;
        }
        Player top_player = map.getPlayer("0");
        Player jaffa = map.getPlayer("1");

        for (int i = 0; i < map.maxsize; i++) {
            String row = "";
            Point point;
            for (int k = 0; k < map.maxsize; k++) {
                point = new Point(k, i);
                CellInterface obj = map.getMapObject(point);

                if (top_player != null && top_player.getPosition().equals(point)) {
                    switch (top_player.getDirection()) {
                        case UP:
                            row += "^";
                            break;
                        case DOWN:
                            row += "V";
                            break;
                        case LEFT:
                            row += "<";
                            break;
                        case RIGHT:
                            row += ">";
                            break;
                        default:
                            row += "_";
                            break;
                    }
                }
                else if (jaffa != null && jaffa.getPosition().equals(point)) {
                    switch (jaffa.getDirection()) {
                        case UP:
                            row += "^";
                            break;
                        case DOWN:
                            row += "V";
                            break;
                        case LEFT:
                            row += "<";
                            break;
                        case RIGHT:
                            row += ">";
                            break;
                        default:
                            row += "_";
                            break;
                    }
                }
                else if (map.getBox(point) != null) {
                    row += "b";
                }
                else if (obj.getClass() == Door.class) {
                    row += "a";
                }
                else if (obj.getClass() == Wall.class) {
                    row += "x";
                }
                else if (obj.getClass() == EmptyCell.class) {
                    row += "0";
                }
                else if (obj.getClass() == PortalWall.class) {
                    row += "p";
                }
                else if (obj.getClass() == PressurePlate.class) {
                    row += "n";
                }
                else if (obj.getClass() == Hole.class) {
                    row += "n";
                }
                else {
                    row += "-";
                }
            }
            System.out.println(row);
        }
    }
}
