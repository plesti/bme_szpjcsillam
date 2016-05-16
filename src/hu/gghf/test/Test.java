package hu.gghf.test;

import hu.gghf.model.GameEventHandler;
import hu.gghf.view.ConsoleApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.System;

public class Test {
    private static BufferedReader brinFile;
    private static BufferedReader brconsole;

    static File inFile;
    static File outFile;
    static File compareFile;
    static int inFilecount;
    private static BufferedReader brExp;
    private static BufferedReader brRes;

    // returns true if the files are same
    private static boolean compare(File expected, File result) throws IOException {
        brExp = new BufferedReader(new FileReader(expected));
        brRes = new BufferedReader(new FileReader(result));
        String lineRes;
        String lineExp;

        lineExp = brExp.readLine();
        lineRes = brRes.readLine();
        while (lineExp != null) {
            if (!(lineExp.toString().equals(lineRes.toString()))) {
                System.out.println("A kovetkezo sorok nem egyeznek:");
                System.out.println("\"" + lineExp.toString() + "\" helyett \"" + lineRes.toString() + "\"");
                return false;
            }
            lineExp = brExp.readLine();
            lineRes = brRes.readLine();
        }


        return true;
    }


    public static void main(String[] args) throws IOException {
        inFile = new File(".\\test\\testcommand");
        inFilecount = inFile.list().length;
        outFile = new File(".\\test\\results\\");

        brconsole = new BufferedReader(new InputStreamReader(System.in));
        PrintStream stdout = System.out;
        ConsoleApplication app = new ConsoleApplication();

        // Every event is handled by the console app
        GameEventHandler.setHandler(app);

        System.out.println("Valaszd ki a sorszamat annak a testnek amelyiket futtatni szeretned:");
        for (int i = 0; i < inFilecount; i++) {
            System.out.println(i + 1 + " - " + inFile.list()[i]);
        }
        String n = brconsole.readLine();
        String commandfile = null;
        try {
             commandfile = inFile.list()[Integer.parseInt(n) - 1];
        } catch (NumberFormatException err) {
            System.out.println("Hibas szam: " + n);
            app.sendCommand("quit");
            return;
        }
        String id = commandfile.split("_")[0].substring(4);

        inFile = new File(inFile.toString() + "\\" + commandfile);
        brinFile = new BufferedReader(new FileReader(inFile));
        outFile = new File(outFile.toString() + "\\result" + id + ".txt");
        System.setOut(new PrintStream(outFile));
        while (!app.exit) {

            try {
                String line = brinFile.readLine();
                while (line != null) {
                    app.sendCommand(line);
                    line = brinFile.readLine();
                }

                System.setOut(stdout);
                {
                    compareFile = new File(".\\test\\compare\\compare" + id + ".txt");
                    if (compare(compareFile, outFile)) {
                        System.out.println("jo");
                        break;
                    } else {
                        System.out.println("nemjo");
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
