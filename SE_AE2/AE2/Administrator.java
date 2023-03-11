package AE2;

import java.util.Scanner;

public class Administrator {

	private static Scanner s = ScannerSingleton.getInstance().getScanner();
	
	//Administrator user menu
	public static void askRequest() {
		int requestType = 0;
		
		do {
			System.out.print("\nWould you like to...\n"
				+ "\t1-Add a training session (enter 1)\n"
				+ "\t2-Assign a teacher to a teaching requirement (enter 2)\n"
				+ "\t3-Assign a teacher to a training sessions (enter 3)\n"
				+ "\t4-Create a teacher (enter 4)\n"
				+ "\t5-Delete a training session (enter 5)\n"
				+ "\t6-View unfilled teaching requirements (enter 6)\n"
				+ "\t7-View all teaching requirements (enter 7)\n"
				+ "\t8-View all part time teachers (enter 8)\n"
				+ "\t9-View training sessions (enter 9)\n"
				+ "\t0-Return to main menu (enter 0)\n"
				+ "\tEnter a number: ");
		
			
			requestType = AskGetValidate.getIntInput();

			
			switch(requestType) {
		    case 1:
		    	addTrainingSession();
		        break;
		    case 2:
		    	assignTeacherToTeachingRequirement();
		        break;
		    case 3:
		    	assignTeacherToTrainingSession();
		        break;
		    case 4:
		    	createPTT();
		    	break;
		    case 5:
		    	deleteTrainingSession();
		    	break;
		    case 6:
		    	viewTeachingRequirements(1);
		        break;
		    case 7:
		    	viewTeachingRequirements(0);
		        break;
		    case 8:
		    	viewPartTimeTeachers();
		        break;
		    case 9:
		    	viewTrainingSession();
		    	break;
		    case 0:
		    	break;
		    default:
		    	System.out.println("\nInvalid Input!\n");
		    	break;
			}
			
		} while(requestType!=0); //Default is 0, which takes user back to start menu
		
		//Write to database files when exiting the menu
		try {
			FileHandler.saveAndExport();
			System.out.print("\nChanges saved!");
		}finally {
			System.out.println();
			Main.main(null); //exit and return to program start!
		}

	}
	
	
	
	public static void addTrainingSession() {
		int tsID;
		String trainDate, trainTime, trainLocation, attendeeIDs;
		
		System.out.println("\nTo create a training session, answer the following:\n");
		
		AskGetValidate.askForInput("1. Enter a unique training session ID number (must be integer)");
		tsID = AskGetValidate.validateIntInput(AskGetValidate.getIntInput());
		
		if(PTTTrainingSessionDB.getPTTTrainingSession(tsID) instanceof PTTTrainingSession) {
			System.out.println("Not a unique ID number!");
			askRequest();
		}
		
		AskGetValidate.askForInput("2. Enter a date for the training session (format: DD/MM/YYYY)");
		trainDate = AskGetValidate.getStringInput();
		
		AskGetValidate.askForInput("3. Enter a time for the training session (format: HH:MM)");
		trainTime = AskGetValidate.getStringInput();
		
		AskGetValidate.askForInput("4. Enter a location for the training session");
		trainLocation = AskGetValidate.getStringInput();
		
		AskGetValidate.askForInput("5. Enter the ID numbers of any trainees (if known) separated by a \"_\" (format: XXX_XXX_...)");
		attendeeIDs = AskGetValidate.getStringInput();
		
		PTTTrainingSession pttTS = new PTTTrainingSession(tsID, trainDate, trainTime, trainLocation, attendeeIDs);
		
		PTTTrainingSessionDB.addPTTTrainingSession(pttTS);
		
		askRequest();
	}
	
	
	public static void assignTeacherToTeachingRequirement() {
		int trID;
		int pttID;
		
		TeachingRequirement TR;
		
		trID = getTeachingRequirementID();
		TR = TeachingRequirementDB.getTeachingRequirement(trID);
		
		if(TR==null) {
			System.out.print("\nA teaching requirement with that ID does not exist.\n");
			Administrator.askRequest();
		}
		
		pttID = getPTTID();
		checkPTTID(pttID,TR);

	}
	
	public static void assignTeacherToTrainingSession() {
		int pttID, tsID;
		System.out.println("\nTo assign a part time teacher to a training session,"
				+ " a valid training session and teacher are required.\n");
		
		pttID = getPTTID();
		if(PartTimeTeacherDB.getPartTimeTeacher(pttID)==null) {
			System.out.print("\nA part time teacher with that ID does not exist.\n");
			askRequest();
		}
		
		tsID = getPTTTrainingSessionID();
		if(PTTTrainingSessionDB.getPTTTrainingSession(tsID)==null) {
			System.out.print("\nA training session with that ID does not exist.\n");
			askRequest();
		}
		
		PTTTrainingSessionDB.getPTTTrainingSession(tsID).addAttendee(String.valueOf(pttID));
		System.out.println("Part time teacher was successfully assigned!");
		askRequest();

	}
	
