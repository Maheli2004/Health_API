/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.model.Doctor;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    // Logger instance for logging messages
    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorDAO.class);

    // List to store Doctor instances
    private static final List<Doctor> doctors = new ArrayList<>();

    // Static block to initialize with default Doctor entries
    static {
        LOGGER.info("Initializing DoctorDAO with default doctors");
        doctors.add(new Doctor("Cardiology", 1, "Dr. Daham Soysa", 1234567890, "Kurunagale"));
        doctors.add(new Doctor("Dermatology", 2, "Dr. Kosala De Silva", 987654321, "Nugegoda"));
        doctors.add(new Doctor("Orthopedics", 4, "Dr. Manujana", 740852290, "Galle"));
        doctors.add(new Doctor("Orthopedics", 3, "Dr. Samitha", 192837465, "Dehiwala"));

    }

    // Method to retrieve all doctors
    public List<Doctor> getAllDoctors() {
        // Log an message at INFO level indicating the action being performed.
        LOGGER.info("Fetching all doctors");
        return doctors;
    }

    // Method to retrieve a doctor by ID
    public Doctor getDoctorById(int id) {
        // Log a message at DEBUG level indicating the doctor being searched.
        LOGGER.debug("Searching for doctor with ID: {}", id);
        // Loop through the list to find a doctor with the specified ID
        for (Doctor doctor : doctors) {
            if (doctor.getId() == id) {
                // Log a success message
                LOGGER.info("Doctor with ID: {} found", id);
                return doctor;
            }
        }
        //Return null if the doctor is not found
        LOGGER.warn("Doctor with ID: {} not found", id);
        return null;
    }

    // Method to retrieve a doctor by name
    public Doctor getDoctorByName(String name) {
        // Log a message at DEBUG level indicating the doctor being searched.
        LOGGER.debug("Searching for doctor with name: {}", name);
        // Loop through the list to find a doctor with the specified ID
        for (Doctor doctor : doctors) {
            if (doctor.getName().equalsIgnoreCase(name)) {
                // Log a success message
                LOGGER.info("Doctor with name: {} found", name);
                return doctor;
            }
        }
        //Return null if the doctor is not found
        LOGGER.warn("Doctor with name: {} not found", name);
        return null;
    }

    // Method to retrieve doctors by specialization
    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        // Log a message at DEBUG level indicating the doctor being searched.
        LOGGER.debug("Fetching doctors with specialization: {}", specialization);
        List<Doctor> doctorsBySpecialization = new ArrayList<>();
        // Loop through the list to find a doctor with the specified specialization
        for (Doctor doctor : doctors) {
            if (doctor.getSpecialization().equalsIgnoreCase(specialization)) {
                doctorsBySpecialization.add(doctor);
            }
        }
        // Log a success message
        LOGGER.info("Found {} doctors with specialization: {}", doctorsBySpecialization.size(), specialization);
        return doctorsBySpecialization;
    }

    // Method to retrieve specializations by doctor's name
    public String getSpecializationByName(String name) {
        // Log a message at DEBUG level indicating the specialization being searched.
        LOGGER.debug("Fetching specialization with doctor's name: {}", getDoctorByName(name));
        Doctor doctor = getDoctorByName(name);
        if (doctor != null) {
            return doctor.getSpecialization();
        }
        //Return null if the specialization is not found
        LOGGER.warn("Specialization with name: {} not found", name);
        return null;
    }

    // Method to add a new doctor
    public void addDoctor(Doctor doctor) {
        // Generate a unique ID for the new doctor
        int newId = getNextDoctorId();
        doctor.setId(newId);
        // Add the new doctor to the list
        doctors.add(doctor);
        // Log a success message
        LOGGER.info("New doctor added with ID: {}", doctor.getId());
    }

    // Method to update an existing doctor
    public void updateDoctor(Doctor updatedDoctor) {
        LOGGER.debug("Updating doctor with ID: {}", updatedDoctor.getId());
        // Iterate through the list to find the doctor to update
        for (int i = 0; i < doctors.size(); i++) {
            Doctor currentDoctor = doctors.get(i);
            // If a matching ID is found, update the doctor
            if (currentDoctor.getId() == updatedDoctor.getId()) {
                doctors.set(i, updatedDoctor);
                //Log an message at INFO level indicating the ID of the doctor being updated.
                LOGGER.info("Doctor with ID: {} updated", updatedDoctor.getId());
                return;
            }
        }
        // Log a warning message
        LOGGER.warn("Doctor with ID: {} not found for update", updatedDoctor.getId());
    }

    // Method to delete a doctor by ID
    public void deleteDoctor(int id) {
        // Remove doctor with the specified id
        doctors.removeIf(doctor -> doctor.getId() == id);
        //Log an message at INFO level indicating the ID of the doctor being deleted.
        LOGGER.info("Doctor with ID: {} deleted", id);
    }

    // Method to get the next available doctor ID
    public int getNextDoctorId() {
        LOGGER.debug("Calculating next available Doctor ID");
        // Initialize maxUserId with a value lower than any possible userId
        int maxDoctorId = Integer.MIN_VALUE;
        // Iterate through the list to find the maximum userId
        for (Doctor doctor : doctors) {
            int doctorId = doctor.getId();
            if (doctorId > maxDoctorId) {
                maxDoctorId = doctorId;
            }
        }
        // Increment the maximum userId to get the next available userId
        return maxDoctorId + 1;
    }

}
