package AE2;

public class Administrator {

	
	
	//Administrator user menu
	public static void askRequest() {
		int requestType = 0;
		
		do {
			System.out.print("\nWould you like to...\n"
				+ "\t1-Add a training session (enter 1)\n"
				+ "\t2-Assign a teacher to a teaching requirement (enter 2)\n"
				+ "\t3-Assign a teacher to a training sessions (enter 3)\n"
				+ "\t4-Delete a training session (enter 4)\n"
				+ "\t5-View unfilled teaching requirements (enter 5)\n"
				+ "\t6-View all teaching requirements (enter 6)\n"
				+ "\t7-View all part time teachers (enter 7)\n"
				+ "\t8-View training sessions (enter 8)\n"
				+ "\t0-Return to main menu (enter 0)\n"
				+ "\tEnter a number: ");
		
			
			//Validate that the user entered an integer
			if(Main.s.hasNextInt()) {
				requestType = Main.s.nextInt();

			} else {
				System.out.println("\nInvalid input, try again...\n");
			}
			
			Main.s.nextLine(); //Clear the scanner

			
			switch(requestType) {
		    case 1:
		    	addTrainingSession();
		        break;
		    case 2:
		    	assignTeacherToTrainingSession();
		        break;
		    case 3:
		    	assignTeacherToTeachingRequirement();
		        break;
		    case 4:
		    	deleteTrainingSession();
		    	break;
		    case 5:
		    	viewTeachingRequirements(1);
		    	break;
		    case 6:
		    	viewTeachingRequirements(0);
		        break;
		    case 7:
		    	viewPartTimeTeachers();
		        break;
		    case 8:
		    	viewTrainingSession();
		        break;
		    default:
		    	requestType=0;
		    	break;

			}
			
		} while(requestType!=0); //Default is 0, which takes user back to start menu
		
		//Write to database files when exiting the menu
		try {
			FileHandler.saveAndExport();
			System.out.println("\nChanges saved!");
		}finally {
			System.out.println();
			Main.main(null); //exit and return to program start!
		}

	}
	
	
	
	public static void addTrainingSession() {
		int tsID;
		String trainDate, trainTime, trainLocation, attendeeIDs;
		
		System.out.println("\nTo create a training session, answer the following:\n");
		
		System.out.print("\t1. Enter a unique training session ID number: ");
		tsID = intInput();
		
		System.out.print("\t2. Enter a date for the training session (format: DD/MM/YYYY): ");
		trainDate = stringInput();
		
		System.out.print("\t3. Enter a time for the training session (format: HH:MM): ");
		trainTime = stringInput();
		
		System.out.print("\t4. Enter a location for the training session: ");
		trainLocation = stringInput();
		
		System.out.print("\t5. Enter the ID numbers of any trainees (if known) separated by a \"-\" (format: XXXX-XXXX-XX...): ");
		attendeeIDs = stringInput();
		
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
				+ "a valid training session and teacher are required.\n");
		
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
		if(Main.s.hasNextInt()) {
			trID = Main.s.nextInt();
			Main.s.nextLine();
		} else {
			System.out.println("\nInvalid input, try again...");
			Main.s.nextLine();
			getTeachingRequirementID();
		}
		return trID;
	}
	
	
	public static int getPTTID() {
		int pttID;
		System.out.print("\nEnter the teacher ID number for assignment: ");
		
		if(Main.s.hasNextInt()) {
			pttID = Main.s.nextInt();
			Main.s.nextLine();
		} else {
			System.out.println("\nInvalid input, try again...");
			Main.s.nextLine();
			getPTTID();
			pttID=-1;
		}
		
		return pttID;
	}
	
	
	public static int getPTTTrainingSessionID() {
		int tsID;
		System.out.print("\nEnter a training session ID: ");
		if(Main.s.hasNextInt()) {
			tsID = Main.s.nextInt();
			Main.s.nextLine();
		} else {
			System.out.println("\nInvalid input, try again...");
			Main.s.nextLine();
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
	
	public static void createPTT(int pttID, TeachingRequirement TR) {
		String fName, lName, birthday, phone, dateHired;
		boolean bachelor, master, doctorate, research;
		System.out.println("\nNo teacher with this ID number exists. Creating a new teacher...\n");
		
		System.out.print("\t1. Enter teacher's firt name: ");
		fName = stringInput();
		
		System.out.print("\t2. Enter teacher's last name: ");
		lName = stringInput();
		
		System.out.print("\t3. Enter teacher's date of birth (format: DD/MM/YYYY): ");
		birthday = stringInput();
		
		System.out.print("\t4. Enter teacher's phone number: ");
		phone = stringInput();
		
		System.out.print("\t5. Teacher holds a bachelor's degree (true or false): ");
		bachelor = booleanInput();
		
		System.out.print("\t6. Teacher holds a master's degree (true or false): ");
		master = booleanInput();
		
		System.out.print("\t7. Teacher holds a doctorate degree (true or false): ");
		doctorate = booleanInput();
		
		System.out.print("\t8. Teacher has research experience (true or false): ");
		research = booleanInput();
		
		System.out.print("\t9. Enter teacher's date of hire (format: DD/MM/YYYY): ");
		dateHired = stringInput();
		
		
		PartTimeTeacher PTT = new PartTimeTeacher(pttID, fName, lName, birthday, phone, 
				new Education(bachelor, master, doctorate, research), dateHired);
		PartTimeTeacherDB.addPartTimeTeacher(PTT);
		TR.setTeacherID(pttID);
		
		System.out.println("\nPart time teacher was succesfully created and assigned to teaching requirement!");
		
		Administrator.askRequest();
		
	}
	
	
	
	//These methods handle checking for different types of user input
	
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
