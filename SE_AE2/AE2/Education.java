package AE2;

public class Education {
	
	private boolean bachelorDegree, masterDegree, doctorate, researchExperience;

	public Education(boolean bachelorDegree, boolean masterDegree, boolean doctorate, boolean researchExperience) {
		this.bachelorDegree = bachelorDegree;
		this.masterDegree = masterDegree;
		this.doctorate = doctorate;
		this.researchExperience = researchExperience;
	}

	public boolean isBachelorDegree() {
		return bachelorDegree;
	}

	public void setBachelorDegree(boolean bachelorDegree) {
		this.bachelorDegree = bachelorDegree;
	}
	

	public boolean isMasterDegree() {
		return masterDegree;
	}

	public void setMasterDegree(boolean masterDegree) {
		this.masterDegree = masterDegree;
	}
	

	public boolean isDoctorate() {
		return doctorate;
	}

	public void setDoctorate(boolean doctorate) {
		this.doctorate = doctorate;
	}
	

	public boolean isResearchExperience() {
		return researchExperience;
	}

	public void setResearchExperience(boolean researchExperience) {
		this.researchExperience = researchExperience;
	}
	
	
	public String toString() {
		return "" + this.bachelorDegree+ "," + this.masterDegree + "," + 
	this.doctorate + "," + this.researchExperience;
	}

}
