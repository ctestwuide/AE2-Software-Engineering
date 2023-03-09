package AE2;

//Each Teaching Requirement has a course/class that is being taught
//This class isn't REQUIRED, but was created when thinking ahead for future changes to system


public class Course {
	
	private String courseName, courseCode;

	public Course(String courseCode, String courseName) {
		this.courseName = courseName;
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String toString() {
		return ""+this.courseCode+","+this.courseName;
	}
}
