/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;
public class Prescription {

    // Private fields to encapsulate the data
    private int id;
    private MedicalRecord medicalRecord;
    private String medication;
    private String dosage;
    private String instructions;

    //Default constructor
    public Prescription() {
    }

    //Parameterized constructor
    public Prescription(int id, MedicalRecord medicalRecord, String medication, String dosage, String instructions) {
        this.id = id;
        this.medicalRecord = medicalRecord;
        this.medication = medication;
        this.dosage = dosage;
        this.instructions = instructions;
    }

    //Getter and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

}
