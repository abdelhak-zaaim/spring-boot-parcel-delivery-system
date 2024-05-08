## Introduction

The DeliX project is a comprehensive system currently under development, designed to streamline the transport and delivery of parcels. It is being developed using the robust Spring Boot framework, which is known for its ability to create stand-alone, production-grade applications swiftly and with ease.

The project harnesses the power of Java Persistence API (JPA), a standard interface for accessing databases in Java, providing a simplified way of handling relational data and object mapping. This ensures efficient and streamlined database operations, making the system capable of handling large volumes of data with ease.

Security is a paramount concern in the DeliX project. It utilizes Spring Security, a powerful and highly customizable authentication and access-control framework, to ensure robust security mechanisms. This includes respecting all roles of security, thereby enhancing the overall security of the system and ensuring that access to sensitive data and functionalities is strictly controlled and limited to authorized users.

The DeliX project places a strong emphasis on code readability and maintainability. The codebase is structured and written in a way that promotes clarity and ease of understanding. This not only improves the efficiency of the development process but also ensures that the system can be easily adapted and extended in the future, thereby enhancing its maintainability.

The ultimate goal of the DeliX project is to evolve into a complete system for transport and delivery of parcels. It aims to provide a comprehensive solution that covers all aspects of the delivery process, from order placement and tracking to delivery confirmation. By leveraging modern technologies and following best practices, the DeliX project is well on its way to achieving this goal.
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
spring.datasource.password=<yourDatabassePassword> # keep it empty if you dont use password for the database
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
## Note
Please note that these steps assume that you have Java, Mysql and Maven installed on your machine.