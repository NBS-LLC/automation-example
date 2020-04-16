package com.github.nbsllc;

import com.github.nbsllc.domain.User;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.Level;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class TestUserAPI extends APITestBase {
    @Test(dataProvider = "userData")
    public void testThatNewUsersCanBeAdded(User body) {
        logger.log(Level.getLevel("STEP"), "Testing that the user data can be added via the user API.");

        Instant start = Instant.now();

        User resp = given()
            .contentType(ContentType.JSON)
            .body(body)
            .when()
            .post("https://jsonplaceholder.typicode.com/users")
            .then().assertThat()
            .statusCode(201)
            .contentType(ContentType.JSON)
            .extract().as(User.class);

        Instant stop = Instant.now();
        long elapsedSec = Duration.between(start, stop).getSeconds();
        logger.info("Adding the user data took {} seconds.", elapsedSec);

        logger.log(Level.getLevel("STEP"), "Verifying that the user data is added.");
        logger.log(Level.getLevel("STEP"), "Verifying that the user data is returned in the response.");

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(resp.getId()).isNotNull();
        softly.assertThat(resp.getName()).isEqualTo(body.getName());
        softly.assertThat(resp.getUsername()).isEqualTo(body.getUsername());
        softly.assertThat(elapsedSec).as("elapsed").isLessThan(30);
        softly.assertAll();
    }

    @Test
    public void testThatUserApiReturnsValidData() {
        logger.log(Level.getLevel("STEP"), "Testing that the user API returns a collection of user data.");

        List<User> users =
            get("https://jsonplaceholder.typicode.com/users")
                .then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().jsonPath().getList("", User.class);

        logger.log(Level.getLevel("STEP"), "Verifying that the correct number of users are returned.");
        logger.log(Level.getLevel("STEP"), "Verifying that the user data is accurate.");

        assertThat(users).hasSize(10);
        assertThat(users).extracting(User::getName).contains("Clementine Bauch");
        assertThat(users).filteredOn(u -> u.getName().equals("Clementine Bauch"))
            .extracting("address.geo.lat").first()
            .isEqualTo(-68.6102);
    }

    @DataProvider
    private Object[][] userData() {
        return new Object[][] {
            new Object[] {
                User.builder()
                    .withName("QA Tester 01")
                    .withUsername("qatester01")
                    .build()
            },
            new Object[] {
                User.builder()
                    .withName("QA Tester 02")
                    .withUsername("qatester02")
                    .build()
            },
            new Object[] {
                User.builder()
                    .withName("QA Tester 03")
                    .withUsername("qatester03")
                    .build()
            }
        };
    }
}
