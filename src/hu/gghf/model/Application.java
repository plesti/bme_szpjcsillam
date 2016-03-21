package hu.gghf.model;

import hu.gghf.entities.CellInterface;
import hu.gghf.entities.Location;

import java.io.IOException;

public class Application {
    private static boolean debug = false;

    public static void main(String[] args) {
        Controller controller = null;


        boolean exit = false;
        while (!exit) {
            System.out.println("_____________");
            System.out.println("Add meg interakcionak megfelelo parancsot!");
            System.out.println("Uj jatek (start)");
            System.out.println("Mozgas fel (u)");
            System.out.println("Mozgas Le (d)");
            System.out.println("Mozgas balra (l)");
            System.out.println("Mozgas jobbra (r)");
            System.out.println("Doboz felemeles (p)");
            System.out.println("Doboz letetel (pd)");
            System.out.println("Kilepes (q)");

            debug = false;
            Test.init();

            if (controller != null) {
//                controller.printmap();
            }
            debug = true;

            System.out.println("_____________");
            try {
                String s = Test.br.readLine();
                if (s.equals("start")) {
                    controller = new Controller();
                }
                else if (s.equals("u")) {
                    if (controller != null) {
                        controller.movePlayer(Location.Direction.UP);
                    }
                } else if (s.equals("d")) {
                    if (controller != null) {
                        controller.movePlayer(Location.Direction.DOWN);
                    }
                }
                else if (s.equals("l")) {
                    if (controller != null) {
                        controller.movePlayer(Location.Direction.LEFT);
                    }
                }
                else if (s.equals("r")) {
                    if (controller != null) {
                        controller.movePlayer(Location.Direction.RIGHT);
                    }
                }
                else if (s.equals("sb")) {
                    if (controller != null) {
                        controller.openPortal(CellInterface.Color.BLUE);
                    }
                }
                else if (s.equals("sy")) {
                    if (controller != null) {
                        controller.openPortal(CellInterface.Color.YELLOW);
                    }
                }
                else if (s.equals("p")) {
                    if (controller != null) {
                        controller.pickUpBox();
                    }
                }
                else if (s.equals("pd")) {
                    if (controller != null) {
                        controller.dropBox();
                    }
                }
                else if (s.equals("q")) {
                    exit = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int levelcounter = 0;
    public static void printCall(Object obj, String msg) {
        if (debug) {
            String output = String.format(" %s (%s)", msg, obj.getClass().getSimpleName());

            if (msg.startsWith("<--")) {
                levelcounter--;
            }
            for (int i = 0; i < levelcounter; i++) {
                System.out.print("\t");
            }
            System.out.println(output);
            if (msg.startsWith("-->")) {
                levelcounter++;
            }
        }
    }
}
