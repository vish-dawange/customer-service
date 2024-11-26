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

## **Running Unit Tests**

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



## **CI/CD Pipeline**

---

### **Pipeline Design**
- **Steps for build job**:
    1. Code checkout.
    2. Build the project with Maven.
    3. Run unit tests.
    4. Static code analysis using Sonar qube, Snyk vulnerability analysis
    5. Build and push Docker image to a container registry.
- **Steps for deploy job**:
  1. Code checkout.
  2. Update cloud formation template for AWS ECS.
  
- **Parameters**: Build: branch name, version; Deploy: branch name, version and env name e.g dev, uat etc
- **Tools**: Jenkins, GitHub/Bitbucket, or similar CI/CD systems.

For more details, refer to the `devops/build` and `devops/deploy` pipeline file.

---

## **Deploying to Kubernetes**
- TO DO
---

## **Customer API Client - Shell Script**

This script provides a command-line interface to interact with the Customer Management API for CRUD operations.

---

### **Prerequisites**

- **Customer API**: Running at `http://localhost:8080`.
- **Tools**:
  - `curl`: HTTP client
  - `jq` (optional): For JSON formatting

---

### **Setup**

1. **Save the Script**: Name it `consume_api.sh`.
2. **Make Executable**:
   ```bash
   chmod +x consume_api.sh
   ```

---

### **Usage**

1. Run the script:
   ```bash
   ./consume_api.sh
   ```
2. Follow the menu options:
   ```
   1. List All Customers
   2. Get Customer by ID
   3. Create Customer
   4. Update Customer
   5. Delete Customer
   6. Exit
   ```

---

### **API Operations**

| **Action**            | **HTTP Method** | **Endpoint**                      |
|------------------------|-----------------|------------------------------------|
| List All Customers     | GET             | `/api/customers`                  |
| Get Customer by ID     | GET             | `/api/customers/{id}`             |
| Create New Customer    | POST            | `/api/customers`                  |
| Update Existing Customer | PUT           | `/api/customers/{id}`             |
| Delete Customer        | DELETE          | `/api/customers/{id}`             |

---

### **Example**

#### Create a Customer:
```bash
Enter First Name: John
Enter Last Name: Doe
Enter Email: john.doe@example.com
Enter Phone Number: 1234567890

Creating customer...
{
  "firstName": "John",
  "middleName": "Bob",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "1234567890"
}
```
### Importing and Using a Postman Collection

#### **Importing a Collection in Postman**
1. Open Postman and click on the **"Import"** button at the top-left corner.
2. Choose [this](postman/customer-api-service.postman_collection.json) postman collection file from your local system and click **"Open"**.
3. The imported collection will appear under the **Collections** tab. Expand it to view all API requests.

---

#### **Using the Collection for Testing**
1. Select a request from the imported collection.
2. Update any required parameters or variables (e.g., `base_url` or `id`).
3. Click **"Send"** to execute the request.
4. View the response in the Postman interface to verify API functionality.
---
