/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.resource;

import com.example.exception.UserNotFoundException;
import com.example.dao.PrescriptionDAO;
import com.example.model.Prescription;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Define a RESTful endpoint with base path "/prescriptions"
@Path("/prescriptions")
public class PrescriptionResource {

    // Logger instance for logging messages.
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonResource.class);
    // Instantiate the PrescriptionDAO to interact with the data layer.
    private PrescriptionDAO prescriptionDAO = new PrescriptionDAO();

    //GET endpoint to retrieve a list of all prescriptions.
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPrescriptions() {
        try {
            List<Prescription> prescriptions = prescriptionDAO.getAllPrescriptions();
            // If the list is empty or null, throw a custom exception
            if (prescriptions == null || prescriptions.isEmpty()) {
                throw new UserNotFoundException("No prescription found.");
            }
            // Return a successful response with the list of prescription
            return Response.ok(prescriptions).build();
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error retrieving all prescription: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
        }
    }

    //GET endpoint to retrieve a specific prescription by their ID.
    @GET
    @Path("/{prescriptionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrescriptionById(@PathParam("prescriptionId") int prescriptionId) {
        try {
            Prescription prescription = prescriptionDAO.getPrescriptionById(prescriptionId);
            if (prescription != null) {
                // If the prescription is found, return it as a successful response
                return Response.ok(prescription).build();
            } else {
                // Log a warning if the prescription was not found and return a 404 response
                LOGGER.warn("Prescription not found: {}");
                return Response.status(Response.Status.NOT_FOUND).entity("Prescription not found.").build();
            }
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error retrieving prescription by ID: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
        }
    }

    // POST method to add a new prescription
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPrescription(Prescription prescription) {
        try {
            // Add a new prescription
            prescriptionDAO.addPrescription(prescription);
            // Return a 201 Created response to indicate success
            return Response.status(Response.Status.CREATED).entity("prescription added successfully.").build();
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error adding prescription: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred.").build();
        }
    }

    //UPDATE method to update a prescription by their ID.
    @PUT
    @Path("/{prescriptionId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePrescription(@PathParam("prescriptionId") int prescriptionId, Prescription updatedPrescription) {
        try {
            // Retrieve the existing prescription to update
            Prescription existingPrescription = prescriptionDAO.getPrescriptionById(prescriptionId);
            if (existingPrescription != null) {
                //If the prescription exists,Updated prescription is set to the existing prescription's id and update the prescription.
                updatedPrescription.setId(prescriptionId);
                prescriptionDAO.updatePrescription(updatedPrescription);
                // Return a successful response indicating the update was successful
                return Response.status(Response.Status.OK).entity("prescription with ID " + prescriptionId + " updated successfuly.").build();
            } else {
                // Log a warning if the prescription was not found and return a 404 response
                LOGGER.warn("prescription not found: {}");
                return Response.status(Response.Status.NOT_FOUND).entity("prescription not found.").build();
            }
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error updating prescription: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
        }
    }

    //DELETE endpoint to remove a prescription by their ID.
    @DELETE
    @Path("/{prescriptionId}")
    public Response deletePrescription(@PathParam("prescriptionId") int prescriptionId) {
        try {
            //Retrieve the existing prescription
            Prescription prescription = prescriptionDAO.getPrescriptionById(prescriptionId);
            if (prescription != null) {
                //If the prescription exists,delete the prescription
                prescriptionDAO.deletePrescription(prescriptionId);
                // Return a successful no-content response indicating deletion
                return Response.noContent().build();
            } else {
                // Log a warning if the prescription was not found and return a 404 response
                LOGGER.warn("prescription not found: {}", prescriptionId);
                return Response.status(Response.Status.NOT_FOUND).entity("prescription not found.").build();
            }
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error deleting prescription: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
        }
    }

    //GET endpoint to retrieve a specific prescription by their patient name.
    @GET
    @Path("/patient/{patientName}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Prescription> getPrescriptionsByPatientName(@PathParam("patientName") String patientName) {
        return prescriptionDAO.getPrescriptionsByPatientName(patientName);
    }

}
