package AE2;

import java.util.ArrayList;
import java.util.List;

public class TeachingRequirementDB {
	
	private static List<TeachingRequirement> tRDB = new ArrayList<>();
	
		
	public static void addTeachingRequirement(TeachingRequirement TR) {
		int ID = TR.getTeachingRequirementID();
		if(getTeachingRequirement(ID)==null) {
			tRDB.add(TR);
		}
	}
	
	public static void removeTeachingRequirement(TeachingRequirement TR) {
		tRDB.remove(TR);
	}
	
	public static List<TeachingRequirement> getTeachingRequirementDB(){
		return tRDB;
	}
	
	public static TeachingRequirement getTeachingRequirement(int ID) {
		for(TeachingRequirement TR:tRDB) {
			if(TR.getTeachingRequirementID()==ID) return TR;
		}
		return null;
	}

	
	public static String printTRDB() {
	    String s = "";
	    for (TeachingRequirement TR : tRDB) {
	    	s += TR + "\n";
	    };
	    return s;
	}

}
