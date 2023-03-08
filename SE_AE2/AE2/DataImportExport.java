package AE2;

import java.io.File;

public class DataImportExport {

	public static String filePathTR, filePathPTT, filePathTS;
	
	public static void askForFilePaths() {		
		
		System.out.println("Starting program..."
				+ "\nThe program needs file paths to read and write data for different parts of the program."
				+ "\nPlease specify 3 file paths for teaching requirements, part time teacher, and training sessions.");
		/*
		filePathTR = "C:\\users\\chase\\desktop\\testTR.csv";
		FileHandler.readTeachingRequirementFile(filePathTR);
		filePathPTT = "C:\\users\\chase\\desktop\\testPTT.csv";
		FileHandler.readPartTimeTeacherFile(filePathPTT);
		filePathTS = "C:\\users\\chase\\desktop\\testTS.csv";
		FileHandler.readPTTTrainingSessionFile(filePathTS);
		*/
		System.out.print("\n   1-Enter file path for Teaching Requirement data: ");
		filePathTR = getFilePath();
		FileHandler.readTeachingRequirementFile(filePathTR);
		
		System.out.print("\n   2-Enter file path for Part Time Teacher data: ");
		filePathPTT = getFilePath();
		FileHandler.readPartTimeTeacherFile(filePathPTT);
		
		System.out.print("\n   3-Enter file path for Training Session data: ");
		filePathTS = getFilePath();
		FileHandler.readPTTTrainingSessionFile(filePathTS);
		
	}
	
	public static String getFilePath() {
		
		String testPath = Main.s.nextLine();
	    File file = new File(testPath);
	    
	    if (!file.exists()) {
	    	System.out.println("Error! File doesn't exist. Try again...\n");
	    	getFilePath();
	    } 
        return testPath;
	}
	
	public static void saveAndExport() {
		FileHandler.writeTeachingRequirementFile(filePathTR);
		FileHandler.writePartTimeTeacherFile(filePathPTT);
		FileHandler.writePTTTrainingSessionFile(filePathTS);
	}

}
