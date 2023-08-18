package com.kashyap.docappointment.model;

public class Appointment {

    private String name;
    private String email;
    private String medicalIssue;
    private long appointmentDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMedicalIssue() {
        return medicalIssue;
    }

    public void setMedicalIssue(String medicalIssue) {
        this.medicalIssue = medicalIssue;
    }

    public long getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(long appointmentDate) {
        this.appointmentDate = appointmentDate;
    }


    public Appointment(String name, String email,  long appointmentDate,String medicalIssue) {
        this.name = name;
        this.email = email;
        this.medicalIssue = medicalIssue;
        this.appointmentDate = appointmentDate;
    }

    // Add getter methods here
}
