package AE2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/*
 This class prompts the user for the database files they plan to use, which populate the internal
 data structures. When entered, the program checks that the files and reads them, line-by-line.
 If there are errors, the program notifies a user of an issues on a certain line of a file; however,
 it continues through the rest of the file. Then, it populates the teaching requirements, part time teachers,
 and training sessions from the data. The class also writes to these files with the updated data later on 
 in the program. 
 */

public class FileHandler {
	
	public static String filePathTR, filePathPTT, filePathTS;
	
	private static Scanner s = ScannerSingleton.getInstance().getScanner();
	
	public static void askForFilePaths() {		
		
		String blank = "";
		
		System.out.println("Starting program..."
				+ "\nThe program needs CSV file paths to read and write data for different parts of the program."
				+ "\nPlease specify 3 CSV file paths for teaching requirements, part time teacher, and training sessions.");

		
		AskGetValidate.askForInput("\n(1 of 3)-Enter CSV file path for Teaching Requirement data");
		filePathTR = getFilePath(blank);
		FileHandler.readTeachingRequirementFile(filePathTR);
		
		AskGetValidate.askForInput("\n(2 of 3)-Enter CSV file path for Part-Time Teacher data");
		filePathPTT = getFilePath(blank);
		FileHandler.readPartTimeTeacherFile(filePathPTT);
		
		AskGetValidate.askForInput("\n(3 of 3)-Enter CSV file path for Training Session data");
		filePathTS = getFilePath(blank);
		FileHandler.readPTTTrainingSessionFile(filePathTS);
		
	}
	
	public static String getFilePath(String filePath) {
		
		String testPath = AskGetValidate.getStringInput();
	    File file = new File(testPath);
	    
	    if (!file.exists()) { //Validates that a file exists!
	    	System.out.print("\nError! File doesn't exist. Enter another: ");
	    	getFilePath(filePath);
	    } 
	    return testPath;
	}
	
	public static void saveAndExport() {
		FileHandler.writeTeachingRequirementFile(filePathTR);
		FileHandler.writePartTimeTeacherFile(filePathPTT);
		FileHandler.writePTTTrainingSessionFile(filePathTS);
	}
	
