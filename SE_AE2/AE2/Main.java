package AE2;


public class Main {
	

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
		
		
		userType = AskGetValidate.getIntInput();
		
		
		switch(userType) {
			case 1:
				CourseDirector.askRequest();
				break;
			case 2:
				Administrator.askRequest();
				break;
			case 0:
				break;
			default:
				System.out.println("\n\nInvalid Input!");
				break;
			}
		}while(userType!=0);
		//FileHandler.saveAndExport();
		System.out.println("\nAll data saved. Program complete!");
		
	}
}
