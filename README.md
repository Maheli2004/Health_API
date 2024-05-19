Java Packages
Java packages offer a methodical way to categorize and retrieve related components, as well as a 
crucial organizational structure for organizing classes within a project. Additionally, this helps to 
prevent naming conflicts and facilitates management and maintenance of the code. The project is 
organized into multiple packages for the purpose of implementing this health system API to
facilitate maintainability, effectiveness, clarity, and modularity.
The following packages are included in this health API:
4.1 com.example.resource package
Resource classes for managing RESTful endpoints connected to different entities in our Health 
System API will be included in this java package.
Resource Classes:
• AppointmentResource
• BillingResource
• MedicalRecordResource
• PatientResource
• PersonResource
• PrescriptionResource
• DoctorResource
These classes will cover how to create, retrieve, update, and remove resources related to 
appointments, bills, medical records, patients, persons, doctors, and prescriptions.
4.2 com.example.dao package
The Data Access Object (DAO) classes in this package are responsible for interacting with the 
database and executing CRUD (Create, Read, Update, Delete) actions on the relevant entities.
This package will contain Data Access Object (DAO) classes responsible for interacting with the 
database and performing CRUD (Create, Read, Update, Delete) operations on the corresponding 
entities.
DAO Classes:
• AppointmentDAO
• BillingDAO
• MedicalRecordDAO
• PatientDAO
• PersonDAO
• PrescriptionDAO
• DoctorDAO
Database actions specific to each DAO class's related entity will be encapsulated, with methods 
for data retrieval, insertion, modification, and deletion available.
4.3 com.example.model package
Model classes that represent the entities in the system will be contained in this package.
Model Classes:
• Appointment
• Billing
• MedicalRecord
• Patient
• Person
• Prescription
• Doctor
These classes will provide methods for accessing and modifying data, as well as defining the 
attributes, features and behaviors of the corresponding entities.
4.4 com.example.exception package
This package will include custom exception classes and exception mappers for handling 
exceptional situations in this health API system.
Exception Classes:
• UserNotFoundException
• UserNotFoundExceptionMapper
These classes will assist in gently handling and propagating exceptions, giving clients insightful 
error messages and replies.
