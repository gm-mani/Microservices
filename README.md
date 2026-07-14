# Cloud-Native Banking Microservices

A distributed, cloud-native backend architecture for a banking domain, built with Spring Boot and Spring Cloud. This project demonstrates production-grade patterns including service-to-service communication, asynchronous event processing, centralized configuration, and API gateway security.

## 🏗️ System Architecture

The ecosystem consists of infrastructure and domain-specific microservices:

| Service | Description |
|---|---|
| `gatewayserver` | The edge server routing all external traffic, enforcing OAuth2/JWT security, and rate-limiting. |
| `eurekaserver` | Service registry enabling dynamic discovery and client-side load balancing. |
| `configserver` | Centralised configuration server managing environment-specific profiles (`default`, `test`, `prod`). |
| `accounts` | Core business service managing customer profiles and bank accounts. |
| `loans` | Business service managing loan lifecycles. |
| `cards` | Business service managing credit/debit card issuance. |
| `message` | Asynchronous Kafka consumer that processes email and SMS notification events. |

## ⚙️ Core Technologies & Patterns

*   **Framework:** Java, Spring Boot, Spring Cloud
*   **Inter-Service Communication:** OpenFeign for synchronous REST calls; Apache Kafka for decoupled, event-driven communication (e.g., triggering notification events upon account creation).
*   **Resiliency:** Resilience4j integrated via OpenFeign to implement Circuit Breakers, Retries, and Fallback methods, preventing downstream cascading failures.
*   **Security:** Keycloak integration at the API Gateway layer to enforce OAuth2 / JWT authentication and Role-Based Access Control.
*   **Data Access:** Spring Data JPA for database operations.
*   **DevOps:** Containerization using Docker; local orchestration across multiple environments using Docker Compose.

---

## 🚀 Getting Started (Local Development)

### Prerequisites
*   Java 21
*   Maven
*   Docker & Docker Compose

### 1. Build the Microservices
Before spinning up the containers, ensure all microservices are built and their Docker images are generated. Navigate to the root directory and run your Maven build command (e.g., using Jib or standard Dockerfiles configured in your `pom.xml`).

### 2. Start the Infrastructure & Services
The project uses Docker Compose to orchestrate Keycloak, Kafka, Redis, and all Spring Boot microservices. 

Navigate to the default Docker Compose directory and start the cluster:
```bash
cd docker-compose/default
docker-compose up -d
```

### 3. Important Local Endpoints
Once the containers are successfully running, you can interact with the system via the following endpoints:

*   **API Gateway:** `http://localhost:8072` *(All API traffic should be routed through here)*
*   **Eureka Dashboard:** `http://localhost:8070`
*   **Config Server:** `http://localhost:8071`
*   **Keycloak Admin Console:** `http://localhost:7080`

### 4. Authentication & API Testing
The API Gateway enforces OAuth2 security. Direct calls to the domain services without a token will result in a `401 Unauthorized` error.

1.  Access Keycloak at `http://localhost:7080` using your configured admin credentials.
2.  Generate a JWT token for the configured client.
3.  Include the token as a `Bearer Token` in the `Authorization` header of your HTTP requests.

**Example Request:**
```bash
curl -X GET http://localhost:8072/accounts/api/fetch \
  -H "Authorization: Bearer <YOUR_ACCESS_TOKEN>"
```
