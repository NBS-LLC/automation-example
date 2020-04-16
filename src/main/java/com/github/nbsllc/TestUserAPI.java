package com.github.nbsllc;

import com.github.nbsllc.domain.User;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.Level;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class TestUserAPI extends APITestBase {
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

    @Test
    public void testThatNewUsersCanBeAdded() {
        logger.log(Level.getLevel("STEP"), "Testing that the user data can be added via the user API.");

        User body = User.builder()
            .withName("QA Tester")
            .withUsername("qatester")
            .build();

        User resp = given()
            .contentType(ContentType.JSON)
            .body(body)
            .when()
            .post("https://jsonplaceholder.typicode.com/users")
            .then().assertThat()
            .statusCode(201)
            .contentType(ContentType.JSON)
            .extract().as(User.class);

        logger.log(Level.getLevel("STEP"), "Verifying that the user data is added.");
        logger.log(Level.getLevel("STEP"), "Verifying that the user data is returned in the response.");

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(resp.getId()).isNotNull();
        softly.assertThat(resp.getName()).isEqualTo("QA Tester");
        softly.assertThat(resp.getUsername()).isEqualTo("qatester");
        softly.assertAll();
    }
}
