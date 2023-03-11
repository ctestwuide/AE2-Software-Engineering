package AE2;


public class CourseDirector {
	
	public static void askRequest() {
		int requestType=0;
		
		
		//Menu for class/course directors to take action
		do {
			System.out.print("\nWould you like to...\n"
				+ "\t1-Add Teaching Requirement (enter 1)\n"
				+ "\t2-View a Teaching Requirement (enter 2)\n"
				+ "\t3-View all Teaching Requirements (enter 3)\n"
				+ "\t4-Delete Teaching Requirement (enter 4)\n"
				+ "\t0-Return to main menu (enter 0)\n"
				+ "\tEnter a number: ");
		
			
			requestType = AskGetValidate.getIntInput();
		
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
		    case 0:
		    	break;
		    default:
		    	System.out.println("\nInvalid Input!");
			}

		}while(requestType!=0);
		
		//If exiting and returning to main menu, all data is written to database file
		try {
			FileHandler.writeTeachingRequirementFile(FileHandler.filePathTR);
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
		
		
		System.out.println("\nTo add a new teaching request, answer the following 10 questions!\n");
		
		
		AskGetValidate.askForInput("1. Enter a unique teaching request ID (must be an integer)");
		ID = AskGetValidate.getIntInput();

	    if(TeachingRequirementDB.getTeachingRequirement(ID) instanceof TeachingRequirement) {
        	System.out.println("\nInvalid input, ID is not unique");
        	askRequest();
	    } else if(ID<0) {
        	System.out.println("\nInvalid input, ID can't be negative");
        	askRequest();
	    }
	    
		AskGetValidate.askForInput("2. Enter course code (Example: \"COMPSCI 5059\")");
		cCode = AskGetValidate.getStringInput();
		
		AskGetValidate.askForInput("3. Enter course name (Example: \"Software Engineering\")");
		cName = AskGetValidate.getStringInput();
		
		AskGetValidate.askForInput("4. Enter salary as a plain integer (Example: \"40000\")");
		salary = AskGetValidate.validateIntInput(AskGetValidate.getIntInput());
				 
		AskGetValidate.askForInput("5. Bachelor's Degree required (enter true or false)");
		BS = AskGetValidate.validateBooleanInput(AskGetValidate.getStringInput());
				
		AskGetValidate.askForInput("6. Master's Degree required (enter true or false)");
		MS = AskGetValidate.validateBooleanInput(AskGetValidate.getStringInput());
		
		AskGetValidate.askForInput("7. Doctorate required (enter true or false)");
		PhD = AskGetValidate.validateBooleanInput(AskGetValidate.getStringInput());
		
		AskGetValidate.askForInput("8. Research experience required (enter true or false)");
		RE = AskGetValidate.validateBooleanInput(AskGetValidate.getStringInput());

		AskGetValidate.askForInput("9. How many hours per week will this position entail (enter integer)");
		hours = AskGetValidate.validateIntInput(AskGetValidate.getIntInput());
		
		AskGetValidate.askForInput("10. Enter any additional information about the position");
		info = AskGetValidate.getStringInput();
		
		
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
		
		AskGetValidate.askForInput("Enter teaching request ID number:");
		int ID = AskGetValidate.getIntInput();
		
		return ID;
	}

}
