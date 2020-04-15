package com.github.nbsllc;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.io.IoBuilder;
import org.testng.annotations.BeforeTest;

import java.io.PrintStream;

public class APITestBase {
    protected static Logger logger = LogManager.getLogger();

    @BeforeTest
    public void configureRestAssured() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        PrintStream loggerStream = IoBuilder.forLogger(logger).setLevel(Level.INFO).buildPrintStream();
        RestAssured.filters(RequestLoggingFilter.logRequestTo(loggerStream));
        RestAssured.filters(ResponseLoggingFilter.logResponseTo(loggerStream));
    }
}
