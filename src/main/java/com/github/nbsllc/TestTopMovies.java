package com.github.nbsllc;

import com.github.nbsllc.domain.Movie;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTopMovies extends APITestBase {
    @Test
    public void testThatMovieDataIsAccurateBetweenEndpoints() {
        List<Movie> topMovies =
            given()
                .queryParam("count", 100)
                .get("http://199.83.14.77:8080/api/v1/topMovies")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().jsonPath().getList("", Movie.class);

        List<Movie> moviesWithoutRank = topMovies.stream().filter(m -> Objects.isNull(m.getRank()))
            .collect(Collectors.toList());
        System.out.println("Movies without a rank:");
        moviesWithoutRank.forEach(System.out::println);
        System.out.println();

        List<Movie> moviesWithNullTitles = topMovies.stream().filter(m -> Objects.isNull(m.getTitle()))
            .collect(Collectors.toList());
        System.out.println("Movies without a title (null):");
        moviesWithNullTitles.forEach(System.out::println);
        System.out.println();

        List<Movie> moviesWithBlankTitles = topMovies.stream().filter(m -> "".equals(m.getTitle()))
            .collect(Collectors.toList());
        System.out.println("Movies without a title (blank):");
        moviesWithBlankTitles.forEach(System.out::println);
        System.out.println();

        List<Movie> moviesWithoutReleaseYear = topMovies.stream().filter(m -> Objects.isNull(m.getReleaseYear()))
            .collect(Collectors.toList());
        System.out.println("Movies without a release year:");
        moviesWithoutReleaseYear.forEach(System.out::println);
        System.out.println();

        //        SoftAssertions softly = new SoftAssertions();
        //        softly.assertThat(topMovies).as("top movie - rank").extracting(Movie::getRank).doesNotContainNull();
        //        softly.assertThat(topMovies).as("top movie - title (null)").extracting(Movie::getTitle).doesNotContainNull();
        //        softly.assertThat(topMovies).as("top movie - title (blank)").extracting(Movie::getTitle).doesNotContain("");
        //        softly.assertThat(topMovies).as("top movie - release year").extracting(Movie::getReleaseYear).doesNotContainNull();
        //        softly.assertAll();

        List<Movie> movieDetailsList = new ArrayList<>();
        for (Movie topMovie : topMovies) {
            Movie movieDetails =
                given()
                    .pathParam("id", topMovie.getId())
                    .when()
                    .get("http://199.83.14.77:8080/api/v1/movie/{id}")
                    .then()
                    .assertThat()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .extract().as(Movie.class);

            try {
                SoftAssertions softly = new SoftAssertions();
                softly.assertThat(topMovie.getId()).isEqualTo(movieDetails.getId());
                softly.assertThat(topMovie.getRank()).isEqualTo(movieDetails.getRank());
                softly.assertThat(topMovie.getTitle()).isEqualTo(movieDetails.getTitle());
                softly.assertThat(topMovie.getReleaseYear()).isEqualTo(movieDetails.getReleaseYear());
                softly.assertAll();
            } catch (AssertionError e) {
                System.out.println(e.getMessage());
                System.out.println(topMovie);
                System.out.println(movieDetails);
                System.out.println();
            }

            movieDetailsList.add(movieDetails);
        }

        List<Movie> movieDetailsWithoutRank = movieDetailsList.stream().filter(m -> Objects.isNull(m.getRank()))
            .collect(Collectors.toList());
        System.out.println("Movie details without a rank:");
        movieDetailsWithoutRank.forEach(System.out::println);
        System.out.println();

        List<Movie> movieDetailsWithNullTitles = movieDetailsList.stream().filter(m -> Objects.isNull(m.getTitle()))
            .collect(Collectors.toList());
        System.out.println("Movie details without a title (null):");
        movieDetailsWithNullTitles.forEach(System.out::println);
        System.out.println();

        List<Movie> movieDetailsWithBlankTitles = movieDetailsList.stream().filter(m -> "".equals(m.getTitle()))
            .collect(Collectors.toList());
        System.out.println("Movie details without a title (blank):");
        movieDetailsWithBlankTitles.forEach(System.out::println);
        System.out.println();

        List<Movie> movieDetailsWithoutReleaseYear = movieDetailsList.stream().filter(m -> Objects.isNull(m.getReleaseYear()))
            .collect(Collectors.toList());
        System.out.println("Movie details without a release year:");
        movieDetailsWithoutReleaseYear.forEach(System.out::println);
        System.out.println();

        List<Movie> movieDetailsWithoutRating = movieDetailsList.stream().filter(m -> Objects.isNull(m.getRating()))
            .collect(Collectors.toList());
        System.out.println("Movie details without a rating (null):");
        movieDetailsWithoutRating.forEach(System.out::println);
        System.out.println();

        List<Movie> moviesDetailsWithBlankRating = movieDetailsList.stream().filter(m -> "".equals(m.getRating()))
            .collect(Collectors.toList());
        System.out.println("Movie details without a rating (blank):");
        moviesDetailsWithBlankRating.forEach(System.out::println);
        System.out.println();

        List<Movie> movieDetailsWithoutDirector = movieDetailsList.stream().filter(m -> Objects.isNull(m.getDirector()))
            .collect(Collectors.toList());
        System.out.println("Movie details without a director (null):");
        movieDetailsWithoutDirector.forEach(System.out::println);
        System.out.println();

        List<Movie> moviesDetailsWithBlankDirector = movieDetailsList.stream().filter(m -> "".equals(m.getDirector()))
            .collect(Collectors.toList());
        System.out.println("Movie details without a director (blank):");
        moviesDetailsWithBlankDirector.forEach(System.out::println);
        System.out.println();


    }
}
