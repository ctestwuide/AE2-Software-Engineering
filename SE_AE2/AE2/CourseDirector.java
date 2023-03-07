package AE2;

public class CourseDirector {
	
	public static void askRequest() {
		int requestType=0;
		
		do {
			System.out.print("\nWould you like to...\n"
				+ "\t1-Add Teaching Requirement (enter 1)\n"
				+ "\t2-View a Teaching Requirement (enter 2)\n"
				+ "\t3-View all Teaching Requirements (enter 3)\n"
				+ "\t4-Delete Teaching Requirement (enter 4)\n"
				+ "\t0-Return to main menu (enter 0)\n"
				+ "\tEnter a number: ");
		
			if(Main.s.hasNextInt()) {
				requestType = Main.s.nextInt();
				
			} else {
				System.out.println("\nInvalid input, try again...\n");
			}
			
			Main.s.nextLine(); // Clear scanner!
		
			switch(requestType) {
		    case 1:
		        addTeachingRequirement();
		        break;
		    case 2:
		        viewTeachingRequirement();
		        break;
		    case 3:
		        viewAllTeachingRequirements();
		        break;
		    case 4:
		        deleteTeachingRequirement();
		        break;
			}

		}while(requestType!=0);
		
		try {
			FileHandler.writeTeachingRequirementFile(DataImportExport.filePathTR);
			System.out.println("\nChanges saved!");
		}finally {
			System.out.println();
			Main.main(null); //exit and return to program start!
		}

	}
	
	public static void addTeachingRequirement() {
		int ID = 0;
		int salary =0;
		int hours =0;
		String cCode, cName, info;
		boolean BS, MS, PhD, RE;
		
		
		System.out.print("\nTo add a new teaching request, answer the following 10 questions!\n\n"
				+ "\t1. Enter a unique teaching request ID (must be an integer): ");
		
		
	    if (Main.s.hasNextInt()) { //must validate because this is primary key
	        ID = Main.s.nextInt();
	        Main.s.nextLine();
	        if(TeachingRequirementDB.getTeachingRequirement(ID) instanceof TeachingRequirement) {
	        	System.out.println("\nInvalid input, ID is not unique");
	        	askRequest();
	        }
	    } else {
        System.out.println("\nInvalid input. ID number must be an integer.");
        askRequest();
	    }
		
		System.out.print("\t2. Enter course code (Example: \"COMPSCI 5059\"): ");
		cCode = stringInput();
		
		System.out.print("\t3. Enter course name (Example: \"Software Engineering\"): ");
		cName = stringInput();
		
		System.out.print("\t4. Enter salary as a plain integer (Example: \"40000\"): ");
		salary = intInput();
				
		
		System.out.print("\t5. Bachelor's Degree required (enter true or false): ");
		BS = booleanInput();
		
		System.out.print("\t6. Master's Degree required (enter true or false): ");
		MS = booleanInput();
		
		System.out.print("\t7. Doctorate required (enter true or false): ");
		PhD = booleanInput();
		
		System.out.print("\t8. Research experience required (enter true or false): ");
		RE = booleanInput();
		
		System.out.print("\t9. How many hours per week will this position entail (enter integer): ");
		hours = intInput();
		
		System.out.print("\t10. Enter any additional information about the position: ");
		info = stringInput();
		
		TeachingRequirement TR = new TeachingRequirement(ID,new Course(cCode,cName),salary,new Education(BS,MS,PhD,RE),hours,info);
		TeachingRequirementDB.addTeachingRequirement(TR);
		
		askRequest();
		
	}
	
	public static void viewTeachingRequirement() {
		int ID = askTeachingRequestID();
		TeachingRequirement TR = TeachingRequirementDB.getTeachingRequirement(ID);
		if(TR!=null){
			System.out.println(TR.internalVerticalView());
		} else {
			System.out.println("\nGiven ID does not match any Teaching Requirement.");
		}
		askRequest();
		
	}
	
	public static void viewAllTeachingRequirements() {
		String header = String.format("\n----------------------------------------------------------------------------------------------------------------------------\n"
				+ "%-7s|%-14s|%-20s|%-8s|%-9s|%-7s|%-8s|%-6s|%-5s|%-11s|%-5s|%-8s",
    			"Req ID","Code","Course Name","isFilled","TeacherID","Salary","Bachelor","Master","PhD","Research Ex",
    			"Hours","Info");
		System.out.println(header);
		for(TeachingRequirement TR:TeachingRequirementDB.getTeachingRequirementDB()) {
			System.out.println(TR.internaHorizontalView());
		}
		System.out.println("----------------------------------------------------------------------------------------------------------------------------");
		
		askRequest();
	}
	
	public static void deleteTeachingRequirement() {
		int ID = askTeachingRequestID();
		TeachingRequirement TR = TeachingRequirementDB.getTeachingRequirement(ID);
		if(TR!=null){
			System.out.println(TR.internalVerticalView());
			TeachingRequirementDB.removeTeachingRequirement(TR);
			System.out.println("\nRequirement Deleted!");
		} else {
			System.out.println("\nGiven ID does not match any Teaching Requirement.");
		}
		
		askRequest();
	}
	
	public static int askTeachingRequestID() {
		
		System.out.print("\nEnter teaching request ID number: ");
		int ID = Main.s.nextInt();
		Main.s.nextLine();
		
		return ID;
	}
	
	
	public static int intInput() {
		String sString = Main.s.nextLine();
		if(sString.length()>0)	{
			return Integer.parseInt(sString);
		} else {
			return 0;
		}
	}
	
	public static boolean booleanInput() {
		String sString = Main.s.nextLine();
		if(sString.equals("true"))	{
			return true;
		} else {
			return false;
		}
	}
	
	public static String stringInput() {
		String sString = Main.s.nextLine();
		if(sString.length()>0)	{
			return sString;
		} else {
			return "0";
		}
	}
	

}
