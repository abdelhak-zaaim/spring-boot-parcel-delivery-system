## Introduction

DeliXis a comprehensive package tracking system, developed using a robust technology stack that includes Java, SQL, Spring Boot, and Maven. It leverages the power of JPA (Java Persistence API) for seamless database operations and Spring Security for robust authentication and authorization mechanisms. The system allows users to track their packages in real-time, providing timely updates on package location, delivery status, and estimated delivery time. With its intuitive interface and reliable performance.

## Future Enhancements

In the future, this project aims to incorporate an intelligent transport optimization algorithm. This will further enhance the efficiency of the package tracking process, ensuring packages are delivered in the most effective and timely manner.
## Installation Steps

1. **Clone the repository**

   You can clone the repository by running the following command in your terminal:

   ```bash
   git clone https://github.com/abdelhak-zaaim/DeliX.git
    ```
2. **Navigate to the project directory**

   Once you have cloned the repository, navigate to the project directory using the following command:

   ```bash
   cd DeliX
   ```

3. Database Configuration

The application uses MySQL as its database. You need to configure the database connection details in the `application.properties` file located in `src/main/resources/`.

Here are the properties you need to set:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/<yourDatabaseNaame>?createDatabaseIfNotExist=true
spring.datasource.username=root # your database username
spring.datasource.password=<yourDatabassePassword> # keep it empty if you dont use password of the database
```

4. **Run the project**

   You can run the project by executing the following command:

   ```bash
   mvn spring-boot:run
   ```
5. **Access the application**

   Once the project is up and running, you can access the application by navigating to the following URL in your web browser:

   ```bash
   http://localhost:8080
   ```
## Conclusion
Please note that these steps assume that you have Java, Mysql and Maven installed on your machine.