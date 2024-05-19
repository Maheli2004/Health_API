/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.resource;

import com.example.dao.PersonDAO;
import com.example.model.Person;
import com.example.exception.UserNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

// Define a RESTful endpoint with base path "/persons"
@Path("/persons")
public class PersonResource {

    // Logger instance for logging messages.
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonResource.class);
    // Instantiate the PersonDAO to interact with the data layer.
    private PersonDAO personDAO = new PersonDAO();

    //GET endpoint to retrieve a list of all persons.
    @GET
    @Produces(MediaType.APPLICATION_JSON)//specifies that the output media type as JSON format.
    public Response getAllPersons() {
        try {
            List<Person> persons = personDAO.getAllPersons();
            // If the list is empty or null, throw a custom exception
            if (persons == null || persons.isEmpty()) {
                throw new UserNotFoundException("No persons found.");
            }
            // Return a successful response with the list of persons
            return Response.ok(persons).build();
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error retrieving all persons: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
        }
    }

    //GET endpoint to retrieve a specific person by their ID.
    @GET
    @Path("/{personId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonById(@PathParam("personId") int personId) {
        try {
            Person person = personDAO.getPersonById(personId);
            if (person != null) {
                // If the person is found, return it as a successful response
                return Response.ok(person).build();
            } else {
                // Log a warning if the person was not found and return a 404 response
                LOGGER.warn("User not found: {}", personId);
                return Response.status(Response.Status.NOT_FOUND).entity("User not found.").build();
            }
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error retrieving person by ID: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred while retrieving the person").build();
        }
    }

    // POST method to add a new person
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPerson(Person person) {
        try {
            // Add a new person
            personDAO.addPerson(person);
            // Return a 201 Created response to indicate success
            return Response.status(Response.Status.CREATED).entity(" New person created successfuly.").build();

        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error adding person: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
        }
    }

    //UPDATE method to update a person by their ID.
    @PUT
    @Path("/{personId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(@PathParam("personId") int personId, Person updatedPerson) {
        try {
            // Retrieve the existing person to update
            Person existingPerson = personDAO.getPersonById(personId);
            if (existingPerson != null) {
                //If the person exists,Updated Person is set to the existing person's id and update the person.
                updatedPerson.setId(personId);
                personDAO.updatePerson(updatedPerson);
                // Return a successful response indicating the update was successful
                return Response.status(Response.Status.OK).entity("Person with ID " + personId + " updated successfuly.").build();
            } else {
                // Log a warning if the person was not found and return a 404 response
                LOGGER.warn("User not found: {}", personId);
                return Response.status(Response.Status.NOT_FOUND).entity("User not found.").build();
            }
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error updating person: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
        }
    }

    //DELETE method to remove a person by their ID.
    @DELETE
    @Path("/{personId}")
    public Response deletePerson(@PathParam("personId") int personId) {
        try {
            //Retrieve the existing person
            Person existingPerson = personDAO.getPersonById(personId);
            if (existingPerson != null) {
                //If the person exists,delete the person
                personDAO.deletePerson(personId);
                // Return a successful no-content response indicating deletion
                return Response.noContent().build();
            } else {
                // Log a warning if the person was not found and return a 404 response
                LOGGER.warn("Person not found: {}", personId);
                return Response.status(Response.Status.NOT_FOUND).entity("User not found.").build();
            }
        } catch (Exception e) {
            // Log the error and return an internal server error response
            LOGGER.error("Error deleting person: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
        }
    }
}
