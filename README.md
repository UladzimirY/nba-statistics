# nba-statistics
reshef.sharvit@skyhawk.security


### Project: NBA Player Statistics System

#### **Technologies**:

1. Java 17: A modern version of Java with performance and security improvements.
2. Spring Boot: Facilitates the rapid and easy creation of microservices with built-in templates and support for various libraries.
3. Hibernate: Provides ORM for database interaction, simplifying data handling.
4. Liquibase: A tool for managing database changes, simplifying deployment and schema maintenance.
5. PostgreSQL: A reliable and high-performance relational database.
6. Lombok: Simplifies Java code by reducing boilerplate.
7. RestAPI: A standard for interaction between microservices and client applications.
8. Docker: Ensures containerization and environment independence for microservices.

####    **Architectural Patterns:**

1. API Gateway:
   * Advantages: Simplifies microservice management, provides security, routing, authentication, and authorization.
   * Comparison with Service Mesh: API Gateway is simpler to implement and maintain, especially in the initial stages, whereas Service Mesh offers more complex traffic management and security capabilities but requires more sophisticated infrastructure.
2. API Composition:
   * Advantages: Combines data from multiple microservices to respond to client requests, optimizing request handling and reducing system load.
   * Comparison with Orchestration: API Composition distributes responsibility and improves scalability, reducing the load on a central orchestrator. Orchestration can create bottlenecks and complicate the system.

#### Microservices Division:

1. Player Service:
   * Role: Manages player information and statistics.
   * Advantages: Specializing in player data optimizes queries and updates, improves performance, and simplifies management.
2. Team Service:
   * Role: Manages team information.
   * Advantages: Specializing in team data allows for flexible team structure management, independently of player data.
3. Statistics Service:
   * Role: Calculates and provides aggregated statistics (season averages).
   * Advantages: Specializing in computations improves performance and allows for optimized algorithms for processing large volumes of data.
4. Game Service:
   * Role: Handles game information requests and provides game statistics.
   * Advantages: A service responsible for game validation and processing enables the implementation of complex validation rules and processing logic without overloading other services.
5. API Gateway:
   * Role: Central management of requests, ensuring security and load balancing.
   * Advantages: Centralized management of authentication, authorization, and request routing simplifies administration and enhances security.

####      Advantages of This Division:

1. Scalability and Flexibility:
   * Justification: Microservices can be scaled independently, allowing efficient resource allocation and handling loads.
   * Example: When the number of requests to Player Service increases, only this service can be scaled without affecting others.
2. Independence and Resilience:
   * Justification: A failure in one service does not lead to a failure of the entire system.
   * Example: If the Statistics Service fails, other services will continue to operate.
3. Ease of Development and Deployment:
   * Justification: Teams can work independently on different services, speeding up development and implementation of new features.
   * Example: The team working on Team Service can deploy new features without waiting for other teams.
4. Performance Improvement:
   * Justification: Dividing tasks among services allows each service to be optimized for its specific function.
   * Example: The Statistics Service can use optimized algorithms for computations, improving performance.
     Conclusion:
     The proposed solution based on microservices using modern technologies and architectural patterns ensures high availability, scalability, flexibility, and resilience of the system. The chosen patterns and technologies help optimize performance and simplify the development and maintenance of the system, making it ready for future changes and scaling.

####      **Future Growth Potential:**

1. As load increases and there is a need for more secure data storage, the database can be divided into multiple databases.
2. Frequently requested statistics can be cached to increase read request speed.

Diagram : https://drive.google.com/file/d/1j9t3AXghmIjVt-E0k85qifb77fcs8l5D/view?usp=drive_link