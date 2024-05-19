/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;

/**
 *
 * @author AM
 */
public class Billing {

    // Private fields to encapsulate the data
    private int id;
    private Patient patient;
    private double totalCost;
    private double amountPaid;
    private double outstandingBalance;

    //Default constructor
    public Billing() {
    }

    //Parameterized constructor
    public Billing(int id, Patient patient, double totalCost, double amountPaid, double outstandingBalance) {
        this.id = id;
        this.patient = patient;
        this.totalCost = totalCost;
        this.amountPaid = amountPaid;
        this.outstandingBalance = outstandingBalance;
    }

    //Getter and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getOutstandingBalance() {
        return outstandingBalance;
    }

    public void setOutstandingBalance(double outstandingBalance) {
        this.outstandingBalance = outstandingBalance;
    }

}
