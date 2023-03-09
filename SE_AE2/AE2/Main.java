package AE2;

import java.util.Scanner;

public class Main {
	
	/*
	 A static scanner is needed. Too many scanner objects using 'System.in' causes errors.
	 */
	
	public static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {
		int userType=0;
		
		if(FileHandler.filePathTR==null) {
			FileHandler.askForFilePaths();
		}
		
		while(userType==0) {
			
		//Main menu for user. If this were a real program, there would be a user validation feature
		System.out.print("Welcome!\n"
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
			case 0:
				FileHandler.saveAndExport();
				return;
			}
		}
	}
}
