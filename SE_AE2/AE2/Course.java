package AE2;

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
