package AE2;

import java.util.ArrayList;
import java.util.List;

public class PTTTrainingSessionDB {
	
	private static List<PTTTrainingSession> pttTSDB = new ArrayList<>();
	
		
	public static void addPTTTrainingSession(PTTTrainingSession TS) {
		pttTSDB.add(TS);
	}
	
	public static void removePTTTrainingSession(PTTTrainingSession TS) {
		pttTSDB.remove(TS);
	}
	
	public static List<PTTTrainingSession> getPTTTrainingSessionDB(){
		return pttTSDB;
	}
	
	public static PTTTrainingSession getPTTTrainingSession(int ID) {
		for(PTTTrainingSession TS:pttTSDB) {
			if(TS.getTrainingSessionID()==ID) return TS;
		}
		return null;
	}
	
	public static String printpttTSDB() {
		String p = "\n";
		for(PTTTrainingSession TS:pttTSDB) {
			p += TS.toString() + "\n";
		}
		
		return p;
	}

}