	//Reads the teaching requirement import file and populates the data in the Teaching RequirementDB
	public static void readTeachingRequirementFile(String fileName) {
        String line = "";
        String delimiter = ",";
        int counter = 1;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // read the header row
            String[] headers = br.readLine().split(delimiter);
            
            while ((line = br.readLine()) != null) {
                String[] row = line.split(delimiter);
                counter++;
                
                try {
                int teachingRequirementID = Integer.parseInt(row[0]);             
                Course c = new Course(row[1],row[2]);
                boolean isFilled = Boolean.parseBoolean(row[3]);
                int assignedTeacherID = Integer.parseInt(row[4]);
                int salary = Integer.parseInt(row[5]);
                Education e = new Education(Boolean.parseBoolean(row[6]),
                		Boolean.parseBoolean(row[7]),Boolean.parseBoolean(row[8]),Boolean.parseBoolean(row[9]));
                int hours = Integer.parseInt(row[10]);
                String additionalInfo = row[11];
                
                TeachingRequirement TR = new TeachingRequirement(teachingRequirementID, c, isFilled,
                		assignedTeacherID, salary, e, hours, additionalInfo);
                TeachingRequirementDB.addTeachingRequirement(TR);
                
                } catch (Exception e) {
                System.out.println("\nError: Missing data on line " + counter + " of Teaching Requirement data file."
                			+ "\nNote that this individual Teaching Requirement will not be included in the import."
                			+ "\nContinuing rest of import now...");
                }
            }
        } catch (IOException e) {
            
        } finally {
        	System.out.println("\t\t\tIMPORT COMPLETE!");
        }
	}
	
	//Writes all teaching requirements to export file from TeachingRequirementDB class
	public static void writeTeachingRequirementFile(String fileName) {
        
        // add data to the ArrayList
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            // write the header row

            bw.write("Teaching Requirement ID,Course Code,Course Name,isFilled,Assigned Teacher ID,"
            		+ "Salary,Requires Bachelor,Requires Master,Requires Doctorate,Requires Research Exp,Average Hours, Additional Info\n");
            
            bw.write(TeachingRequirementDB.printTRDB());

            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	//Reads the part-time teacher import file and populates the data in the PartTimeTeacherDB class
	public static void readPartTimeTeacherFile(String fileName) {
        String line = "";
        String delimiter = ",";
        int counter = 1;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // read the header row
            String[] headers = br.readLine().split(delimiter);
            
            while ((line = br.readLine()) != null) {
                String[] row = line.split(delimiter);
                counter++;

                try {
                    int pttID = Integer.parseInt(row[0]);
                    String fName = row[1];
                    String lName = row[2];
                    String dob = row[3];
                    String phone = row[4];
                    Education e = new Education(Boolean.parseBoolean(row[5]),
                    		Boolean.parseBoolean(row[6]),Boolean.parseBoolean(row[7]),Boolean.parseBoolean(row[8]));
                    String doh = row[9];
                    
                    PartTimeTeacher PTT = new PartTimeTeacher(pttID, fName, lName, dob, phone, e, doh);
                    PartTimeTeacherDB.addPartTimeTeacher(PTT);

                
                } catch (Exception e) {
                System.out.println("\nError: Missing data on line " + counter + " of Part Time Teacher data file."
                			+ "\nNote that this individual Part Time Teacher will not be included in the import."
                			+ "\nContinuing rest of import now...");
                }
            }
        } catch (IOException e) {
            
        } finally {
        	System.out.println("\t\t\tIMPORT COMPLETE!");
        }
	}
	
	//Writes all part-time teacher data to export file from PartTimeTeacherDB class
	public static void writePartTimeTeacherFile(String fileName) {
        
        // add data to the ArrayList
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            // write the header row
            bw.write("ID,First Name,Last Name,Date of Birth,Phone Number,Bachelor's Degree,"
				+ "Master's Degree,Doctorate,Research Experience,Date of Hire");
            // write the data rows
            bw.write(PartTimeTeacherDB.printPttDB());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	//Reads the training sesssion data import file and populates the data in the PTTTrainingSessionDB class
	public static void readPTTTrainingSessionFile(String fileName) {
        String line = "";
        String delimiter = ",";
        int counter = 1;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // read the header row
            String[] headers = br.readLine().split(delimiter);
            
            while ((line = br.readLine()) != null) {
                String[] row = line.split(delimiter);
                counter++;
                
                try {
                    int tsID = Integer.parseInt(row[0]);
                    String trainDate = row[1];
                    String trainTime = row[2];
                    String trainLoc = row[3];
                    String trainAttendees = "";
                    
                    if(row.length == 5) {
                    	trainAttendees = row[4];
                    }
                    
                    PTTTrainingSession pttTS = new PTTTrainingSession(tsID, trainDate, trainTime, trainLoc, trainAttendees);
                    PTTTrainingSessionDB.addPTTTrainingSession(pttTS);
                
                } catch (Exception e) {
                System.out.println("\nError: Missing data on line " + counter + " of Part Time Teacher Training Session data file."
                			+ "\nNote that this Training Session will not be included in the import."
                			+ "\nContinuing rest of import now...");
                }

            }
        } catch (IOException e) {
            
        } finally {
        	System.out.println("\t\t\tIMPORT COMPLETE!");
        }
	}
	
	//Writes all training session data to export file from PTTTrainingSessionDB class
	public static void writePTTTrainingSessionFile(String fileName) {
        
        // add data to the ArrayList
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            // write the header row
            bw.write("Training Session ID,Training Date,Training Time,Training Location,Attendee IDs");

            bw.write(PTTTrainingSessionDB.printpttTSDB());
 
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	
}
