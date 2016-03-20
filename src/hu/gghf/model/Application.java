package hu.gghf.model;

import hu.gghf.entities.Box;
import hu.gghf.entities.CellInterface;
import hu.gghf.entities.Location;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
    public static void main(String[] args) {
        Controller controller = new Controller();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean exit = false;
        while (!exit) {
            System.out.println("______________");
//            controller.printmap();

            try {
                String s = br.readLine();
                if (s.equals("u")) {
                    controller.movePlayer(Location.Direction.UP);
                } else if (s.equals("d")) {
                    controller.movePlayer(Location.Direction.DOWN);
                }
                else if (s.equals("l")) {
                    controller.movePlayer(Location.Direction.LEFT);
                }
                else if (s.equals("r")) {
                    controller.movePlayer(Location.Direction.RIGHT);
                }
                else if (s.equals("sb")) {
                    controller.openPortal(CellInterface.Color.BLUE);
                }
                else if (s.equals("sy")) {
                    controller.openPortal(CellInterface.Color.YELLOW);
                }
                else if (s.equals("p")) {
                    controller.pickUpBox();
                }
                else if (s.equals("pd")) {
                    controller.dropBox();
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
