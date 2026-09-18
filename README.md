\# Vehicle Rental Management System



\## Project Overview



The Vehicle Rental Management System is a command-line Java program created to manage vehicles, customers and vehicle rentals.



The Vehicle Rental Management System lets customers register, lets users view and search vehicles, lets users rent and return vehicles and keeps records.



An administrator can also manage vehicle inventory and view customer and rental information.



\## Technologies Used



\* Java

\* Object-Oriented Programming

\* Java Collections

\* File Handling

\* Command Line Interface

\* Git and GitHub



\## Main Features



\### Customer Features



1\. Register a customer

2\. View vehicles

3\. Search for a vehicle

4\. Rent a vehicle

5\. Return a rented vehicle

6\. View rental records



\### Administrator Features



1\. Admin authentication

2\. View all vehicles

3\. Add vehicles

4\. Remove vehicles

5\. View all registered customers

6\. View rental records



\### Vehicle Types



The system supports:



\* Cars

\* Bikes



Each vehicle contains information such as:



\* Vehicle ID

\* Brand

\* Model

\* Rental price per day

\* Availability status



Cars additionally store the number of seats while bikes store engine capacity.



\## Object-Oriented Programming Concepts



The project demonstrates Java OOP concepts:



\### Encapsulation



Vehicle, Customer and Rental data are stored in fields, with methods that allow access and changes.



\### Inheritance



`Car` and `Bike` inherit properties and behaviour from the `Vehicle` class.



\### Polymorphism



Vehicle objects can be handled through the `Vehicle` reference while their specific implementations are maintained in the subclasses.



\### Abstraction



Common vehicle behaviour is represented through the base `Vehicle` class.



\## Project Structure



```text

VehicleRentalSystem/



│



├── src/



│   ├── Main.java



│   ├── Vehicle.java



│   ├── Car.java



│   ├── Bike.java



│   ├── Customer.java



│   ├── Rental.java



│   └── VehicleRentalSystem.java



│



├── data/



│   ├── vehicles.txt



│   ├── customers.txt



│   └── rentals.txt



│



└── README.md





