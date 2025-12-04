package com.contracttesting.contracts;

import com.contracttesting.base.TestBase;
import com.contracttesting.clients.PetClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PetContractTests extends TestBase{

    private final PetClient petClient = new PetClient();

    @Test(description = "Verify GET /pet/{id} returns the correct pet details")
    public void testGetPetById() {
        long petId = 10; // Replace with an existing pet ID in Swagger Petstore

        Response response = petClient.getPetById(petId); /*RestAssured
                .given()
                .baseUri("https://petstore.swagger.io/v2")
                .basePath("/pet/" + petId)
                .when()
                .get();*/

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
}
