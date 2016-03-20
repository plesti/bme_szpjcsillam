package hu.gghf.model;

import hu.gghf.entities.CellInterface;
import hu.gghf.entities.Location;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
    public static void main(String[] args) {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean exit = false;
        while (!exit) {
            Controller controller = null;
            System.out.println("Nyomd le az interakcionak megfelelo parancsot!");
            System.out.println("Mozgas fel (u)");
            System.out.println("Mozgas Le (d)");
            System.out.println("Mozgas balra (l)");
            System.out.println("Mozgas jobbra (r)");

//            controller.printmap();

            try {
                String s = br.readLine();
                if (s.equals("start")) {
                    controller = new Controller();
                }
                if (s.equals("u")) {
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

    public static void printCall(Object obj, String msg) {
        System.out.println(String.format("%s.%s", obj.getClass().getSimpleName(), msg));
    }
}
