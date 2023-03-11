package AE2;

import java.util.Scanner;

public class AskGetValidate {

	private static Scanner s = ScannerSingleton.getInstance().getScanner();
	
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
		int myInt = -1;
		if(s.hasNextInt()) {
			myInt = s.nextInt();
		}
		s.nextLine();
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
