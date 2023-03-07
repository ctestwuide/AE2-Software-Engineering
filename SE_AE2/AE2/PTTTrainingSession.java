package AE2;


public class PTTTrainingSession {
	
	private int trainingSessionID;
	private String trainingDate;
	private String trainingTime;
	private String trainingLocation;
	private String attendees;

	
	public PTTTrainingSession(int ID, String trainingDate, String trainingTime, String trainingLocation, String attendees) {
		this.trainingSessionID = ID;
		this.trainingDate = trainingDate;
		this.trainingTime = trainingTime;
		this.trainingLocation = trainingLocation;
		this.attendees = attendees;

	}

	public String getTrainingDate() {
		return trainingDate;
	}

	public void setTrainingDate(String trainingDate) {
		this.trainingDate = trainingDate;
	}

	public String getTrainingTime() {
		return trainingTime;
	}

	public void setTrainingTime(String trainingTime) {
		this.trainingTime = trainingTime;
	}

	public String getTrainingLocation() {
		return trainingLocation;
	}

	public void setTrainingLocation(String trainingLocation) {
		this.trainingLocation = trainingLocation;
	}

	public String getAttendees() {
		return this.attendees;
	}

	public void addAttendee(String attendee) {
		this.attendees += "_" + attendee;
	}

	public int getTrainingSessionID() {
		return trainingSessionID;
	}

	public void setID(int iD) {
		this.trainingSessionID = iD;
	}
	
	public String toString() {
		return ""+this.trainingSessionID+","+this.trainingDate+","
				+this.trainingTime+","+this.trainingLocation+","+this.attendees;
	
	}
	
	public String internalVerticalView() {
		return "" +
				"\n\tTraining Session ID: " +this.trainingSessionID+
				"\n\tDate: " +this.trainingDate+
				"\n\tTime: " +this.trainingTime+
				"\n\tLocation: " +this.trainingLocation+
				"\n\tAttendee IDs: " +this.attendees+
				"\n---------------------------------------------";
	}
	
	public String internalHorizontalView() {
		
		return String.format("%-19d|%-10s|%-5s|%-20s|%-30s",
				this.trainingSessionID,this.trainingDate,this.trainingTime,this.trainingLocation,
				this.attendees);
    			
	}
	

	
	
	
}
