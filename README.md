# Crop Monitoring System

The **Crop Monitoring System** is an enterprise-level Spring Boot application designed to streamline the management of agricultural operations. This system integrates functionalities for overseeing fields, crops, staff, vehicles, equipment, and related monitoring logs, thus supporting comprehensive farm management at scale.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Architecture](#architecture)
- [Data Model](#data-model)
- [Technology Stack](#technology-stack)
- [Prerequisites](#prerequisites)
- [Installation and Setup](#installation-and-setup)
- [Running the Application](#running-the-application)
- [Security and Authentication](#security-and-authentication)
- [API Documentation](#api-documentation)
- [Validation and Error Handling](#validation-and-error-handling)
- [Logging](#logging)
- [Enhancements](#enhancements)
- [License](#license)

## Overview
Green Shadow (Pvt) Ltd. is expanding its agricultural footprint and requires a unified system to manage operational complexity. The Crop Monitoring System centralizes and simplifies the administration of fields, crops, staff assignments, equipment, vehicles, and activity logs. It leverages Spring Boot and a layered architecture to ensure maintainability, scalability, and adherence to industry best practices.

## Features
- **Field Management:** Create, update, and retrieve details of cultivation fields, including geographic coordinates, images, extent, assigned crops, and staff.
- **Crop Management:** Store crop information (common and scientific names, category, season, images) and link each crop to a specific field.
- **Staff Management:** Maintain staff records (roles, designations, personal details) and their assignments to fields, vehicles, and equipment.
- **Monitoring Logs:** Record crop-related activities, observations, and images, as well as associated fields, staff, and crops.
- **Vehicle and Equipment Management:** Assign vehicles and equipment to staff and fields, track availability, and maintain operational status.
- **Role-Based Access Control (RBAC):** Implement authentication and authorization with roles such as MANAGER, ADMINISTRATIVE, SCIENTIST, and OTHER, restricting certain operations as per business rules.
- **Data Analysis Preparedness:** Enhance relational, spatial, and temporal analyses for informed decision-making.

## Architecture
The application follows a three-layer architecture:
1. **API (Controller) Layer:** Exposes RESTful endpoints for CRUD operations and data queries.
2. **Service Layer:** Implements business logic, handles transactional operations, and coordinates between the API and Persistence layers.
3. **Persistence Layer (Repository):** Manages database interactions via Spring Data JPA, ensuring seamless integration with MySQL.

Security, validation, and DTO mapping are handled across these layers, ensuring a clean, maintainable codebase.

## Data Model
The system manages multiple entities:
- **Field:** Includes name, coordinates, extent, images, assigned staff and crops.
- **Crop:** Linked to a field, storing identification data and growth details.
- **Staff:** Includes personal details, roles, and associations with fields, vehicles, and equipment.
- **Monitoring Log:** Records observations and activities for fields and crops, linking them with staff.
- **Vehicle:** Associates staff and fields, with license plate numbers, categories, fuel type, and status.
- **Equipment:** Tracks availability, staff assignments, and optional field assignments.

Foreign keys and proper constraints ensure data integrity and consistency.

## Technology Stack
**Backend:**
- Spring Boot, Spring Data JPA, Spring Web MVC, Spring Security
- Lombok, ModelMapper/MapStruct, Jackson
- JWT-based authentication
- Hibernate for persistence

**Database:**
- MySQL

**Frontend (Optional):**
- HTML, CSS, JavaScript, jQuery, AJAX (for asynchronous requests)

**Build and Dependency Management:**
- Maven

**Documentation:**
- Springdoc OpenAPI/Swagger UI

## Prerequisites
- **Java 17+**
- **Maven 3.8+**
- **MySQL 8.x+**
- Valid JWT tokens for secured endpoints, following the authentication flow provided.

## Installation and Setup
1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/crop_monitoring_system.git
   cd crop_monitoring_system
