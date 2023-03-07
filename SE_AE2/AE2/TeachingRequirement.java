package AE2;

public class TeachingRequirement {
    private int teachingRequirementID;
    private Course course;
    private Education requiredEducation;
    private boolean isFilled;
    private int assignedTeacherID;
    private String additionalInfo;
    private int salary;
    private int avgHoursPerWeek;

    // Constructor    
    public TeachingRequirement(int teachingRequirementID, Course course, int salary, Education requiredEducation, 
    		int avgHoursPerWeek, String additionalInfo) {
        this.teachingRequirementID = teachingRequirementID;
        this.course = course;
        this.requiredEducation = requiredEducation;
        this.isFilled = false;
        this.additionalInfo = additionalInfo;
        this.salary = salary;
        this.avgHoursPerWeek = avgHoursPerWeek;
    }
    
    public TeachingRequirement(int teachingRequirementID, Course course, boolean isFilled, int assignedTeacherID, int salary, Education requiredEducation, 
    		int avgHoursPerWeek, String additionalInfo) {
        this.teachingRequirementID = teachingRequirementID;
        this.course = course;
        this.requiredEducation = requiredEducation;
        this.isFilled = isFilled;
        this.assignedTeacherID = assignedTeacherID;
        this.additionalInfo = additionalInfo;
        this.salary = salary;
        this.avgHoursPerWeek = avgHoursPerWeek;
    }
    

    // Getters and Setters
    public int getTeachingRequirementID() {
        return teachingRequirementID;
    }

    public void setTeachingRequirementID(int teachingRequirementID) {
        this.teachingRequirementID = teachingRequirementID;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Education getRequiredEducation() {
        return requiredEducation;
    }

    public void setRequiredEducation(Education requiredEducation) {
        this.requiredEducation = requiredEducation;
    }

    public boolean getIsFilled() {
        return isFilled;
    }

    public void setFilled(boolean isFilled) {
        this.isFilled = isFilled;
    }

    public int getAssignedTeacherID() {
        return this.assignedTeacherID;
    }

    public void setTeacherID(int ID) {
        this.assignedTeacherID = ID;
        this.isFilled = true;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAvgHoursPerWeek() {
        return avgHoursPerWeek;
    }

    public void setAvgHoursPerWeek(int avgHoursPerWeek) {
        this.avgHoursPerWeek = avgHoursPerWeek;
    }
    
    public String toString() {
    	return  ""+this.teachingRequirementID+","+this.course.getCourseCode()+","+this.course.getCourseName()+","
    			+ this.isFilled+","+this.assignedTeacherID+","+this.salary+","+this.requiredEducation.isBachelorDegree()+","
    			+ this.requiredEducation.isMasterDegree()+","+this.requiredEducation.isDoctorate()+","
    			+ this.requiredEducation.isResearchExperience()+","+this.avgHoursPerWeek+","+this.additionalInfo+",";
 
    }
    
    public String internalVerticalView() {
		return "-------------------------------------------------------------------------------" +
				"\n\tTeaching Requirement ID: " +this.getTeachingRequirementID()+
				"\n\tCourse Code: " +this.getCourse().getCourseCode()+
				"\n\tCourse Name: " +this.getCourse().getCourseName()+
				"\n\tPosition Filled: " +this.getIsFilled()+
				"\n\tAssigned Teacher ID: " +this.getAssignedTeacherID()+
				"\n\tSalary: " +this.getSalary()+
				"\n\tRequires Bachelor's: " +this.getRequiredEducation().isBachelorDegree()+
				"\n\tRequires Master's: " +this.getRequiredEducation().isMasterDegree()+
				"\n\tRequires Doctorate: " +this.getRequiredEducation().isDoctorate()+
				"\n\tRequires Research Experience: " +this.getRequiredEducation().isResearchExperience()+
				"\n\tAverage Hours/Week: " +this.getAvgHoursPerWeek()+
				"\n\tAdditional Information: " +this.getAdditionalInfo()+
				"\n-------------------------------------------------------------------------------";
    }
    
    public String internaHorizontalView() {
    	return String.format("%-7s|%-14s|%-20s|%-8b|%-9s|%-7d|%-8b|%-6b|%-5b|%-11b|%-5d|%-8s",
    	    			this.teachingRequirementID,this.course.getCourseCode(),this.course.getCourseName(),
    	    			this.isFilled,this.assignedTeacherID,this.salary,this.requiredEducation.isBachelorDegree(),
    	    			this.requiredEducation.isMasterDegree(),this.requiredEducation.isDoctorate(),
    	    			this.requiredEducation.isResearchExperience(),this.avgHoursPerWeek,this.additionalInfo);
    }
    
    
}
