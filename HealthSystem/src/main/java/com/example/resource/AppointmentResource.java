/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.resource;

import com.example.exception.UserNotFoundException;
import com.example.dao.AppointmentDAO;
import com.example.model.Appointment;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Define a RESTful endpoint with base path "/appointments"
@Path("/appointments")
public class AppointmentResource {

    // Logger instance for logging messages.
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonResource.class);
    // Instantiate the AppointmentDAO to interact with the data layer.
    private AppointmentDAO appointmentDAO = new AppointmentDAO();

    //GET endpoint to retrieve a list of all appoinments.
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAppointments() {
        try {
            List<Appointment> appointments = appointmentDAO.getAllAppointments();
            // If the list is empty or null, throw a custom exception
            if (appointments == null || appointments.isEmpty()) {
                throw new UserNotFoundException("No appointments found.");
            }
            // Return a successful response with the list of appointments
            return Response.ok(appointments).build();
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error retrieving all appointments: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
        }
    }

    //GET endpoint to retrieve a specific appointment by their ID.
    @GET
    @Path("/{appointmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppointmentById(@PathParam("appointmentId") int appointmentId) {
        try {
            Appointment appointment = appointmentDAO.getAppointmentById(appointmentId);
            if (appointment != null) {
                // If the appointment is found, return it as a successful response
                return Response.ok(appointment).build();
            } else {
                // Log a warning if the appointment was not found and return a 404 response
                LOGGER.warn("Appointment not found: {}");
                return Response.status(Response.Status.NOT_FOUND).entity("Appointment not found.").build();
            }
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error retrieving appointment by ID: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
        }
    }

    // POST method to add a new appointment
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAppointment(Appointment appointment) {
        try {
            // Add a new appointment
            appointmentDAO.addAppointment(appointment);
            // Return a 201 Created response to indicate success
            return Response.status(Response.Status.CREATED).entity("appointment added successfully.").build();
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error adding appointment: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred.").build();
        }
    }

    //UPDATE method to update a appoinment by their ID.
    @PUT
    @Path("/{appointmentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAppointment(@PathParam("appointmentId") int appointmentId, Appointment updatedAppointment) {
        try {
            // Retrieve the existing appointment to update
            Appointment existingAppointment = appointmentDAO.getAppointmentById(appointmentId);
            if (existingAppointment != null) {
                //If the appointment exists,Updated appointment is set to the existing appointment's id and update the appointment.
                updatedAppointment.setId(appointmentId);
                appointmentDAO.updateAppointment(updatedAppointment);
                // Return a successful response indicating the update was successful
                return Response.status(Response.Status.OK).entity("appointment with ID " + appointmentId + " updated successfuly.").build();
            } else {
                // Log a warning if the appointment was not found and return a 404 response
                LOGGER.warn("appointment not found: {}");
                return Response.status(Response.Status.NOT_FOUND).entity("appointment not found.").build();
            }
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error updating appointment: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
        }
    }

    //DELETE endpoint to delete a specific appointment by appointment's id.
    @DELETE
    @Path("/{appointmentId}")
    public Response deleteAppointment(@PathParam("appointmentId") int appointmentId) {
        try {
            //Retrieve the existing appointment
            Appointment appointment = appointmentDAO.getAppointmentById(appointmentId);
            if (appointment != null) {
                //If the appointment exists,delete the appointment
                appointmentDAO.deleteAppointment(appointmentId);
                // Return a successful no-content response indicating deletion
                return Response.noContent().build();
            } else {
                // Log a warning if the appointment was not found and return a 404 response
                LOGGER.warn("appointment not found: {}", appointmentId);
                return Response.status(Response.Status.NOT_FOUND).entity("appointment not found.").build();
            }
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error deleting appointment: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
        }
    }
}

