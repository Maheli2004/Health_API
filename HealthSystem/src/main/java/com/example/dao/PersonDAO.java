/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.model.Person;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {
    
    // Logger instance for logging messages
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonDAO.class);
    
    // Static list of Person objects representing a data storage
    private static List<Person> persons = new ArrayList<>();
    
    // Static block to initialize some default Person entries in the list
    static {
        LOGGER.info("Initializing PersonDAO with default persons");
        persons.add(new Person(1,"Nathasha" ,1234567890 , "Colombo"));
        persons.add(new Person(2,"Vihansa" ,876543210 , "Rathmalana"));
    }
    
    //Method to retrieve all persons
    public List<Person> getAllPersons() {
        // Log an message at INFO level indicating the action being performed.
        LOGGER.info("Getting all persons");
        return persons;
    }
    
    //Method to retrieve a person by ID
    public Person getPersonById(int id) {
        // Log a message at DEBUG level indicating the person being searched.
        LOGGER.debug("Searching for person with ID: {}", id);
        // Loop through the list to find a Person with the specified ID
        for (Person person : persons) {
            if (person.getId() == id) {
                // Log a success message
                LOGGER.info("Person with ID: {} found", id);
                return person;
            }
        }
        //Return null if the person is not found
        LOGGER.warn("Person with ID {} not found", id);
        return null;
    }
    
    //Method to add a new person
    public void addPerson(Person person) {
        // Generate a unique ID for the new person
        int newId = getNextPersonId();
        person.setId(newId);
        // Add the new Person to the list
        persons.add(person);
        // Log a success message
        LOGGER.info("New person with ID: {} added", person.getName());
    }
    
    //Method to update an existing person
    public void updatePerson(Person updatedPerson) {
        LOGGER.debug("Updating person with ID: {}", updatedPerson.getId());
        // Iterate through the list to find the Person to update
        for (int i = 0; i < persons.size(); i++) {
            Person existingPerson = persons.get(i);
            // If a matching ID is found, update the Person
            if (existingPerson.getId() == updatedPerson.getId()) {
                persons.set(i, updatedPerson);
                //Log an message at INFO level indicating the ID of the person being updated.
                LOGGER.info("Person with ID: {} updated", updatedPerson.getId());
                return;
            }
        }
        // Log a warning message
        LOGGER.warn("Person with ID {} not found", updatedPerson.getId());
    }
    
    //Method to delete a person by ID
    public void deletePerson(int id) {
        // Remove Person with the specified id
        persons.removeIf(person -> person.getId() == id);
        //Log an message at INFO level indicating the ID of the person being deleted.
        LOGGER.info("Person with ID {} deleted", id);
    }
    
    //Method to get the next available person ID
    public int getNextPersonId() {
        LOGGER.debug("Calculating next available ID");
        // Initialize maxUserId with a value lower than any possible userId
        int maxPersonId = Integer.MIN_VALUE;
        // Iterate through the list to find the maximum userId
        for (Person person : persons) {
            int personId = person.getId();
            if (personId > maxPersonId) {
                maxPersonId = personId;
            }
        }
        // Increment the maximum userId to get the next available userId
        return maxPersonId + 1;
    }
        
}    
