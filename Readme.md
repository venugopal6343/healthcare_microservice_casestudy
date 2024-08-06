# Case Study: Healthcare Appointment Scheduling System

## Overview

This case study involves designing and implementing a microservices-based architecture for a healthcare appointment scheduling system. The system has three main services: Patient Service, Doctor Service, and Appointment Service. The Patient Service manages patient information, the Doctor Service handles doctor information, and the Appointment Service schedules and manages appointments between patients and doctors.

Requirements
**Patient Service**

    Create Patient: Registers new patients.
    Get Patient Info: Retrieves information about a specific patient.
    Update Patient Info: Updates patient details.

**Doctor Service**

    Create Doctor: Registers new doctors.
    Get Doctor Info: Retrieves information about a specific doctor.
    Update Doctor Info: Updates doctor details.

**Appointment Service**

    Schedule Appointment: Schedules an appointment between a patient and a doctor.
    Cancel Appointment: Allows patients to cancel appointments.
    Get Appointment Info: Retrieves details about a specific appointment.

**Technologies**

    Java for developing the microservices
    Spring Boot for creating standalone, production-grade Spring applications
    Spring Cloud for building a resilient and scalable microservices architecture
    Eureka for service discovery
    Spring Cloud Gateway for routing and load balancing
    RabbitMQ for asynchronous communication between services
    MySQL for data storage
    Docker for containerization
    Kubernetes for orchestration and managing the deployment

**Architecture**

    Patient Service:
        Exposes REST APIs for creating, retrieving, and updating patient information.

    Doctor Service:
        Exposes REST APIs for creating, retrieving, and updating doctor information.

    Appointment Service:
        Exposes REST APIs for scheduling, canceling, and retrieving appointment details.
        Communicates with Patient Service and Doctor Service to verify availability and details.
        Publishes appointment events to RabbitMQ.

    Service Discovery:
        Uses Eureka for service registration and discovery.

    API Gateway:
        Uses Spring Cloud Gateway to route requests to the appropriate service.

    Asynchronous Communication:
        Uses RabbitMQ for decoupling services and handling events asynchronously.
