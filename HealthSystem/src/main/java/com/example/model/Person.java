/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;

/**
 *
 * @author AM
 */
public class Person {
    
    // Private fields to encapsulate the data
    private int id;
    private String name;
    private int contactNumber;
    private String address;
    
    //Default constructor for the Person class
    public Person(){
    }

    //Parameterized constructor for the Person class
    public Person(int id, String name, int contactNumber, String address) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
        this.address = address;
    }
    
    //Getter method for retrieving the person's id.
    public int getId() {
        return id;
    }
    
    //Setter method to set the person's id.
    public void setId(int id) {
        this.id = id;
    }
    
    //Getter method for retrieving the person's name.
    public String getName() {
        return name;
    }
    
    //Setter method to set the person's name.
    public void setName(String name) {
        this.name = name;
    }
    
    //Getter method for retrieving the person's Contact Number.
    public int getContactNumber() {
        return contactNumber;
    }
    
    //Setter method to set the person's contact number.
    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }
    
    //Getter method for retrieving the person's address.
    public String getAddress() {
        return address;
    }

    //Setter method to set the person's address.
    public void setAddress(String address) {
        this.address = address;
    }
    
}