	public static void createPTT(int pttID, TeachingRequirement TR) {
		String fName, lName, birthday, phone, dateHired;
		boolean bachelor, master, doctorate, research;
		System.out.println("\nNo teacher with this ID number exists. Creating a new teacher...\n");
		
		
		AskGetValidate.askForInput("1. Enter teacher's firt name");
		fName = AskGetValidate.getStringInput();
		
		AskGetValidate.askForInput("2. Enter teacher's last name");
		lName = AskGetValidate.getStringInput();
		
		AskGetValidate.askForInput("3. Enter teacher's date of birth (format: DD/MM/YYYY)");
		birthday = AskGetValidate.getStringInput();
		
		AskGetValidate.askForInput("4. Enter teacher's phone number");
		phone = AskGetValidate.getStringInput();
		
		AskGetValidate.askForInput("5. Teacher holds a bachelor's degree (true or false)");
		bachelor = AskGetValidate.validateBooleanInput(AskGetValidate.getStringInput());
		
		AskGetValidate.askForInput("6. Teacher holds a master's degree (true or false)");
		master = AskGetValidate.validateBooleanInput(AskGetValidate.getStringInput());

		AskGetValidate.askForInput("7. Teacher holds a doctorate degree (true or false)");
		doctorate = AskGetValidate.validateBooleanInput(AskGetValidate.getStringInput());

		AskGetValidate.askForInput("8. Teacher has research experience (true or false)");
		research = AskGetValidate.validateBooleanInput(AskGetValidate.getStringInput());

		AskGetValidate.askForInput("9. Enter teacher's date of hire (format: DD/MM/YYYY)");
		dateHired = AskGetValidate.getStringInput();
		
		
		PartTimeTeacher PTT = new PartTimeTeacher(pttID, fName, lName, birthday, phone, 
				new Education(bachelor, master, doctorate, research), dateHired);
		PartTimeTeacherDB.addPartTimeTeacher(PTT);
		TR.setTeacherID(pttID);
		
		System.out.println("\nPart time teacher was succesfully created and assigned to teaching requirement!");
		
		Administrator.askRequest();
		
	}
	
	public static void createPTT() {
		String fName, lName, birthday, phone, dateHired;
		boolean bachelor, master, doctorate, research;
		int pttID;
		
		System.out.println("\nTo create a part-time teacher, enter the following details...\n");
		
		AskGetValidate.askForInput("1. Enter a unique, integer ID number for the part-time teacher");
		pttID = AskGetValidate.validateIntInput(AskGetValidate.getIntInput());
		
	    if(PartTimeTeacherDB.getPartTimeTeacher(pttID) instanceof PartTimeTeacher) {
        	System.out.println("\n\nInvalid input, ID is not unique");
        	askRequest();
	    } else if(pttID<0) {
        	System.out.println("\n\nInvalid input, ID can't be negative");
        	askRequest();
	    }
		
		AskGetValidate.askForInput("2. Enter teacher's firt name");
		fName = AskGetValidate.getStringInput();
		
		AskGetValidate.askForInput("3. Enter teacher's last name");
		lName = AskGetValidate.getStringInput();
		
		AskGetValidate.askForInput("4. Enter teacher's date of birth (format: DD/MM/YYYY)");
		birthday = AskGetValidate.getStringInput();
		
		AskGetValidate.askForInput("5. Enter teacher's phone number");
		phone = AskGetValidate.getStringInput();
		
		AskGetValidate.askForInput("6. Teacher holds a bachelor's degree (true or false)");
		bachelor = AskGetValidate.validateBooleanInput(AskGetValidate.getStringInput());
		
		AskGetValidate.askForInput("7. Teacher holds a master's degree (true or false)");
		master = AskGetValidate.validateBooleanInput(AskGetValidate.getStringInput());

		AskGetValidate.askForInput("8. Teacher holds a doctorate degree (true or false)");
		doctorate = AskGetValidate.validateBooleanInput(AskGetValidate.getStringInput());

		AskGetValidate.askForInput("9. Teacher has research experience (true or false)");
		research = AskGetValidate.validateBooleanInput(AskGetValidate.getStringInput());

		AskGetValidate.askForInput("10. Enter teacher's date of hire (format: DD/MM/YYYY)");
		dateHired = AskGetValidate.getStringInput();
		
		
		PartTimeTeacher PTT = new PartTimeTeacher(pttID, fName, lName, birthday, phone, 
				new Education(bachelor, master, doctorate, research), dateHired);
		PartTimeTeacherDB.addPartTimeTeacher(PTT);
		
		System.out.println("\nPart-time teacher was succesfully created!");
		
		Administrator.askRequest();
		
	}
	
