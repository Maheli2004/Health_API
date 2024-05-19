/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.resource;

import com.example.exception.UserNotFoundException;
import com.example.dao.BillingDAO;
import com.example.model.Billing;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Define a RESTful endpoint with base path "/billings"
@Path("/billings")
public class BillingResource {

    // Logger instance for logging messages.
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonResource.class);
    // Instantiate the BillingDAO to interact with the data layer.
    private final BillingDAO billingDAO = new BillingDAO();

    //GET endpoint to retrieve a list of all billings.
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBillings() {
        try {
            List<Billing> billings = billingDAO.getAllBillings();
            // If the list is empty or null, throw a custom exception
            if (billings == null || billings.isEmpty()) {
                throw new UserNotFoundException("No billings found.");
            }
            // Return a successful response with the list of billings
            return Response.ok(billings).build();
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error retrieving all billings: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
        }
    }

    //GET endpoint to retrieve a specific billing by their ID.
    @GET
    @Path("/{billingId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBillingById(@PathParam("billingId") int billingId) {
        try {
            Billing billing = billingDAO.getBillingById(billingId);
            if (billing != null) {
                // If the billing is found, return it as a successful response
                return Response.ok(billing).build();
            } else {
                // Log a warning if the billing was not found and return a 404 response
                LOGGER.warn("Billing not found: {}");
                return Response.status(Response.Status.NOT_FOUND).entity("Billing not found.").build();
            }
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error retrieving billing by ID: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
        }
    }

    // POST endpoint to add a new billing
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBilling(Billing billing) {
        try {
            // Add a new billing
            billingDAO.addBilling(billing);
            // Return a 201 Created response to indicate success
            return Response.status(Response.Status.CREATED).entity("billing added successfully.").build();
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error adding billing: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred.").build();
        }
    }

    //UPDATE endpoint to update a billing by their ID.
    @PUT
    @Path("/{billingId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBilling(@PathParam("billingId") int billingId, Billing updatedBilling) {
        try {
            // Retrieve the existing billing to update
            Billing existingBilling = billingDAO.getBillingById(billingId);
            if (existingBilling != null) {
                //If the billing exists,Updated billing is set to the existing billing's id and update the billing.
                updatedBilling.setId(billingId);
                billingDAO.updateBilling(updatedBilling);
                // Return a successful response indicating the update was successful
                return Response.status(Response.Status.OK).entity("billing with ID " + billingId + " updated successfuly.").build();
            } else {
                // Log a warning if the billing was not found and return a 404 response
                LOGGER.warn("billing not found: {}");
                return Response.status(Response.Status.NOT_FOUND).entity("billing not found.").build();
            }
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error updating billing: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
        }
    }

    //DELETE endpoint to remove a billing by their ID.
    @DELETE
    @Path("/{billingId}")
    public Response deleteBilling(@PathParam("billingId") int billingId) {
        try {
            //Retrieve the existing billing
            Billing billing = billingDAO.getBillingById(billingId);
            if (billing != null) {
                //If the billing exists,delete the billing
                billingDAO.deleteBilling(billingId);
                // Return a successful no-content response indicating deletion
                return Response.noContent().build();
            } else {
                // Log a warning if the billing was not found and return a 404 response
                LOGGER.warn("billing not found: {}", billingId);
                return Response.status(Response.Status.NOT_FOUND).entity("billing not found.").build();
            }
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error deleting billing: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
        }
    }

    //GET endpoint to retrieve a specific bil by patient's name.
    @GET
    @Path("/patient/{patientName}")
    @Produces(MediaType.APPLICATION_JSON)

    public List<Billing> getBilling(@PathParam("patientName") String patientName) {
        return billingDAO.getPrescriptionsByPatientName(patientName);
    }

}
