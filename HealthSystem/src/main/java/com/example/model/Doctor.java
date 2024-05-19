/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;

/**
 *
 * @author AM
 */
public class Doctor extends Person {

    // Private fields to encapsulate the data
    private String specialization;

    // Default constructor
    public Doctor() {
    }
    //Parameterized constructor

    public Doctor(String specialization, int id, String name, int contactNumber, String address) {
        super(id, name, contactNumber, address);
        this.specialization = specialization;
    }

    //Getter and Setters
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

}
