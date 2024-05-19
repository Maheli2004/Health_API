/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

/**
 *
 * @author AM
 */
import com.example.model.Appointment;
import com.example.model.Patient;
import com.example.model.Doctor;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppointmentDAO {

    // Logger instance for logging messages
    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentDAO.class);

    // Static list of appointments objects representing a data storage
    private static final List<Appointment> appointments = new ArrayList<>();

    // Static block to initialize some default appointments entries in the list
    static {
        LOGGER.info("Initializing AppointmentDAO with default appointments");
        Patient patient1 = new Patient("None", "Healthy", 1, "Senudi", 1234567890, "Galle");
        Patient patient2 = new Patient("Diabetes", "Stable", 2, "Taniya", 1133590890, "Colombo");

        Doctor doctor1 = new Doctor("Cardiology", 1, "Dr. Daham Soysa", 1234567890, "Kurunagale");
        Doctor doctor2 = new Doctor("Dermatology", 2, "Dr. Kosala De Silva", 987654321, "Kaluthara");

        appointments.add(new Appointment(1, patient2, doctor2, "2024-05-11", "03:00 PM"));
        appointments.add(new Appointment(2, patient1, doctor1, "2024-05-10", "11:00 AM"));
    }

    //Method to retrieve all appointments
    public List<Appointment> getAllAppointments() {
        // Log an message at INFO level indicating the action being performed.
        LOGGER.info("Getting all appointments");
        return appointments;
    }

    //Method to retrieve a appointments by ID
    public Appointment getAppointmentById(int id) {
        // Log a message at DEBUG level indicating the appointments being searched.
        LOGGER.debug("Searching for appointments with ID: {}", id);
        // Loop through the list to find a appointments with the specified ID
        for (Appointment appointment : appointments) {
            if (appointment.getId() == id) {
                // Log a success message
                LOGGER.info("appointments with ID: {} found", id);
                return appointment;
            }
        }
        //Return null if the appointments is not found
        LOGGER.warn("appointments with ID {} not found", id);
        return null;
    }

    //Method to add a new appointments
    public void addAppointment(Appointment appointment) {
        // Generate a unique ID for the new appointments
        int newId = getNextAppointmentId();
        appointment.setId(newId);
        // Add the new appointments to the list
        appointments.add(appointment);
        // Log a success message
        LOGGER.info("New appointments with ID: {} added", appointment.getId());
    }

    //Method to update an existing appointments
    public void updateAppointment(Appointment updatedAppointment) {
        LOGGER.debug("Updating appointments with ID: {}", updatedAppointment.getId());
        // Iterate through the list to find the appointments to update
        for (int i = 0; i < appointments.size(); i++) {
            Appointment currentAppointment = appointments.get(i);
            // If a matching ID is found, update the appointments
            if (currentAppointment.getId() == updatedAppointment.getId()) {
                appointments.set(i, updatedAppointment);
                //Log an message at INFO level indicating the ID of the appointments being updated.
                LOGGER.info("appointments with ID: {} updated", updatedAppointment.getId());
                return;
            }
        }
        // Log a warning message
        LOGGER.warn("appointments with ID {} not found", updatedAppointment.getId());
    }

    //Method to delete a appointments by ID
    public void deleteAppointment(int id) {
        // Remove appointments with the specified id
        appointments.removeIf(appointment -> appointment.getId() == id);
        //Log an message at INFO level indicating the ID of the appointments being deleted.
        LOGGER.info("appointments with ID {} deleted", id);
    }

    //Method to get the next available appointments ID
    public int getNextAppointmentId() {
        LOGGER.debug("Calculating next available ID");
        // Initialize maxUserId with a value lower than any possible userId
        int maxAppointmentId = Integer.MIN_VALUE;
        // Iterate through the list to find the maximum userId
        for (Appointment appointment : appointments) {
            if (appointment.getId() > maxAppointmentId) {
                maxAppointmentId = appointment.getId();
            }
        }
        // Increment the maximum userId to get the next available userId
        return maxAppointmentId + 1;
    }

}
