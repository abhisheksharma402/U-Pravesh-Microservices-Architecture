package org.example.common;

public class AdmissionEvent {
    private String applicationID;
    private String studentID;
    private String phone;

    // Constructor
    public AdmissionEvent(String applicationID, String studentID, String phone) {
        this.applicationID = applicationID;
        this.studentID = studentID;
        this.phone = phone;
    }

    // Default constructor
    public AdmissionEvent() {}

    // Getters and Setters
    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "AdmissionEvent{" +
                "applicationID='" + applicationID + '\'' +
                ", studentID='" + studentID + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
