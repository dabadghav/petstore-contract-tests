package com.contracttesting.contracts;

import com.contracttesting.base.TestBase;
import com.contracttesting.clients.PetClient;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PetContractTests extends TestBase{

    private final PetClient petClient = new PetClient();

    @Test(description = "Verify GET /pet/{id} returns the correct pet details")
    public void testGetPetById() {
        long petId = 10; // Replace with an existing pet ID in Swagger Petstore

        Response response = petClient.getPetById(petId);

        // Assert HTTP status
        assertThat(response.getStatusCode(), equalTo(200));

        // Assert response body (basic checks)
        long responseId = response.jsonPath().getLong("id");
        assertThat(responseId, equalTo(petId));

        String petName = response.jsonPath().getString("name");
        System.out.println("Pet name: " + petName);

        response.then().assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/pet-schema.json"));
    }

    @Test(description = "Create a new pet and verify it can be retrieved")
    public void testCreatePet() {
        // Sample pet JSON
        long newPetId = 12345; // Pick a unique ID for testing
        String petJson = """
                {
                  "id": %d,
                  "name": "Buddy",
                  "photoUrls": ["http://example.com/photo1"],
                  "status": "available"
                }
                """.formatted(newPetId);

        // Create the pet
        Response createResponse = petClient.createPet(petJson);
        createResponse.then().statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/pet-schema.json"));

        // Verify the pet was created by retrieving it
        Response getResponse = petClient.getPetById(newPetId);
        getResponse.then().statusCode(200);

        // Assert key fields
        assertThat(getResponse.jsonPath().getLong("id"), equalTo(newPetId));
        assertThat(getResponse.jsonPath().getString("name"), equalTo("Buddy"));
        assertThat(getResponse.jsonPath().getString("status"), equalTo("available"));
    }

    @Test(description = "Verify PUT /pet updates an existing pet")
    public void testUpdatePet() {

        // Step 1: Create a new pet first (required before updating)
        String createPetJson = """
        {
            "id": 999999,
            "category": { "id": 1, "name": "Dogs" },
            "name": "Rex",
            "photoUrls": ["url1"],
            "tags": [{ "id": 1, "name": "Tag1" }],
            "status": "available"
        }
        """;

        Response createResponse = petClient.createPet(createPetJson);
        assertThat(createResponse.getStatusCode(), equalTo(200));

        // Step 2: Update the same pet
        String updatePetJson = """
        {
            "id": 999999,
            "category": { "id": 1, "name": "Dogs" },
            "name": "Rex-Updated",
            "photoUrls": ["url1"],
            "tags": [{ "id": 1, "name": "Tag1" }],
            "status": "sold"
        }
        """;

        Response updateResponse = petClient.updatePet(updatePetJson);

        // Step 3: Assert update success
        assertThat(updateResponse.getStatusCode(), equalTo(200));

        // Step 4: Validate schema
        updateResponse.then().assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/pet-schema.json"));

        // Step 5: Verify fields were updated
        assertThat(updateResponse.jsonPath().getString("name"), equalTo("Rex-Updated"));
        assertThat(updateResponse.jsonPath().getString("status"), equalTo("sold"));
    }
}
