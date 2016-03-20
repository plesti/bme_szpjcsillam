package hu.gghf.model;

import hu.gghf.entities.Box;
import hu.gghf.entities.CellInterface;
import hu.gghf.entities.Location;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
	
	enum TestingState {IDLE, STARTED, EXITED}
    public static void main(String[] args) {
    	
    	TestingState t = TestingState.IDLE;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Controller controller = null;
       
        while (true) {
        if (t == TestingState.IDLE) {
           System.out.println("Állapotom: IDLE");
//             controller.printmap();
           try {
                String s = br.readLine();
                if (s.equals("start")) {
                    controller = new Controller();
                    t = TestingState.STARTED;
                    System.out.println("______________");
                }
           }
           catch (IOException e) {
               e.printStackTrace();
           }
        }
        if (t == TestingState.STARTED) {
        	System.out.println("Állapotom: STARTED");
            //controller.printmap();
        try {
             String s = br.readLine();
             if (s.equals("q")) {
                 t = TestingState.EXITED;
             }
             else {
            	 if (s.equals("u"))
            		 controller.movePlayer(Location.Direction.UP);
                 if (s.equals("d"))
                     controller.movePlayer(Location.Direction.DOWN);
                 if (s.equals("l"))
                     controller.movePlayer(Location.Direction.LEFT);
                 if (s.equals("r"))
                     controller.movePlayer(Location.Direction.RIGHT);
                 if (s.equals("sb"))
                     controller.openPortal(CellInterface.Color.BLUE);
                 if (s.equals("sy"))
                     controller.openPortal(CellInterface.Color.YELLOW);
                 if (s.equals("p"))
                     controller.pickUpBox();
                 if (s.equals("pd"))
                     controller.dropBox();                 
                 System.out.println("______________");
            	 }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        }
        
        if (t == TestingState.EXITED) {
        	System.out.println("Állapotom: EXITED");
        	System.out.println("Én itt végeztem!");
        	t = TestingState.IDLE;
        }
        }
    }

    public static void printCall(Object obj, String msg) {
        System.out.println(String.format("%s.%s", obj.getClass().getSimpleName(), msg));
    }
}
