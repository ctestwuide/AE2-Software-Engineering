package AE2;

import java.util.Scanner;

public class AskGetValidate {

	private static Scanner s = ScannerSingleton.getInstance().getScanner();
	
	//A standard format for asking for user input
	public static void askForInput(String s) {
		System.out.print("\t" + s + ": ");
	}
	
	public static String getStringInput() {
		String myString = s.nextLine();
		
		return myString;
	}
	
	public static Boolean validateStringInput(String s) {
		if(s.length()>0) return true;
		return false;
	}

	public static int getIntInput() {
	    int myInt = 0; // Set to 0 by default
	    String input = s.nextLine();
	    if (!input.isEmpty()) {
	        try {
	            myInt = Integer.parseInt(input);
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid Input! Will default to 0");
	        }
	    }
	    return myInt;
	}
	
	public static int validateIntInput(int i) {
		if(i>0) return i;
		System.out.println("Invalid Input! Will default to 0");
		return 0;
	}
	
	public static boolean getBooleanInput() {
		boolean myBoolean = false;
		if(s.hasNextBoolean()) {
			myBoolean = s.nextBoolean();
		}
		s.nextLine();
		return myBoolean;
	}
	
	public static boolean validateBooleanInput(String s) {
		boolean myBoolean = false;
		String lowS = s.toLowerCase();
		if(lowS.equals("true") || lowS.equals("t")) {
			myBoolean = true;
		}
		return myBoolean;
	}

}
