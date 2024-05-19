/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;

/**
 *
 * @author AM
 */
public class Patient extends Person {

    // Private fields to encapsulate the data
    private String medicalHistory;
    private String currentHealthStatus;

    //Default constructor
    public Patient() {
    }

    //Parameterized constructor
    public Patient(String medicalHistory, String currentHealthStatus, int id, String name, int contactNumber, String address) {
        super(id, name, contactNumber, address);
        this.medicalHistory = medicalHistory;
        this.currentHealthStatus = currentHealthStatus;
    }

    //Getter and Setters
    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getCurrentHealthStatus() {
        return currentHealthStatus;
    }

    public void setCurrentHealthStatus(String currentHealthStatus) {
        this.currentHealthStatus = currentHealthStatus;
    }

}
