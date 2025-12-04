
- `TestBase.java` – sets up RestAssured with base URI and logging.
- `PetClient.java` – encapsulates GET /pet/{id} API call.
- `PetContractTests.java` – TestNG tests for Pet endpoints:
    1. **GET /pet/{id}** – retrieves an existing pet by ID, validates HTTP status, key fields (`id`, `name`), and JSON schema (`pet-schema.json`).
    2. **POST /pet** – creates a new pet using a JSON payload, validates HTTP status and response fields, confirms creation via GET, and validates JSON schema (`pet-schema.json`).
    3. **PUT /pet** – updates an existing pet by first creating it (setup step), sends an update request, validates HTTP status, confirms changes to fields such as `name` and `status`, and applies full JSON schema validation (`pet-schema.json`).
- `schemas/` – contains JSON schemas for Pet, Category, and Tag.

---

## **Dependencies**

- Java 21
- Maven
- TestNG
- RestAssured
- RestAssured JSON Schema Validator

Ensure `pom.xml` includes:

```xml
<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>rest-assured</artifactId>
    <version>5.5.6</version>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>json-schema-validator</artifactId>
    <version>5.5.6</version>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>tools.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>3.0.2</version>
</dependency>
        
<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>7.11.0</version>
    <scope>test</scope>
</dependency>
```

---
## **How to Run**

### **1. Clone the repository**

```bash
git clone https://github.com/dabadghav/petstore-contract-tests.git
cd petstore-contract-tests
```

### **2. Run all tests using Maven**

```bash
mvn test
```
