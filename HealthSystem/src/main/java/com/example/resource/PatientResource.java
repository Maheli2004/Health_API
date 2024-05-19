/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.resource;

/**
 *
 * @author AM
 */
import com.example.exception.UserNotFoundException;
import com.example.dao.PatientDAO;
import com.example.model.Patient;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Define a RESTful endpoint with base path "/patients"
@Path("/patients")
public class PatientResource {

    // Logger instance for logging messages.
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonResource.class);
    // Instantiate the PersonDAO to interact with the data layer.
    private PatientDAO patientDAO = new PatientDAO();

    //GET endpoint to retrieve a list of all patients.
    @GET
    @Produces(MediaType.APPLICATION_JSON)//specifies that the output media type as JSON format.
    public Response getAllDoctors() {

        try {
            List<Patient> patients = patientDAO.getAllPatients();
            // If the list is empty or null, throw a custom exception
            if (patients == null || patients.isEmpty()) {
                throw new UserNotFoundException("No patients found.");
            }
            // Return a successful response with the list of patients
            return Response.ok(patients).build();
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error retrieving all patients: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
        }
    }

    //GET endpoint to retrieve a specific patient by their ID.
    @GET
    @Path("/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatientById(@PathParam("patientId") int patientId) {
        try {
            Patient patient = patientDAO.getPatientById(patientId);
            if (patient != null) {
                // If the patient is found, return it as a successful response
                return Response.ok(patient).build();
            } else {
                // Log a warning if the patient was not found and return a 404 response
                LOGGER.warn("User not found: {}");
                return Response.status(Response.Status.NOT_FOUND).entity("User not found.").build();
            }
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error retrieving patient by ID: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
        }
    }

    // POST endpoint to add a new patient
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPatient(Patient patient) {
        try {
            // Add a new patient
            patientDAO.addPatient(patient);
            // Return a 201 Created response to indicate success
            return Response.status(Response.Status.CREATED).entity("patient added successfully.").build();
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error adding patient: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred.").build();
        }
    }

    //UPDATE endpoint to update a patient by their ID.
    @PUT
    @Path("/{patientId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePatient(@PathParam("patientId") int patientId, Patient updatedPatient) {
        try {
            // Retrieve the existing patient to update
            Patient existingPatient = patientDAO.getPatientById(patientId);
            if (existingPatient != null) {
                //If the patient exists,Updated patient is set to the existing patient's id and update the patient.
                updatedPatient.setId(patientId);
                patientDAO.updatePatient(updatedPatient);
                // Return a successful response indicating the update was successful
                return Response.status(Response.Status.OK).entity("patient with ID " + patientId + " updated successfuly.").build();
            } else {
                // Log a warning if the patient was not found and return a 404 response
                LOGGER.warn("User not found: {}");
                return Response.status(Response.Status.NOT_FOUND).entity("User not found.").build();
            }
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error updating patient: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
        }
    }

    //DELETE endpoint to remove a patient by their ID.
    @DELETE
    @Path("/{patientId}")
    public Response deletePatient(@PathParam("patientId") int patientId) {
        try {
            //Retrieve the existing patient
            Patient patient = patientDAO.getPatientById(patientId);
            if (patient != null) {
                //If the patient exists,delete the patient
                patientDAO.deletePatient(patientId);
                // Return a successful no-content response indicating deletion
                return Response.noContent().build();
            } else {
                // Log a warning if the patient was not found and return a 404 response
                LOGGER.warn("patient not found: {}", patientId);
                return Response.status(Response.Status.NOT_FOUND).entity("User not found.").build();
            }
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error deleting patient: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
        }
    }

}
