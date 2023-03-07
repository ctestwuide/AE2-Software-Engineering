package AE2;

public class PartTimeTeacher extends Person{
	
	private String pttPhoneNumber;
	private Education pttEducation;
	private int pttIDNumber;
	private String dateOfHire;

	public PartTimeTeacher(int pttIDNumber, String firstName, String lastName, String dateOfBirth, 
			String pttPhoneNumber, Education pttEducation, String dateOfHire) {
		super(firstName, lastName, dateOfBirth);
		
		this.pttPhoneNumber = pttPhoneNumber;
		this.pttEducation = pttEducation;
		this.pttIDNumber = pttIDNumber;
		this.dateOfHire = dateOfHire;
	}

	public String getPttPhoneNumber() {
		return pttPhoneNumber;
	}

	public void setPttPhoneNumber(String pttPhoneNumber) {
		this.pttPhoneNumber = pttPhoneNumber;
	}

	public Education getPttEducation() {
		return pttEducation;
	}

	public void setPttEducation(Education pttEducation) {
		this.pttEducation = pttEducation;
	}

	public int getPttIDNumber() {
		return pttIDNumber;
	}

	public void setPttIDNumber(int pttIDNumber) {
		this.pttIDNumber = pttIDNumber;
	}

	public String getDateOfHire() {
		return dateOfHire;
	}

	public void setDateOfHire(String dateOfHire) {
		this.dateOfHire = dateOfHire;
	}
	
	public String toString() {
		
    	return "" + this.pttIDNumber+","+this.getFirstName()+","+this.getLastName()+","+this.getDateOfBirth()+","+this.pttPhoneNumber+","+
    			this.pttEducation.isBachelorDegree()+","+this.pttEducation.isMasterDegree()+","+this.pttEducation.isDoctorate()+","+
    			this.pttEducation.isResearchExperience()+","+this.dateOfHire;
	}
	
	public String internalHorizontalView() {
		return "" + String.format("%-7s|%-10s|%-20s|%-10s|%-13s|%-8s|%-6s|%-5s|%-11s|%-8s",
    			this.pttIDNumber,this.getFirstName(),this.getLastName(),this.getDateOfBirth(),this.pttPhoneNumber,
    			this.pttEducation.isBachelorDegree(),this.pttEducation.isMasterDegree(),this.pttEducation.isDoctorate(),
    			this.pttEducation.isResearchExperience(),this.dateOfHire);
	}

}