	public static void deleteTrainingSession() {
		int tsID;
		
		System.out.println("\nTo delete a training session...");
		
		tsID = getPTTTrainingSessionID();
		
		if(PTTTrainingSessionDB.getPTTTrainingSession(tsID)==null) {
			System.out.print("\nA training session with that ID does not exist.\n");
			askRequest();
		}
		
		//Get and remove Training Session from DB
		PTTTrainingSessionDB.removePTTTrainingSession(PTTTrainingSessionDB.getPTTTrainingSession(tsID));
		System.out.println("Training Session was succesfully deleted!");
		askRequest();
		
	}
	
	public static void viewTeachingRequirements(int unfilledOnly) {
		String header = String.format("\n----------------------------------------------------------------------------------------------------------------------------\n"
				+ "%-7s|%-14s|%-20s|%-8s|%-9s|%-7s|%-8s|%-6s|%-5s|%-11s|%-5s|%-8s",
    			"Req ID","Code","Course Name","isFilled","TeacherID","Salary","Bachelor","Master","PhD","Research Ex",
    			"Hours","Info");
		System.out.println(header);
		for(TeachingRequirement TR:TeachingRequirementDB.getTeachingRequirementDB()) {
			if(unfilledOnly==1 && TR.getIsFilled()) {
				continue;
			}
			System.out.println(TR.internaHorizontalView());
		}
		System.out.println("----------------------------------------------------------------------------------------------------------------------------");
		
		Administrator.askRequest();

	}
	
	
	public static void viewPartTimeTeachers() {
		String header = String.format("\n--------------------------------------------------------------------------------------------------------------\n"
				+ "%-7s|%-10s|%-20s|%-10s|%-13s|%-8s|%-6s|%-5s|%-11s|%-8s",
    			"pttID","Name","Surname","Birthday","Phone","Bachelor","Master","PhD","Research Ex","Hired");
		
		System.out.println(header);
		for(PartTimeTeacher PTT:PartTimeTeacherDB.getPartTimeTeacherDB()) {
			System.out.println(PTT.internalHorizontalView());
		}
		System.out.println("--------------------------------------------------------------------------------------------------------------");
		
		Administrator.askRequest();
	}
	
	
	public static void viewTrainingSession() {
		String header = String.format("\n-----------------------------------------------------------------------\n"
				+ "%-19s|%-10s|%-5s|%-20s|%-30s",
    			"Training Session ID","Date","Time","Location","Attendee IDs");
		System.out.println(header);
		for(PTTTrainingSession pttTS:PTTTrainingSessionDB.getPTTTrainingSessionDB()) {
			System.out.println(pttTS.internalHorizontalView());
		}
		System.out.println("-----------------------------------------------------------------------");
		
		askRequest();
	}
	
	
	//HELPER METHODS

	
	public static int getTeachingRequirementID() {
		int trID=-1;
		System.out.print("\nEnter the TeachingRequirementID that you'd like to assign a teacher: ");
		if(s.hasNextInt()) {
			trID = s.nextInt();
			s.nextLine();
		} else {
			System.out.println("\nInvalid input, try again...");
			s.nextLine();
			getTeachingRequirementID();
		}
		return trID;
	}
	
	
	public static int getPTTID() {
		int pttID;
		System.out.print("\nEnter the teacher ID number for assignment: ");
		
		if(s.hasNextInt()) {
			pttID = s.nextInt();
			s.nextLine();
		} else {
			System.out.println("\nInvalid input, try again...");
			s.nextLine();
			getPTTID();
			pttID=-1;
		}
		
		return pttID;
	}
	
	
	public static int getPTTTrainingSessionID() {
		int tsID;
		System.out.print("\nEnter a training session ID: ");
		if(s.hasNextInt()) {
			tsID = s.nextInt();
			s.nextLine();
		} else {
			System.out.println("\nInvalid input, try again...");
			s.nextLine();
			getPTTTrainingSessionID();
			tsID=0;
		}
		return tsID;
	}
	
	
	public static void checkPTTID(int pttID, TeachingRequirement TR) {
		
		PartTimeTeacher PTT = PartTimeTeacherDB.getPartTimeTeacher(pttID);;
		
		if(PTT!=null) {
			System.out.println("\nA teacher with this ID number exists and has been "
					+ "assigned to this teaching requirement.");
			TR.setTeacherID(pttID);
			Administrator.askRequest();
		} else {
			createPTT(pttID, TR);
		}
	}
	
}
