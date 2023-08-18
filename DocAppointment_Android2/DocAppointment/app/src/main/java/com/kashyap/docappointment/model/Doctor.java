package com.kashyap.docappointment.model;

public class Doctor {
    private String name;
    private String specialization;
    private int imageResource; // Resource ID for the doctor's image

    public Doctor(String name, String specialization, int imageResource) {
        this.name = name;
        this.specialization = specialization;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public int getImageResource() {
        return imageResource;
    }

    // Setter methods if required
    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    // Override the toString() method for easier debugging
    @Override
    public String toString() {
        return "Doctor{" +
                "name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", imageResource=" + imageResource +
                '}';
    }
}
