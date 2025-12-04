
- `TestBase.java` – sets up RestAssured with base URI and logging.
- `PetClient.java` – encapsulates GET /pet/{id} API call.
- `PetContractTests.java` – TestNG test that:
    1. Retrieves a valid pet ID dynamically.
    2. Calls GET /pet/{id} via `PetClient`.
    3. Validates response status and JSON schema.
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
