package com.github.nbsllc;

import com.github.nbsllc.domain.Product;
import com.github.nbsllc.pages.HomePage;
import com.github.nbsllc.pages.SearchResultsPage;
import org.apache.logging.log4j.Level;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.List;

public class TestSearchFunctionality extends UITestBase {
    @Test
    public void testThatSearchingForInvalidItemReturnsNothing() {
        logger.log(
            Level.getLevel("STEP"),
            "Testing that no results are returned when searching for invalid products."
        );

        HomePage homePage = HomePage.load(getDriver());
        SearchResultsPage searchResultsPage = homePage.searchFor("invalid");
        List<Product> products = searchResultsPage.getSearchResults();

        logger.log(
            Level.getLevel("STEP"),
            "Verifying that a warning message is displayed."
        );

        logger.log(
            Level.getLevel("STEP"),
            "Verifying that no products are displayed."
        );

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(searchResultsPage.hasWarning()).isTrue();
        softly.assertThat(searchResultsPage.getWarningMessage()).contains("No results were found");
        softly.assertThat(products).isEmpty();
        softly.assertAll();
    }

    @Test
    public void testThatSearchingForValidItemReturnsResults() {
        logger.log(
            Level.getLevel("STEP"),
            "Testing that results are returned when searching for valid products."
        );

        HomePage homePage = HomePage.load(getDriver());
        SearchResultsPage searchResultsPage = homePage.searchFor("dress");
        List<Product> products = searchResultsPage.getSearchResults();

        logger.log(
            Level.getLevel("STEP"),
            "Verifying that a warning message is not displayed."
        );

        logger.log(
            Level.getLevel("STEP"),
            "Verifying that the correct number of products are displayed."
        );

        logger.log(
            Level.getLevel("STEP"),
            "Verifying that the product prices are correct."
        );

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(searchResultsPage.hasWarning()).isFalse();
        softly.assertThat(products).hasSize(7);
        softly.assertThat(products).extracting(Product::getPrice)
            .containsExactlyInAnyOrder(
                28.98,
                50.99,
                30.50,
                16.40,
                26.00,
                16.51,
                27.00
            );
        softly.assertAll();
    }
}
