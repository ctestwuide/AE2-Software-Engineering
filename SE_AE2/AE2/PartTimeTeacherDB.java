package AE2;


import java.util.ArrayList;
import java.util.List;

//The database class to hold teachers

public class PartTimeTeacherDB {
	
	private static List<PartTimeTeacher> pttDB = new ArrayList<>();
	
	
		
	public static void addPartTimeTeacher(PartTimeTeacher PTT) {
		pttDB.add(PTT);
	}
	
	public static void removePartTimeTeacher(PartTimeTeacher PTT) {
		pttDB.remove(PTT);
	}
	
	public static List<PartTimeTeacher> getPartTimeTeacherDB(){
		return pttDB;
	}
	
	public static PartTimeTeacher getPartTimeTeacher(int ID) {
		for(PartTimeTeacher PTT:pttDB) {
			if(PTT.getPttIDNumber()==ID) return PTT;
		}
		return null;
	}
	
	public static String printPttDB() {
		String p = "\n";
		for(PartTimeTeacher PTT:pttDB) {
			p += PTT.toString() + "\n";
		}
		
		return p;
	}

}
