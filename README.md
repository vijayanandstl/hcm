# eZHire - Easy Hiring Platform

This document provides detailed instructions on how to build, test, run, and configure the microservices within this project.

## Table of Contents
1.  [Project Overview](#project-overview)
2.  [System Architecture](#system-architecture)
3.  [Prerequisites](#prerequisites)
4.  [Environment Setup](#environment-setup)
5.  [Configuration](#configuration)
6.  [Build and Run](#build-and-run)
7.  [Managing Service Processes](#managing-service-processes)
8.  [Testing](#testing)
9.  [API Documentation (OpenAPI)](#api-documentation-openapi)
10. [Troubleshooting](#troubleshooting)

---

## 1. Project Overview

This repository contains a set of microservices for the HCM (Human Capital Management) system.

- **`hcm-parent`:** The main parent POM that manages dependencies, plugins, and properties for all modules.
- **`hcm-common`:** A shared library containing common code used across multiple services, such as JPA entities, DTOs, and utility classes.
- **`hcm-message-broker`:** Handles asynchronous messaging via Apache Kafka for event-driven communication between services.
- **`candidate-service`:** Manages all aspects of a candidate's profile, including skills, education, and work history.
- **`tenant-service`:** Manages tenant (client) information.
- **`organization-service`:** Manages details about organizations or departments.
- **`hcm-core`:** Acts as an API Gateway or facade. It provides a unified REST API for frontend clients, routing requests to the appropriate downstream microservice (`candidate`, `tenant`, etc.).

---

## 2. System Architecture

The services communicate in two primary ways:
- **Synchronous (REST API):** Frontend applications interact with the `hcm-core` service, which then makes synchronous calls to the other microservices.
- **Asynchronous (Events):** Services publish and subscribe to events using Apache Kafka for decoupled, event-driven communication (e.g., a new candidate creation event).

---

## 3. Prerequisites

- **Java 17:** Ensure you have JDK 17 installed and that your `JAVA_HOME` environment variable is set correctly.
- **Apache Maven:** This project uses Maven for dependency management and builds.
- **PostgreSQL:** A running instance of PostgreSQL is required for data persistence.
- **Apache Kafka:** A running Kafka broker is required for the messaging system.
- **Zipkin (Optional):** The services are configured to send trace data to a Zipkin instance on `localhost:9411`. If you do not run Zipkin, tracing can be disabled in the configuration.

---

## 4. Environment Setup

### Database
Each service that requires a database connection is configured to connect to a PostgreSQL database named `stl_hcm_db` and use the `hcm` schema.

You must create the database and schema in your PostgreSQL instance before running the applications.
```sql
CREATE DATABASE stl_hcm_db;
-- Connect to the new database, then run:
CREATE SCHEMA hcm;
```

### Kafka
Ensure your Kafka instance is running and accessible at the host and port specified in the `application.properties` files.

---

## 5. Configuration

All service-specific configurations are located in the `src/main/resources/application.properties` file of each module.

The `hcm-core` service acts as a gateway, and its `application.properties` file is particularly important as it contains the URLs for all the downstream services it communicates with (e.g., `services.candidate-url`, `services.tenant-url`, `services.organization-url`).

### Service Ports
| Service                | Path to Properties                                       | Default Port |
| ---------------------- | -------------------------------------------------------- | ------------ |
| `candidate-service`    | `candidate-service/src/main/resources/application.properties` | `8099`       |
| `tenant-service`       | `tenant-service/src/main/resources/application.properties`     | `8098`       |
| `organization-service` | `organization-service/src/main/resources/application.properties` | `8100`       |
| `hcm-core`             | `hcm-core/src/main/resources/application.properties`           | `8085`       |

### Key Properties
Key properties you might need to change include:
- `server.port`: The port for the service.
- `spring.datasource.url`: The JDBC connection URL for PostgreSQL.
- `spring.datasource.username` / `password`: Database credentials.
- `spring.kafka.bootstrap-servers`: The address of the Kafka broker.
- `management.tracing.enabled`: Set to `false` to disable Zipkin tracing if you are not running it.

---

## 6. Build and Run

### Building the Project
To build all microservices, run the following command from the project root directory:
```bash
mvn clean install
```
This command will compile, test, and package each service into a JAR file in its `target` directory. **This command will not start any applications.**

### Running the Services
After building, run any service individually using the `spring-boot:run` goal. For example:
```bash
# To run the candidate service
mvn spring-boot:run -pl candidate-service

# To run the core service
mvn spring-boot:run -pl hcm-core
```
Each service must be run in a separate terminal window.

---

## 7. Managing Service Processes

When running services locally for development, you may need to find the process ID (PID) of a running service to stop it if it doesn't shut down correctly.

### Finding and Stopping a Service (macOS / Linux)

To find the PID of a service running on a specific port (e.g., port `8099`), use the `lsof` command:
```bash
lsof -i :8099
```
This will show you the PID of the process. To stop it, use the `kill` command:
```bash
kill -9 <PID>
```
Replace `<PID>` with the actual process ID.

### Finding and Stopping a Service (Windows)

To find the PID of a service running on a specific port (e.g., port `8099`), use the `netstat` command combined with `findstr`:
```cmd
netstat -ano | findstr :8099
```
The last column in the output is the PID. To stop the process, use the `taskkill` command:
```cmd
taskkill /PID <PID> /F
```
Replace `<PID>` with the actual process ID. The `/F` flag forcefully terminates the process.

---

## 8. Testing
The standard build command executes all unit tests. To only run tests:
```bash
mvn test
```

---

## 9. API Documentation (OpenAPI)

To generate the OpenAPI v3 specifications, you must activate the `generate-openapi` Maven profile. This profile will start each service, generate the API docs, and then stop the service.

Run from the root directory:
```bash
mvn clean install -Pgenerate-openapi
```
This will create `openapi.json` and `openapi.yaml` files in the `src/main/resources` directory of each service with an API.

---

## 10. Troubleshooting

- **Problem:** Running `mvn clean install` starts the applications unexpectedly.
  - **Cause:** The `spring-boot-maven-plugin` has an `<executions>` block in the main `<build>` section of a `pom.xml`.
  - **Solution:** Ensure the `start` and `stop` executions for the plugin are defined **only** within the `generate-openapi` profile for each service's `pom.xml`.

- **Problem:** OpenAPI generation fails with a `401 Unauthorized` error.
  - **Cause:** Spring Security is enabled by default and blocks the plugin.
  - **Solution:** Security has been disabled by excluding `SecurityAutoConfiguration` and `ManagementWebSecurityAutoConfiguration` in the `@SpringBootApplication` annotation of each service's main application class.

- **Problem:** OpenAPI generation fails with a `404 Not Found` error.
  - **Cause:** The `apiDocsUrl` in the `springdoc-openapi-maven-plugin` configuration is incorrect.
  - **Solution:** Ensure the URL includes the correct port and context path (e.g., `/api`). The URL should be `http://localhost:<port>/<context-path>/v3/api-docs`.

- **Problem:** Build fails with a `Connection refused: localhost:9411` error.
  - **Cause:** Distributed tracing is enabled but a Zipkin instance is not running.
  - **Solution:** Disable tracing by setting `management.tracing.enabled=false` in the relevant `application.properties` file.

- **Problem:** Application fails to start with errors related to PostgreSQL `oid` type or `@Lob` annotation.
  - **Cause:** Hibernate's `@Lob` annotation on a `String` field can map to the `oid` type in PostgreSQL, which is deprecated for large objects.
  - **Solution:** Replace the `@Lob` annotation on `String` fields in your JPA entities with `@Column(columnDefinition = "TEXT")`.
