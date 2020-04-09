package com.github.nbsllc;

import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;

public class APITestBase {
    protected static Logger logger = LogManager.getLogger();

    @BeforeTest
    public void configureRestAssured() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
