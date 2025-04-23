# Transport Order Management System

This is a Java-based transport order management system built with a layered architecture (Model-Presenter-View).  
The project simulates a simplified internal logistics system for managing drivers, vehicles, clients, and transport orders.

## Overview

The system allows clients to create orders, administrators to manage assignments, and drivers to report progress.  
Data is stored in memory using a singleton-based `InMemoryDataStore`.

### Key Features:

- Clients can create transport orders with pickup/delivery details.
- The system automatically assigns available drivers and vehicles.
- Admins can override these automatic assignments.
- Drivers can report progress through defined order statuses.
- Notifications are triggered based on order events.
- Includes logic for fallback vehicle/driver assignment and different routing strategies based on deadlines.

## Architecture

The project follows a clean separation of concerns:
- **Model** – Data classes and in-memory DAO layer (e.g., `Order`, `VehicleDAO`, `DriverDAO`)
- **Presenter** – Core business logic, service layer, notification strategy
- **View** – Simulated user interface for clients, drivers, and admins (console-based)

## Technologies Used

- Java 17
- Maven
- JUnit 5
- Mockito
