/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

/**
 *
 * @author AM
 */
import com.example.model.Prescription;
import com.example.model.MedicalRecord;
import com.example.model.Patient;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrescriptionDAO {

    // Logger instance for logging messages
    private static final Logger LOGGER = LoggerFactory.getLogger(PrescriptionDAO.class);

    private static final List<Prescription> prescriptions = new ArrayList<>();

    // Static block to initialize some default prescription entries in the list
    static {
        LOGGER.info("Initializing PrescriptionDAO with default prescriptions");
        MedicalRecord medicalRecord1 = new MedicalRecord(1, new Patient("None", "Healthy", 1, "Charith", 123456789, "Colombo"), "None", "Inhaler");
        MedicalRecord medicalRecord2 = new MedicalRecord(2, new Patient("Diabetes", "Under Control", 2, "Janith", 987654321, "Kandy"), "Diabetes", "Insulin");

        prescriptions.add(new Prescription(1, medicalRecord1, "Acetaminophen", "2 puffs", "As needed"));
        prescriptions.add(new Prescription(2, medicalRecord2, "Melatonin", "500 mg", "Twice daily"));
    }

    //Method to retrieve all prescription
    public List<Prescription> getAllPrescriptions() {
        // Log an message at INFO level indicating the action being performed.
        LOGGER.info("Getting all prescription");
        return prescriptions;

    }

    //Method to retrieve a prescription by ID
    public Prescription getPrescriptionById(int id) {
        // Log a message at DEBUG level indicating the prescription being searched.
        LOGGER.debug("Searching for prescription with ID: {}", id);
        // Loop through the list to find a prescription with the specified ID
        for (Prescription prescription : prescriptions) {
            if (prescription.getId() == id) {
                // Log a success message
                LOGGER.info("prescription with ID: {} found", id);
                return prescription;
            }
        }
        //Return null if the prescription is not found
        LOGGER.warn("prescription with ID {} not found", id);
        return null;
    }

    public List<Prescription> getPrescriptionsByPatientName(String patientName) {
        List<Prescription> result = new ArrayList<>();
        // Loop through the list to find a prescription with the specified ID
        for (Prescription prescription : prescriptions) {
            if (prescription.getMedicalRecord().getPatient().getName().equalsIgnoreCase(patientName)) {
                result.add(prescription);
            }
        }
        return result.isEmpty() ? null : result;
    }

    //Method to add a new prescription
    public void addPrescription(Prescription prescription) {
        // Generate a unique ID for the new prescription
        int newId = getNextPrescriptionId();
        prescription.setId(newId);
        // Add the new prescription to the list
        prescriptions.add(prescription);
        // Log a success message
        LOGGER.info("New prescription with ID: {} added", prescription.getId());
    }

    //Method to update an existing prescription
    public void updatePrescription(Prescription updatedPrescription) {
        LOGGER.debug("Updating prescription with ID: {}", updatedPrescription.getId());
        // Iterate through the list to find the prescription to update
        for (int i = 0; i < prescriptions.size(); i++) {
            Prescription currentPrescription = prescriptions.get(i);
            // If a matching ID is found, update the prescription
            if (currentPrescription.getId() == updatedPrescription.getId()) {
                prescriptions.set(i, updatedPrescription);
                //Log an message at INFO level indicating the ID of the prescription being updated.
                LOGGER.info("prescription with ID: {} updated", updatedPrescription.getId());
                return;
            }
        }
        // Log a warning message
        LOGGER.warn("prescription with ID {} not found", updatedPrescription.getId());
    }

    //Method to delete a prescription by ID
    public void deletePrescription(int id) {
        // Remove prescription with the specified id
        prescriptions.removeIf(prescription -> prescription.getId() == id);
        //Log an message at INFO level indicating the ID of the prescription being deleted.
        LOGGER.info("prescription with ID {} deleted", id);
    }

    //Method to get the next available prescription ID
    public int getNextPrescriptionId() {
        LOGGER.debug("Calculating next available ID");
        // Initialize maxUserId with a value lower than any possible userId
        int maxPrescriptionId = Integer.MIN_VALUE;
        // Iterate through the list to find the maximum userId
        for (Prescription prescription : prescriptions) {
            int prescriptionId = prescription.getId();
            if (prescriptionId > maxPrescriptionId) {
                maxPrescriptionId = prescriptionId;
            }
        }
        // Increment the maximum userId to get the next available userId
        return maxPrescriptionId + 1;
    }

}
