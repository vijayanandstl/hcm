# HCM Candidate Microservice

A microservice for managing candidate information in the Human Capital Management (HCM) system.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Getting Started](#getting-started)
- [Application Properties](#application-properties)
- [Database Schema](#database-schema)
- [API Documentation](#api-documentation)
- [Development](#development)
- [Testing](#testing)
- [Deployment](#deployment)
- [Contributing](#contributing)
- [License](#license)

## Overview

The Candidate Microservice is responsible for managing candidate information, including personal details, education history, work experience, certifications, and skills. It provides RESTful APIs for CRUD operations and integrates with Kafka for event-driven communication.

## Features

- Candidate profile management
- Education history tracking
- Work experience management
- Certification tracking with expiry notifications
- Skill management with proficiency levels
- Event-driven architecture using Kafka
- Database versioning with Liquibase
- Comprehensive audit trail
- Rate limiting and circuit breaker patterns
- Caching support
- Health monitoring and metrics

## Technology Stack

- Java 17
- Spring Boot 3.x
- PostgreSQL 14+
- Kafka 3.x
- Liquibase
- JPA/Hibernate
- Maven
- JUnit 5
- Docker

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.8+
- PostgreSQL 14+
- Kafka 3.x
- Docker (optional)

### Installation

1. Clone the repository:
```bash
git clone https://github.com/your-org/hcm-candidate-service.git
cd hcm-candidate-service
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

## Application Properties

The application uses a comprehensive set of properties for configuration. Here's a detailed breakdown:

### Profile Configuration
```properties
spring.profiles.active=dev
```
- Controls which profile is active (dev, test, prod)
- Determines which properties and configurations are loaded

### Application Configuration
```properties
spring.application.name=candidate-microservice
server.port=8080
server.servlet.context-path=/api
```
- Sets the application name for service discovery
- Configures the server port and context path

### Database Configuration
```properties
spring.datasource.url=jdbc:postgresql://localhost:30032/hcm_db
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.hikari.connection-timeout=20000
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=5
```
- Database connection settings
- Connection pool configuration using HikariCP
- Connection timeout and pool size settings

### JPA Configuration
```properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.default_schema=hcm
```
- JPA/Hibernate settings
- SQL logging and formatting
- Schema configuration

### Liquibase Configuration
```properties
spring.liquibase.enabled=true
spring.liquibase.change-log=classpath:db/changelog/db.changelog-master.xml
spring.liquibase.contexts=dev
spring.liquibase.default-schema=hcm
spring.liquibase.drop-first=false
```
- Database migration settings
- Changelog file location
- Schema and context configuration

### Kafka Configuration
```properties
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.consumer.group-id=hcm-candidate-service-group
spring.kafka.consumer.auto-offset-reset=earliest
```
- Kafka broker settings
- Producer and consumer configurations
- Serialization settings
- Consumer group configuration

### Rate Limiting Configuration
```properties
app.rate-limit.enabled=true
app.rate-limit.capacity=100
app.rate-limit.refill-tokens=100
app.rate-limit.refill-duration=60
```
- Rate limiting settings
- Token bucket configuration
- Request throttling parameters

### Circuit Breaker Configuration
```properties
app.circuit-breaker.enabled=true
app.circuit-breaker.sliding-window-size=10
app.circuit-breaker.failure-rate-threshold=50
app.circuit-breaker.wait-duration-in-open-state=60
```
- Circuit breaker settings
- Failure detection parameters
- Recovery configuration

### Cache Configuration
```properties
spring.cache.type=caffeine
spring.cache.caffeine.spec=maximumSize=500,expireAfterWrite=600s
```
- Cache provider settings
- Cache size and expiration configuration

### Actuator Configuration
```properties
management.endpoints.web.exposure.include=health,metrics,prometheus,info
management.endpoint.health.show-details=always
management.metrics.export.prometheus.enabled=true
```
- Monitoring and metrics settings
- Health check configuration
- Prometheus integration

### Security Configuration
```properties
spring.security.user.name=admin
spring.security.user.password=admin123
```
- Basic authentication settings
- User credentials configuration

### Thread Pool Configuration
```properties
app.thread-pool.core-size=10
app.thread-pool.max-size=20
app.thread-pool.queue-capacity=500
app.thread-pool.keep-alive-seconds=60
```
- Thread pool settings
- Pool size and queue configuration
- Thread lifecycle parameters

### Retry Configuration
```properties
app.retry.max-attempts=3
app.retry.initial-interval=1000
app.retry.multiplier=2.0
app.retry.max-interval=10000
```
- Retry mechanism settings
- Backoff configuration
- Maximum attempts and intervals

### Logging Configuration
```properties
logging.level.root=INFO
logging.level.tech.stl.hcm=DEBUG
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n
```
- Log level settings
- Log pattern configuration
- Package-specific logging

### CORS Configuration
```properties
web.cors.allowed-origins=http://localhost:3000,http://localhost:8080
web.cors.allowed-methods=GET,POST,PUT,DELETE,OPTIONS
web.cors.allowed-headers=Authorization,Content-Type,X-Requested-With
```
- Cross-Origin Resource Sharing settings
- Allowed origins, methods, and headers

### Audit Configuration
```properties
audit.enabled=true
audit.default-auditor=system
```
- Audit trail settings
- Default auditor configuration

## Database Schema

The application uses Liquibase for database versioning. The schema includes:

- Candidate table
- Education history table
- Work history table
- Certification table
- Skills table
- Audit columns

See the `src/main/resources/db/changelog` directory for detailed schema definitions.

## API Documentation

The API documentation is available at `/swagger-ui.html` when running the application.

### Key Endpoints

- `POST /api/candidates` - Create a new candidate
- `GET /api/candidates/{id}` - Get candidate details
- `PUT /api/candidates/{id}` - Update candidate information
- `DELETE /api/candidates/{id}` - Delete a candidate
- `GET /api/candidates/{id}/education` - Get candidate education history
- `GET /api/candidates/{id}/work-history` - Get candidate work history
- `GET /api/candidates/{id}/certifications` - Get candidate certifications
- `GET /api/candidates/{id}/skills` - Get candidate skills

## Development

### Code Style

The project follows Google Java Style Guide. Use the provided Maven plugin to format code:

```bash
mvn spotless:apply
```

### Building

```bash
mvn clean install
```

### Running Tests

```bash
mvn test
```

## Testing

The project includes:

- Unit tests
- Integration tests
- API tests
- Performance tests

Run all tests:

```bash
mvn verify
```

## Deployment

### Environment-Specific Configuration

#### Development Environment
```properties
# application-dev.properties
spring.profiles.active=dev
spring.datasource.url=jdbc:postgresql://localhost:30032/hcm_db_dev
spring.kafka.bootstrap-servers=localhost:9092
logging.level.tech.stl.hcm=DEBUG
```

#### Testing Environment
```properties
# application-test.properties
spring.profiles.active=test
spring.datasource.url=jdbc:postgresql://test-db:30032/hcm_db_test
spring.kafka.bootstrap-servers=test-kafka:9092
logging.level.tech.stl.hcm=INFO
```

#### Production Environment
```properties
# application-prod.properties
spring.profiles.active=prod
spring.datasource.url=jdbc:postgresql://prod-db:30032/hcm_db_prod
spring.kafka.bootstrap-servers=prod-kafka:9092
logging.level.tech.stl.hcm=WARN
```

### Docker Deployment

#### Building the Image

1. Create a Dockerfile:
```dockerfile
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

2. Build the image:
```bash
# Build the application
mvn clean package -DskipTests

# Build the Docker image
docker build -t hcm-candidate-service:latest .
```

#### Running with Docker

1. Basic run:
```bash
docker run -p 8080:8080 hcm-candidate-service:latest
```

2. With environment variables:
```bash
docker run -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=prod \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://prod-db:30032/hcm_db_prod \
  -e SPRING_KAFKA_BOOTSTRAP_SERVERS=prod-kafka:9092 \
  hcm-candidate-service:latest
```

3. With Docker Compose:
```yaml
version: '3.8'
services:
  candidate-service:
    image: hcm-candidate-service:latest
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=prod
      - SPRING_DATASOURCE_URL=jdbc:postgresql://db:30032/hcm_db_prod
      - SPRING_KAFKA_BOOTSTRAP_SERVERS=kafka:9092
    depends_on:
      - db
      - kafka

  db:
    image: postgres:14-alpine
    environment:
      - POSTGRES_DB=hcm_db_prod
      - POSTGRES_USER=postgres
      - POSTGRES_PASSWORD=postgres
    volumes:
      - postgres_data:/var/lib/postgresql/data

  kafka:
    image: confluentinc/cp-kafka:7.3.0
    environment:
      - KAFKA_BROKER_ID=1
      - KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181
      - KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://kafka:9092
      - KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1

volumes:
  postgres_data:
```

Run with:
```bash
docker-compose up -d
```

### Kubernetes Deployment

#### Prerequisites
- Kubernetes cluster
- kubectl configured
- Helm (optional)

#### Basic Deployment

1. Create namespace:
```bash
kubectl create namespace hcm
```

2. Deploy PostgreSQL:
```yaml
# postgres-deployment.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: postgres
  namespace: hcm
spec:
  replicas: 1
  selector:
    matchLabels:
      app: postgres
  template:
    metadata:
      labels:
        app: postgres
    spec:
      containers:
      - name: postgres
        image: postgres:14-alpine
        env:
        - name: POSTGRES_DB
          value: hcm_db
        - name: POSTGRES_USER
          value: postgres
        - name: POSTGRES_PASSWORD
          valueFrom:
            secretKeyRef:
              name: postgres-secret
              key: password
        ports:
        - containerPort: 5432
        volumeMounts:
        - name: postgres-storage
          mountPath: /var/lib/postgresql/data
      volumes:
      - name: postgres-storage
        persistentVolumeClaim:
          claimName: postgres-pvc
```

3. Deploy Kafka:
```yaml
# kafka-deployment.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: kafka
  namespace: hcm
spec:
  replicas: 1
  selector:
    matchLabels:
      app: kafka
  template:
    metadata:
      labels:
        app: kafka
    spec:
      containers:
      - name: kafka
        image: confluentinc/cp-kafka:7.3.0
        env:
        - name: KAFKA_BROKER_ID
          value: "1"
        - name: KAFKA_ZOOKEEPER_CONNECT
          value: zookeeper:2181
        - name: KAFKA_ADVERTISED_LISTENERS
          value: PLAINTEXT://kafka:9092
        - name: KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR
          value: "1"
```

4. Deploy the application:
```yaml
# candidate-service-deployment.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: candidate-service
  namespace: hcm
spec:
  replicas: 3
  selector:
    matchLabels:
      app: candidate-service
  template:
    metadata:
      labels:
        app: candidate-service
    spec:
      containers:
      - name: candidate-service
        image: hcm-candidate-service:latest
        ports:
        - containerPort: 8080
        env:
        - name: SPRING_PROFILES_ACTIVE
          value: prod
        - name: SPRING_DATASOURCE_URL
          value: jdbc:postgresql://postgres:5432/hcm_db
        - name: SPRING_KAFKA_BOOTSTRAP_SERVERS
          value: kafka:9092
        resources:
          requests:
            memory: "512Mi"
            cpu: "500m"
          limits:
            memory: "1Gi"
            cpu: "1000m"
        livenessProbe:
          httpGet:
            path: /actuator/health
            port: 8080
          initialDelaySeconds: 60
          periodSeconds: 10
        readinessProbe:
          httpGet:
            path: /actuator/health
            port: 8080
          initialDelaySeconds: 30
          periodSeconds: 5
```

5. Create service:
```yaml
# candidate-service-service.yaml
apiVersion: v1
kind: Service
metadata:
  name: candidate-service
  namespace: hcm
spec:
  selector:
    app: candidate-service
  ports:
  - port: 80
    targetPort: 8080
  type: LoadBalancer
```

6. Apply configurations:
```bash
kubectl apply -f postgres-deployment.yaml
kubectl apply -f kafka-deployment.yaml
kubectl apply -f candidate-service-deployment.yaml
kubectl apply -f candidate-service-service.yaml
```

#### Using Helm (Optional)

1. Create Helm chart:
```bash
helm create hcm-candidate-service
```

2. Modify values.yaml:
```yaml
replicaCount: 3
image:
  repository: hcm-candidate-service
  tag: latest
  pullPolicy: IfNotPresent

service:
  type: LoadBalancer
  port: 80

resources:
  requests:
    memory: "512Mi"
    cpu: "500m"
  limits:
    memory: "1Gi"
    cpu: "1000m"

env:
  - name: SPRING_PROFILES_ACTIVE
    value: prod
  - name: SPRING_DATASOURCE_URL
    value: jdbc:postgresql://postgres:5432/hcm_db
  - name: SPRING_KAFKA_BOOTSTRAP_SERVERS
    value: kafka:9092
```

3. Deploy with Helm:
```bash
helm install hcm-candidate-service ./hcm-candidate-service
```

### Monitoring and Maintenance

#### Health Checks
- Application health: `http://localhost:8080/actuator/health`
- Database health: `http://localhost:8080/actuator/health/db`
- Kafka health: `http://localhost:8080/actuator/health/kafka`

#### Metrics
- Prometheus metrics: `http://localhost:8080/actuator/prometheus`
- Application metrics: `http://localhost:8080/actuator/metrics`

#### Logs
```bash
# Docker logs
docker logs hcm-candidate-service

# Kubernetes logs
kubectl logs -f deployment/candidate-service -n hcm
```

#### Scaling
```bash
# Docker Compose
docker-compose up -d --scale candidate-service=3

# Kubernetes
kubectl scale deployment candidate-service -n hcm --replicas=3
```

#### Backup and Restore
```bash
# Database backup
pg_dump -U postgres hcm_db > backup.sql

# Database restore
psql -U postgres hcm_db < backup.sql
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details. 