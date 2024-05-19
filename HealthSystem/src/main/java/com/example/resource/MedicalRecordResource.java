/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.resource;

import com.example.exception.UserNotFoundException;
import com.example.dao.MedicalRecordDAO;
import com.example.model.MedicalRecord;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Define a RESTful endpoint with base path "/medicalrecords"
@Path("/medicalrecords")
public class MedicalRecordResource {

    // Logger instance for logging messages.
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonResource.class);
    // Instantiate the MedicalRecordDAO to interact with the data layer.
    private MedicalRecordDAO medicalRecordDAO = new MedicalRecordDAO();

    //GET endpoint to retrieve a list of all medicalRecords.
    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public Response getAllMedicalRecords() {
        try {
            List<MedicalRecord> medicalRecords = medicalRecordDAO.getAllMedicalRecords();
            // If the list is empty or null, throw a custom exception
            if (medicalRecords == null || medicalRecords.isEmpty()) {
                throw new UserNotFoundException("No meical records found.");
            }
            // Return a successful response with the list of medical records
            return Response.ok(medicalRecords).build();
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error retrieving all medical records: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
        }
    }

    //GET endpoint to retrieve a specific medical record by their ID.
    @GET
    @Path("/{recordId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMedicalRecordById(@PathParam("recordId") int recordId) {
        try {
            MedicalRecord record = medicalRecordDAO.getMedicalRecordById(recordId);
            if (record != null) {
                // If the medical record is found, return it as a successful response
                return Response.ok(record).build();
            } else {
                // Log a warning if the record was not found and return a 404 response
                LOGGER.warn("Medical record not found: {}");
                return Response.status(Response.Status.NOT_FOUND).entity("Medical record not found.").build();
            }
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error retrieving Medical Record by ID: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
        }
    }

    // POST endpoint to add a new Medical Record
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMedicalRecord(MedicalRecord record) {
        try {
            // Add a new medical record
            medicalRecordDAO.addMedicalRecord(record);
            // Return a 201 Created response to indicate success
            return Response.status(Response.Status.CREATED).entity("Medical Record added successfully.").build();
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error adding Medical Record: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred.").build();
        }
    }

    //UPDATE endpoint to update a medical record by their ID.
    @PUT
    @Path("/{recordId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMedicalRecord(@PathParam("recordId") int recordId, MedicalRecord updatedRecord) {
        try {
            // Retrieve the existing medical record to update
            MedicalRecord existingRecord = medicalRecordDAO.getMedicalRecordById(recordId);
            if (existingRecord != null) {
                //If the medical record exists,Updated medical record is set to the existing medical record's id and update the medical record.
                updatedRecord.setId(recordId);
                medicalRecordDAO.updateMedicalRecord(updatedRecord);
                // Return a successful response indicating the update was successful
                return Response.status(Response.Status.OK).entity("medical record with ID " + recordId + " updated successfuly.").build();
            } else {
                // Log a warning if the medical record was not found and return a 404 response
                LOGGER.warn("User not found: {}");
                return Response.status(Response.Status.NOT_FOUND).entity("medical record not found.").build();
            }
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error updating medical record: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
        }
    }

    //DELETE endpoint to remove a medical record by their ID.
    @DELETE
    @Path("/{recordId}")
    public Response deleteMedicalRecord(@PathParam("recordId") int recordId) {
        try {
            //Retrieve the existing medical record
            MedicalRecord record = medicalRecordDAO.getMedicalRecordById(recordId);
            if (record != null) {
                //If the medical record exists,delete the medical record
                medicalRecordDAO.deleteMedicalRecord(recordId);
                // Return a successful no-content response indicating deletion
                return Response.noContent().build();
            } else {
                // Log a warning if the medical record was not found and return a 404 response
                LOGGER.warn("medical record not found: {}", record);
                return Response.status(Response.Status.NOT_FOUND).entity("medical record not found.").build();
            }
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error deleting medical record: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
        }
    }

    //GET endpoint to retrieve a specific medical record by patient's name.
    @GET
    @Path("/patient/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MedicalRecord> getMedicalRecordsByPatient(@PathParam("patientId") int patientId) {
        return medicalRecordDAO.getMedicalRecordsByPatient(patientId);
    }

}
