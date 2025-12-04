package com.contracttesting.clients;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PetClient {

    /**
     * GET /pet/{id}
     */
    public Response getPetById(long petId) {
        return RestAssured
                .given()
                .basePath("/pet/" + petId)
                .when()
                .get();
    }

    /**
     * POST /pet
     */
    public Response createPet(String petJson) {
        return RestAssured
                .given()
                .basePath("/pet")
                .header("Content-Type", "application/json")
                .body(petJson)
                .when()
                .post();
    }

    /**
     * PUT /pet
     */
    public Response updatePet(String petJson) {
        return RestAssured
                .given()
                .basePath("/pet")
                .header("Content-Type", "application/json")
                .body(petJson)
                .when()
                .put();
    }

    /**
     * DELETE /pet/{id}
     */
    public Response deletePet(long petId) {
        return RestAssured
                .given()
                .basePath("/pet/" + petId)
                .when()
                .delete();
    }
}
