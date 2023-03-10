package AE2;

import java.util.Scanner;

public class TeachingRequirementBuilder {
    private int teachingRequirementID;
    private Course course;
    private Education requiredEducation;
    private boolean isFilled;
    private int assignedTeacherID;
    private String additionalInfo;
    private int salary;
    private int avgHoursPerWeek;
    private static Scanner s = ScannerSingleton.getInstance().getScanner();
    

    public TeachingRequirementBuilder setTeachingRequirementID(int teachingRequirementID) {
        this.teachingRequirementID = teachingRequirementID;
        return this;
    }

    public TeachingRequirementBuilder setCourse(Course course) {
        this.course = course;
        return this;
    }

    public TeachingRequirementBuilder setRequiredEducation(Education requiredEducation) {
        this.requiredEducation = requiredEducation;
        return this;
    }

    public TeachingRequirementBuilder setIsFilled(boolean isFilled) {
        this.isFilled = isFilled;
        return this;
    }

    public TeachingRequirementBuilder setAssignedTeacherID(int assignedTeacherID) {
        this.assignedTeacherID = assignedTeacherID;
        return this;
    }

    public TeachingRequirementBuilder setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
        return this;
    }

    public TeachingRequirementBuilder setSalary(int salary) {
        this.salary = salary;
        return this;
    }

    public TeachingRequirementBuilder setAvgHoursPerWeek(int avgHoursPerWeek) {
        this.avgHoursPerWeek = avgHoursPerWeek;
        return this;
    }

    public TeachingRequirement build() {
    	
        if (teachingRequirementID <= 0) {
            throw new IllegalStateException("Teaching requirement ID must be a positive integer");
        }
        if (salary <= 0) {
            throw new IllegalStateException("Salary must be a positive integer");
        }
        if (avgHoursPerWeek <= 0) {
            throw new IllegalStateException("Average hours per week must be a positive integer");
        }
        if (course == null) {
            throw new IllegalStateException("Course must not be null");
        }
        if (requiredEducation == null) {
            throw new IllegalStateException("Required education must not be null");
        }
        return new TeachingRequirement(teachingRequirementID, course, isFilled, assignedTeacherID, salary, requiredEducation, avgHoursPerWeek, additionalInfo);
    }
}
