/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.resource;

import com.example.dao.DoctorDAO;
import com.example.exception.UserNotFoundException;
import com.example.model.Doctor;
import com.example.model.Person;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Define a RESTful endpoint with base path "/doctors"
@Path("/doctors")
public class DoctorResource {

    // Logger instance for logging messages.
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonResource.class);
    // Instantiate the PersonDAO to interact with the data layer.
    private DoctorDAO doctorDAO = new DoctorDAO();

    //GET endpoint to retrieve a list of all doctors.
    @GET
    @Produces(MediaType.APPLICATION_JSON)//specifies that the output media type as JSON format.
    public Response getAllDoctors() {

        try {
            List<Doctor> doctors = doctorDAO.getAllDoctors();
            // If the list is empty or null, throw a custom exception
            if (doctors == null || doctors.isEmpty()) {
                throw new UserNotFoundException("No doctors found.");
            }
            // Return a successful response with the list of doctors
            return Response.ok(doctors).build();
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error retrieving all doctors: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
        }
    }

    //GET endpoint to retrieve a specific doctor by their ID.
    @GET
    @Path("/{doctorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDoctorById(@PathParam("doctorId") int doctorId) {
        try {
            Doctor doctor = doctorDAO.getDoctorById(doctorId);
            if (doctor != null) {
                // If the doctor is found, return it as a successful response
                return Response.ok(doctor).build();
            } else {
                // Log a warning if the doctor was not found and return a 404 response
                LOGGER.warn("User not found: {}");
                return Response.status(Response.Status.NOT_FOUND).entity("User not found.").build();
            }
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error retrieving doctor by ID: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
        }
    }

    //GET endpoint to retrieve a specific doctor by their Name.
    @GET
    @Path("/name/{doctorName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDoctorByName(@PathParam("doctorName") String doctorName) {
        try {
            Doctor doctor = doctorDAO.getDoctorByName(doctorName);
            if (doctor != null) {
                // If the doctor is found, return it as a successful response
                return Response.ok(doctor).build();
            } else {
                // Log a warning if the doctor was not found and return a 404 response
                LOGGER.warn("User not found: {}");
                return Response.status(Response.Status.NOT_FOUND).entity("User not found.").build();
            }
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error retrieving doctor by Name: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred.").build();
        }
    }

    // POST endpoint to add a new doctor
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDoctor(Doctor doctor) {
        try {
            // Add a new doctor
            doctorDAO.addDoctor(doctor);
            // Return a 201 Created response to indicate success
            return Response.status(Response.Status.CREATED).entity("Doctor added successfully.").build();
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error adding doctor: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred.").build();
        }
    }

    //UPDATE endpoint to update a doctor by their ID.
    @PUT
    @Path("/{doctorId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDoctor(@PathParam("doctorId") int doctorId, Doctor updatedDoctor) {
        try {
            // Retrieve the existing doctor to update
            Doctor existingDoctor = doctorDAO.getDoctorById(doctorId);
            if (existingDoctor != null) {
                //If the doctor exists,Updated doctor is set to the existing doctor's id and update the doctor.
                updatedDoctor.setId(doctorId);
                doctorDAO.updateDoctor(updatedDoctor);
                // Return a successful response indicating the update was successful
                return Response.status(Response.Status.OK).entity("Doctor with ID " + doctorId + " updated successfuly.").build();
            } else {
                // Log a warning if the doctor was not found and return a 404 response
                LOGGER.warn("User not found: {}");
                return Response.status(Response.Status.NOT_FOUND).entity("User not found.").build();
            }
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error updating doctor: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
        }
    }

    //DELETE endpoint to remove a doctor by their ID.
    @DELETE
    @Path("/{doctorId}")
    public Response deleteDoctor(@PathParam("doctorId") int doctorId) {
        try {
            //Retrieve the existing doctor
            Doctor doctor = doctorDAO.getDoctorById(doctorId);
            if (doctor != null) {
                //If the doctor exists,delete the doctor
                doctorDAO.deleteDoctor(doctorId);
                // Return a successful no-content response indicating deletion
                return Response.noContent().build();
            } else {
                // Log a warning if the doctor was not found and return a 404 response
                LOGGER.warn("Doctor not found: {}", doctorId);
                return Response.status(Response.Status.NOT_FOUND).entity("User not found.").build();
            }
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error deleting doctor: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
        }
    }

    //GET endpoint to retrieve a specific specialization by doctor's name.
    @GET
    @Path("/specialization/name/{doctorName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSpecializationByDoctorName(@PathParam("doctorName") String doctorName) {

        try {
            String specialization = doctorDAO.getSpecializationByName(doctorName);
            if (specialization != null) {
                // If the specialization is found, return it as a successful response
                return Response.ok(specialization).build();
            } else {
                // Log a warning if the specialization was not found and return a 404 response
                LOGGER.warn("Specialization not found: {}");
                return Response.status(Response.Status.NOT_FOUND).entity("Specialization with name '" + doctorName + "' not found.").build();
            }
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error retrieving specialization by Name: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
        }
    }

    //GET endpoint to retrieve a specific doctor by doctor's specialization.
    @GET
    @Path("/specialization/{specialization}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDoctorsBySpecialization(@PathParam("specialization") String specialization) {
        try {
            List<Doctor> doctors = doctorDAO.getDoctorsBySpecialization(specialization);
            if (doctors != null) {
                // If the doctor is found, return it as a successful response
                return Response.ok(doctors).build();
            } else {
                // Log a warning if the doctor was not found and return a 404 response
                LOGGER.warn("Doctor not found: {}");
                return Response.status(Response.Status.NOT_FOUND).entity("No doctors found with specialization '" + specialization + "'.").build();
            }
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error retrieving doctor by ID: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
        }

    }
}
