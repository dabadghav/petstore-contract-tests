package com.contracttesting.base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

public class TestBase {
    @BeforeSuite
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }
}
