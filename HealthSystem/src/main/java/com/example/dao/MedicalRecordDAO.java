/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

/**
 *
 * @author AM
 */
import com.example.model.MedicalRecord;
import com.example.model.Patient;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MedicalRecordDAO {

    // Logger instance for logging messages
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalRecordDAO.class);

    // Static list of medical records objects representing a data storage
    private static final List<MedicalRecord> medicalRecords = new ArrayList<>();

    // Static block to initialize some default medical records entries in the list
    static {
        LOGGER.info("Initializing MedicalRecordDAO with default medical records");
        Patient patient1 = new Patient("None", "Stable", 1, "Senuth", 123456789, "Polonnaruwa");

        medicalRecords.add(new MedicalRecord(1, patient1, "Asthma", "Inhaler"));
        medicalRecords.add(new MedicalRecord(2, patient1, "Hypertension", "Medication"));
    }

    //Method to retrieve all medical records
    public List<MedicalRecord> getAllMedicalRecords() {
        // Log an message at INFO level indicating the action being performed.
        LOGGER.info("Getting all medical records");
        return medicalRecords;
    }

    //Method to retrieve a medical records by ID
    public MedicalRecord getMedicalRecordById(int id) {
        // Log a message at DEBUG level indicating the medical records being searched.
        LOGGER.debug("Searching for medical records with ID: {}", id);
        // Loop through the list to find a medical records with the specified ID
        for (MedicalRecord record : medicalRecords) {
            if (record.getId() == id) {
                // Log a success message
                LOGGER.info("medical records with ID: {} found", id);
                return record;
            }
        }
        //Return null if the medical records is not found
        LOGGER.warn("medical records with ID {} not found", id);
        return null;
    }

    public List<MedicalRecord> getMedicalRecordsByPatient(int patientId) {
        List<MedicalRecord> result = new ArrayList<>();
        for (MedicalRecord record : medicalRecords) {
            if (record.getPatient().getId() == patientId) {
                result.add(record);
            }
        }
        return result.isEmpty() ? null : result;
    }

    //Method to add a new medical records
    public void addMedicalRecord(MedicalRecord record) {
        // Generate a unique ID for the new medical records
        int newRecordId = getNextMedicalRecordId();
        record.setId(newRecordId);
        // Add the new medical records to the list
        medicalRecords.add(record);
        // Log a success message
        LOGGER.info("New medical records with ID: {} added", record.getId());
    }

    //Method to update an existing medical records
    public void updateMedicalRecord(MedicalRecord updatedRecord) {
        LOGGER.debug("Updating medical records with ID: {}", updatedRecord.getId());
        // Iterate through the list to find the medical records to update
        for (int i = 0; i < medicalRecords.size(); i++) {
            // If a matching ID is found, update the medical records
            if (medicalRecords.get(i).getId() == updatedRecord.getId()) {
                medicalRecords.set(i, updatedRecord);
                //Log an message at INFO level indicating the ID of the medical records being updated.
                LOGGER.info("medical records with ID: {} updated", updatedRecord.getId());
                return;
            }
        }
        // Log a warning message
        LOGGER.warn("medical records with ID {} not found", updatedRecord.getId());
    }

    //Method to delete a medical records by ID
    public void deleteMedicalRecord(int id) {
        // Remove medical records with the specified id
        medicalRecords.removeIf(record -> record.getId() == id);
        //Log an message at INFO level indicating the ID of the medical records being deleted.
        LOGGER.info("medical records with ID {} deleted", id);
    }

    //Method to get the next available medical records ID
    public int getNextMedicalRecordId() {
        LOGGER.debug("Calculating next available ID");
        // Initialize maxUserId with a value lower than any possible userId
        int maxRecordId = Integer.MIN_VALUE;
        // Iterate through the list to find the maximum userId
        for (MedicalRecord record : medicalRecords) {
            if (record.getId() > maxRecordId) {
                maxRecordId = record.getId();
            }
        }
        // Increment the maximum userId to get the next available userId
        return maxRecordId + 1;
    }

}
