## Tech stack

**Core**
`Java 21` · `Spring Boot 4` · `Spring Cloud` · `Spring Security`

**Microservices & Messaging**
`Eureka Service Discovery` · `Spring Cloud Config` · `OpenFeign` · `Apache Kafka` · `Spring Cloud Stream`

**Resilience**
`Resilience4j` · `Circuit Breaker` · `Retry` · `Rate Limiter`

**API & Security**
`Spring Cloud Gateway` · `OAuth2 / JWT` · `Keycloak` · `REST APIs` · `OpenAPI / Swagger`

**Data & Persistence**
`Spring Data JPA` · `Hibernate` · `H2` · `Redis`

**DevOps & Tooling**
`Docker` · `Docker Compose` · `JIB` · `Maven` · `Git`

---

## Featured project

### [Microservices — Banking Backend System](https://github.com/gm-mani/Microservices)

A fully wired, production-style microservices architecture built around a fictional banking domain. Not a tutorial clone — this is a system designed end-to-end with the patterns you'd expect in real distributed services work.

**What's inside:**

| Service | What it does |
|---|---|
| `accounts` | Core CRUD service — manages customers and bank accounts |
| `loans` | Loan lifecycle management |
| `cards` | Card issuance and details |
| `gatewayserver` | API gateway — routes, secures, rate-limits, and traces all traffic |
| `eurekaserver` | Service registry — all services register and discover each other here |
| `configserver` | Centralised config with environment-specific profiles (default / test / prod) |
| `message` | Async consumer — handles email and SMS notification events off the Kafka queue |

**How it's wired together:**

- Services communicate via **OpenFeign** with **Resilience4j circuit breakers** and **fallback handlers** — if a downstream service is down, requests degrade gracefully instead of cascading
- Account creation fires an event onto a **Kafka topic** (`send-communication`), the message service consumes it and processes email/SMS — fully async, fully decoupled
- The gateway enforces **OAuth2 / JWT auth via Keycloak**, performs **Redis-based rate limiting**, and propagates a **correlation ID** across the entire request chain for distributed tracing
- Retry policies, circuit breaker configs, and rate limiters are all tuned per-service in the centralised config server
- **Three Docker Compose environments** — `default`, `test`, `prod` — with shared base configs and service health checks

**Stack:** `Spring Boot 4` · `Java 21` · `Kafka` · `Resilience4j` · `Spring Cloud Gateway` · `Keycloak` · `Redis` · `Docker Compose` · `JIB`

---

## Currently working on

- Deepening hands-on knowledge of Kubernetes — moving the above system from Docker Compose to K8s with proper config maps and health probes
- Exploring distributed tracing with Micrometer and Zipkin
- Consistent open-source contributions

---

## Get in touch

I'm open to conversations about backend engineering, distributed systems, or anything Java/Spring.

**LinkedIn:** [linkedin.com/in/mani12](http://www.linkedin.com/in/mani12)
**GitHub:** [github.com/gm-mani](https://github.com/gm-mani)

---

<div align="center">
<sub>Based in Bengaluru, India · Open to opportunities</sub>
</div>
