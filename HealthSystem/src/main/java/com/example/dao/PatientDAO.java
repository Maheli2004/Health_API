/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.model.Patient;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PatientDAO {

    // Logger instance for logging messages
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientDAO.class);

    // Static list of patient objects representing a data storage
    private static final List<Patient> patients = new ArrayList<>();

    // Static block to initialize some default patient entries in the list
    static {
        LOGGER.info("Initializing PatientDAO with default patients");
        patients.add(new Patient("None", "Healthy", 1, "Binaga", 123456789, "Kandy"));
        patients.add(new Patient("None", "Stable", 2, "Sasindu", 876543210, "Mathara"));

        patients.add(new Patient("Diabetes and hypertension", "Requires monitoring", 3, "Siheli", 723449234, "Colombo"));
        patients.add(new Patient("Chronic back pain", "Under treatment", 4, "Vihansa", 192837465, "Horana"));
    }

    //Method to retrieve all patients
    public List<Patient> getAllPatients() {
        // Log an message at INFO level indicating the action being performed.
        LOGGER.info("Getting all patients");
        return patients;
    }

    //Method to retrieve a patient by ID
    public Patient getPatientById(int id) {
        // Log a message at DEBUG level indicating the patient being searched.
        LOGGER.debug("Searching for patient with ID: {}", id);
        // Loop through the list to find a patient with the specified ID
        for (Patient patient : patients) {
            if (patient.getId() == id) {

                // Log a success message
                LOGGER.info("patient with ID: {} found", id);
                return patient;
            }
        }
        //Return null if the patient is not found
        LOGGER.warn("patient with ID {} not found", id);
        return null;
    }

    //Method to add a new patient
    public void addPatient(Patient patient) {
        // Generate a unique ID for the new patient
        int newId = getNextPatientId();
        patient.setId(newId);
        // Add the new patient to the list
        patients.add(patient);
        // Log a success message
        LOGGER.info("New patient with ID: {} added", patient.getName());
    }

    //Method to update an existing patient
    public void updatePatient(Patient updatedPatient) {
        LOGGER.debug("Updating patient with ID: {}", updatedPatient.getId());
        // Iterate through the list to find the patient to update
        for (int i = 0; i < patients.size(); i++) {
            Patient currentPatient = patients.get(i);
            // If a matching ID is found, update the patient
            if (currentPatient.getId() == updatedPatient.getId()) {
                patients.set(i, updatedPatient);
                //Log an message at INFO level indicating the ID of the patient being updated.
                LOGGER.info("patient with ID: {} updated", updatedPatient.getId());
                return;
            }
        }
        // Log a warning message
        LOGGER.warn("patient with ID {} not found", updatedPatient.getId());
    }

    //Method to delete a patient by ID
    public void deletePatient(int id) {
        // Remove patient with the specified id
        patients.removeIf(patient -> patient.getId() == id);
        //Log an message at INFO level indicating the ID of the patient being deleted.
        LOGGER.info("patient with ID {} deleted", id);
    }

    //Method to get the next available patient ID
    public int getNextPatientId() {
        LOGGER.debug("Calculating next available ID");
        // Initialize maxUserId with a value lower than any possible userId
        int maxPatientId = Integer.MIN_VALUE;
        // Iterate through the list to find the maximum userId
        for (Patient patient : patients) {
            int patientId = patient.getId();
            if (patientId > maxPatientId) {
                maxPatientId = patientId;
            }
        }
        // Increment the maximum userId to get the next available userId
        return maxPatientId + 1;
    }

}
