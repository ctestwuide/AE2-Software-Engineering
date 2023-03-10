package AE2;

import java.util.Scanner;

public class Main {
	
	private static Scanner s = ScannerSingleton.getInstance().getScanner();

	public static void main(String[] args) {
		int userType=0;
		
		if(FileHandler.filePathTR==null) {
			FileHandler.askForFilePaths();
		}
		
		do {
			
		//Main menu for user. If this were a real program, there would be a user validation feature
		System.out.print("\nWelcome!\n"
				+ "Enter type of user...\n"
				+ "\tFor class director, enter \"1\"\n"
				+ "\tFor administrator, enter \"2\"\n"
				+ "\tTo save/export data and terminate program, enter \"0\"\n"
				+ "\tEnter type of user: ");
		
		
		if(s.hasNextInt()) {
		userType = s.nextInt();
		s.nextLine();
		} 

		
		switch(userType) {
			case 1:
				CourseDirector.askRequest();
				break;
			case 2:
				Administrator.askRequest();
				break;
			}
		}while(userType!=0);
		FileHandler.saveAndExport();
		System.out.println("\nAll data saved. Program complete!");
		
	}
}
