package hu.gghf.view;

import hu.gghf.controller.Controller;
import hu.gghf.entities.*;
import hu.gghf.interfaces.CellInterface;
import hu.gghf.interfaces.GameEventListener;
import hu.gghf.interfaces.Location;
import hu.gghf.interfaces.Shootable;
import hu.gghf.model.GameEventHandler;
import hu.gghf.model.Map;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Application {
    public static boolean test = false;
    public boolean exit = false;
    private static Window view;
    private static ReplicatorThread autopilot;
    private static Map map = null;

    public static void main(String[] args) throws InterruptedException {
        Application app = new Application();
        view = new Window();

        // Every event is handled by the console app
        GameEventHandler.setHandler(view);

        app.sendCommand("loadmap palya.csv");

        view.setMap(map);
        Controller control = new Controller(view, app);
    }

    public Application() {
        Images.load();

//        Start automatic movement for replicator
        autopilot = new ReplicatorThread(this);
        autopilot.start();
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
                GameEventHandler.printCall("Sikeresen betoltve: " + parts[1]);
            } catch (IOException e) {
//                e.printStackTrace();
                GameEventHandler.printCall("Palya betoltese sikertelen: " + (new File(parts[1]).getAbsolutePath()));
            }
        }
        else if (parts[0].equals("move")) {
            if (map != null && parts.length > 2) {
                String dir = parts[2];

                if (parts[1].equals("0") || parts[1].equals("1")) {
                    Player p = map.getPlayer(parts[1]);
                    if (p == null) {
                        GameEventHandler.printCall("A \"" + parts[1] + "\" jatekos nem letezek");
                        return;
                    }
                    switch (dir) {
                        case "up":
                            p.move(Location.Direction.UP);
                            break;
                        case "down":
                            p.move(Location.Direction.DOWN);
                            break;
                        case "left":
                            p.move(Location.Direction.LEFT);
                            break;
                        case "right":
                            p.move(Location.Direction.RIGHT);
                            break;
                        default:
                            GameEventHandler.printCall("Nem tamogatott irany:" + dir);
                            break;
                    }
                    GameEventHandler.printCall(String.format("Uj pozicio [%s]: [%s,%s] %s", parts[1],
                        p.getPosition().x,
                        p.getPosition().y,
                        p.getDirection()));
                } else if (parts[1].equals("2")) {
                    Replicator p = map.getReplicator();
                    if (p == null) {
                        return;
                    }
                    switch (dir) {
                        case "up":
                            p.move(Location.Direction.UP);
                            break;
                        case "down":
                            p.move(Location.Direction.DOWN);
                            break;
                        case "left":
                            p.move(Location.Direction.LEFT);
                            break;
                        case "right":
                            p.move(Location.Direction.RIGHT);
                            break;
                    }
                    GameEventHandler.printCall(String.format("Uj pozicio [%s]: [%s,%s] %s", parts[1],
                        p.getPosition().x,
                        p.getPosition().y,
                        p.getDirection()));
                }

            }
        }
        else if (parts[0].equals("shootportal")) {
            if (map != null && parts.length > 2) {
                Player p = map.getPlayer(parts[1]);
                if (p == null) {
                    GameEventHandler.printCall("A \"" + parts[1] + "\" jatekos nem letezek");
                    return;
                }

            	switch (parts[1]) {
                    case "0":
                        switch (parts[2].toLowerCase()) {
                            case "blue":
                                p.shoot(Shootable.Color.BLUE);
                                break;
                            case "yellow":
                                p.shoot(Shootable.Color.YELLOW);
                                break;
                            default:
                                GameEventHandler.printCall("Nem elerheto szin:" + parts[2]);
                                break;
                        }
                        break;
                    case "1":
                        switch (parts[2].toLowerCase()) {
                            case "green":
                                p.shoot(Shootable.Color.GREEN);
                                break;
                            case "red":
                                p.shoot(Shootable.Color.RED);
                                break;
                            default:
                                GameEventHandler.printCall("Nem elerheto szin:" + parts[2]);
                                break;
                        }
                        break;
            	}
            }
        }
        else if (parts[0].equals("pickbox")) {
            if (map != null && parts.length > 1) {
                Player p = map.getPlayer(parts[1]);
                if (p == null) {
                    GameEventHandler.printCall("A \"" + parts[1] + "\" jatekos nem letezek");
                    return;
                }

                p.pickUpBox();
                if (p.getCarry() != null) {
                    GameEventHandler.printCall(String.format("Doboz felvetele [%s]: [%s,%s]", parts[1],
                            p.posInDirection(p.getDirection(), 1).x,
                            p.posInDirection(p.getDirection(), 1).y));
                } else {
                    GameEventHandler.printCall("Doboz felvetele [" + parts[1] + "]: sikertelen");
                }
            }
        }
        else if (parts[0].equals("dropbox")) {
            if (map != null && parts.length > 1) {
                Player p = map.getPlayer(parts[1]);
                map.getPlayer(parts[1]).dropBox();
                GameEventHandler.printCall(String.format("Doboz letetele [%s]: [%s,%s]", parts[1],
                        p.posInDirection(p.getDirection(), 1).x,
                        p.posInDirection(p.getDirection(), 1).y));
            }

        }
        else if (parts[0].equals("showmap")) {
            if (map != null)
                printmap();
        }
        else if (parts[0].equals("testmode")) {
            test = true;
            GameEventHandler.printCall("Determinisztikus uzemmod aktivalva");
        }
        else if (parts[0].equals("quit")) {
            autopilot.stopThread();
            if (map != null)
                exit = true;
        }
        if (view != null) {
            view.updateMap();
        }
    }

    public void printmap() {
        if (map == null) {
            GameEventHandler.printCall("Map is not initialized");
            return;
        }
        Player top_player = map.getPlayer("0");
        Player jaffa = map.getPlayer("1");
        Replicator replicator = map.getReplicator();

        for (int i = 0; i < map.maxsize; i++) {
            String row = "|";
            Point point;
            for (int k = 0; k < map.maxsize; k++) {
                point = new Point(k, i);
                CellInterface obj = map.getMapObject(point);

                if (top_player != null && top_player.getPosition().equals(point)) {
                    switch (top_player.getDirection()) {
                        case UP:
                            row += "a^";
                            break;
                        case DOWN:
                            row += "aV";
                            break;
                        case LEFT:
                            row += "a<";
                            break;
                        case RIGHT:
                            row += "a>";
                            break;
                        default:
                            row += "a_";
                            break;
                    }
                } else if (jaffa != null && jaffa.getPosition().equals(point)) {
                    switch (jaffa.getDirection()) {
                        case UP:
                            row += "f^";
                            break;
                        case DOWN:
                            row += "fV";
                            break;
                        case LEFT:
                            row += "f<";
                            break;
                        case RIGHT:
                            row += "f>";
                            break;
                        default:
                            row += "f_";
                            break;
                    }
                } else if (replicator != null && replicator.getPosition().equals(point)) {
                switch (replicator.getDirection()) {
                    case UP:
                        row += "r^";
                        break;
                    case DOWN:
                        row += "rV";
                        break;
                    case LEFT:
                        row += "r<";
                        break;
                    case RIGHT:
                        row += "r>";
                        break;
                    default:
                        row += "r_";
                        break;
                }
                } else if (map.getBox(point) != null) {
                    row += "b ";
                } else if (obj.getClass() == Door.class) {
                	if (obj.isStepable())
                    row += "D ";
                	else row += "d ";
                } else if (obj.getClass() == Wall.class) {
                    row += "x ";
                } else if (obj.getClass() == EmptyCell.class) {
                    row += "0 ";
                } else if (obj.getClass() == PortalWall.class) {

                    if (obj.isStepable()) {
                        row += "P";
                    } else {
                        row += "p";
                    }

                    Shootable.Color c = ((PortalWall) obj).getColor();
                    if (c != null) {
                        switch (c) {
                            case BLUE:
                                row += "b";
                                break;
                            case YELLOW:
                                row += "y";
                                break;
                            case RED:
                                row += "r";
                                break;
                            case GREEN:
                                row += "g";
                                break;
                        }
                    } else {
                        row += " ";
                    }
                } else if (obj.getClass() == PressurePlate.class) {
                    row += "n ";
                } else if (obj.getClass() == Hole.class) {
                    row += "h ";
                } else if (obj.getClass() == ShieldGenerator.class) {
                    row += "g ";
                } else if (obj.getClass() == ZPM.class) {
                    if (((ZPM) obj).isDiscovered())
                        row += "0 ";
                    else
                        row += "z ";
                }
                else {
                    row += "- ";
                }
                row += "|";
            }
            System.out.println(row);
        }
    }

    class ReplicatorThread extends Thread {
        private final Application app;
        private boolean stopped = false;
        private String prevdir = "up";
        private Random random = new Random();
        private String[] dirs = {"up", "down", "left", "right"};

        public ReplicatorThread(Application application) {
            this.app = application;
        }

        @Override
        public void run() {
            while (!stopped) {
                if (app != null && !Application.test) {
                    if (random.nextFloat() < 0.4f) {
                        prevdir = dirs[random.nextInt(4)];
                    }
                    String newmove = "move 2 " + prevdir;
                    app.sendCommand(newmove);
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void stopThread() {
            stopped = true;
        }
    }
}
