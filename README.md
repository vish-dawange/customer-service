# **Customer Management API**

## **Overview**

This project is a RESTful API for managing customers. It includes basic CRUD operations, observability features, and is designed to be easily deployed in a containerized and cloud-native environment.

---

## **Features**

- REST API with CRUD operations for `Customer` entities.
- Unit tests to ensure API reliability.
- Observability through logging, metrics, and health checks.
- Containerized using Docker for seamless deployment.
- CI/CD pipeline design for automated build, test, and deployment processes.

---

## **Prerequisites**

Before running the application, ensure you have the following installed:

- **Java 17**
- **Maven** (for building the project)
- **Docker** (for containerization)
- **Git** (to clone the repository)
- Optional: **Postman** (to test the API)

---

## **Setting Up the Application Locally**

### **1. Clone the Repository**
```bash
git clone https://github.com/vish-dawange/customer-service.git
cd customer-service
```

### **2. Configure the Application**
The application uses an H2 embedded database by default.

- No additional database setup is required for local testing.

### **3. Build the Application**
Run the following Maven command to build the project:
```bash
mvn clean install
```

### **4. Run the Application**
Start the application using:
```bash
mvn spring-boot:run
```

The application will run on `http://localhost:8080`.

---

## **API Endpoints**

| **Method** | **Endpoint**             | **Description**               |
|------------|--------------------------|-------------------------------|
| GET        | `/api/v1/customers`      | Get all customers             |
| GET        | `/api/v1/customers/{id}` | Get a customer by ID          |
| POST       | `/api/v1/customers`      | Create a new customer         |
| PUT        | `/api/v1/customers/{id}` | Update an existing customer   |
| DELETE     | `/api/v1/customers/{id}` | Delete a customer by ID       |

For detailed API contracts, refer to the [Postman Collection](postman/customer-api-service.postman_collection.json).

---

## **Building a Docker Image**

### **1. Build the Docker Image**
Run the following command in the project directory:
```bash
docker build -t customer-management-api .
```

### **2. Run the Docker Container**
To run the application inside a Docker container:
```bash
docker run -p 8080:8080 --name customer-api-container customer-management-api
```

The API will be accessible at `http://localhost:8080`.

---

## **Running Integration and Unit Tests**

### **1. Run All Tests**
Use Maven to run the tests:
```bash
mvn test
```

### **2. View Test Results**
Test results will be available in the terminal.

---

## **Observability Features**

### **Actuator Endpoints**
The API includes Spring Boot Actuator for monitoring and observability.

| **Endpoint**     | **Description**             |
|-------------------|-----------------------------|
| `/actuator/health` | Displays application health |
| `/actuator/info`   | Provides application metadata |
| `/actuator/metrics` | Displays application metrics |

---

## **Deploying to Kubernetes**
TO DO

---

## **CI/CD Pipeline**

### **Pipeline Design**
- **Steps**:
    1. Code checkout.
    2. Build the project with Maven.
    3. Run unit tests.
    4. Static code analysis using Sonar qube, Snyk vulnerability analysis
    5. Build and push Docker image to a container registry.
- **Tools**: Jenkins, GitHub/Bitbucket, or similar CI/CD systems.

For more details, refer to the `devops/build` and `devops/deploy` pipeline file.

---
