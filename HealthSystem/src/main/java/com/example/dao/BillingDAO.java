/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

/**
 *
 * @author AM
 */
import com.example.model.Billing;
import com.example.model.Patient;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BillingDAO {

    // Logger instance for logging messages
    private static final Logger LOGGER = LoggerFactory.getLogger(BillingDAO.class);

    // Static list of billing objects representing a data storage
    private static final List<Billing> billings = new ArrayList<>();

    // Static block to initialize some default billing entries in the list
    static {
        LOGGER.info("Initializing BillingDAO with default billings");
        Patient patient1 = new Patient("None", "Stable", 1, "Senuth", 123456789, "Polonnaruwa");
        Patient patient2 = new Patient("Diabetes", "Stable", 2, "Taniya", 1133590890, "Colombo");

        billings.add(new Billing(1, patient1, 1000.0, 500.0, 500.0));
        billings.add(new Billing(2, patient2, 2000.0, 1000.0, 1000.0));
    }

    //Method to retrieve all billings
    public List<Billing> getAllBillings() {
        // Log an message at INFO level indicating the action being performed.
        LOGGER.info("Getting all billings");
        return billings;
    }

    //Method to retrieve a billing by ID
    public Billing getBillingById(int id) {
        // Log a message at DEBUG level indicating the billing being searched.
        LOGGER.debug("Searching for billing with ID: {}", id);
        // Loop through the list to find a billing with the specified ID
        for (Billing billing : billings) {
            if (billing.getId() == id) {
                // Log a success message
                LOGGER.info("billing with ID: {} found", id);
                return billing;
            }
        }
        //Return null if the billing is not found
        LOGGER.warn("billing with ID {} not found", id);
        return null;
    }

    public List<Billing> getPrescriptionsByPatientName(String patientName) {
        List<Billing> result = new ArrayList<>();
        for (Billing billing : billings) {
            if (billing.getPatient().getName().equalsIgnoreCase(patientName)) {
                result.add(billing);
            }
        }
        return result.isEmpty() ? null : result;
    }

    //Method to add a new billing
    public void addBilling(Billing billing) {
        // Generate a unique ID for the new billing
        int newId = getNextBillingId();
        billing.setId(newId);
        // Add the new billing to the list
        billings.add(billing);
        // Log a success message
        LOGGER.info("New billing with ID: {} added", billing.getId());
    }

    //Method to update an existing billing
    public void updateBilling(Billing updatedBilling) {
        LOGGER.debug("Updating billing with ID: {}", updatedBilling.getId());
        // Iterate through the list to find the billing to update
        for (int i = 0; i < billings.size(); i++) {
            Billing currentBilling = billings.get(i);
            // If a matching ID is found, update the billings
            if (currentBilling.getId() == updatedBilling.getId()) {
                billings.set(i, updatedBilling);
                //Log an message at INFO level indicating the ID of the billing being updated.
                LOGGER.info("billing with ID: {} updated", updatedBilling.getId());
                return;
            }
        }
        // Log a warning message
        LOGGER.warn("billing with ID {} not found", updatedBilling.getId());
    }

    //Method to delete a billing by ID
    public void deleteBilling(int id) {
        // Remove billing with the specified id
        billings.removeIf(billing -> billing.getId() == id);
        //Log an message at INFO level indicating the ID of the billing being deleted.
        LOGGER.info("billing with ID {} deleted", id);
    }

    //Method to get the next available billing ID
    public int getNextBillingId() {
        LOGGER.debug("Calculating next available ID");
        // Initialize maxUserId with a value lower than any possible userId
        int maxBillingId = Integer.MIN_VALUE;
        // Iterate through the list to find the maximum userId
        for (Billing billing : billings) {
            if (billing.getId() > maxBillingId) {
                maxBillingId = billing.getId();
            }
        }
        // Increment the maximum userId to get the next available userId
        return maxBillingId + 1;
    }

}
