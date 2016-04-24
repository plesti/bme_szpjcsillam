package hu.gghf.test;

import hu.gghf.model.Application;

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
	private static boolean compare(File expected, File result) throws IOException
	{ 
		brExp = new BufferedReader(new FileReader(expected));
		brRes = new BufferedReader(new FileReader(result));
		//StringBuilder sbexp = new StringBuilder();
		//StringBuilder sbres = new StringBuilder();
	    String lineRes;
	    String lineExp;
	    
		lineExp = brExp.readLine();
		lineRes = brRes.readLine();
		while (lineExp != null) { 
			
	        if(!(lineExp.toString().equals(lineRes.toString())))
			{
			System.out.println("A kovetkezo sorok nem egyeznek:");
			System.out.println("\"" + lineExp.toString()+"\" helyett \""+lineRes.toString() + "\"");
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
		   Application app = new Application();
		   
		   System.out.println("Valaszd ki a sorszamat annak a testnek amelyiket futtatni szeretned:");
		   for(int i = 0; i < inFilecount; i++)
    	   {
    		  System.out.println(i+1+" - "+inFile.list()[i]);
    	   }
		   String n = brconsole.readLine();
		   inFile = new File(inFile.toString()+"\\test"+n+".txt");
 		   brinFile = new BufferedReader(new FileReader(inFile));
 		   outFile = new File(outFile.toString()+"\\result"+n+".txt");
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
	            	compareFile = new File(".\\test\\compare\\compare"+n+".txt");
	 	           	if(compare(compareFile,outFile))
	 	           		{System.out.println("jo"); break;}
	 	           	else
	 	           		{System.out.println("nemjo"); break;}
	            }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	
	
	
}
