package com.github.nbsllc;

import com.github.nbsllc.domain.Product;
import com.github.nbsllc.pages.HomePage;
import com.github.nbsllc.pages.SearchResultsPage;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.List;

public class TestSearchFunctionality extends UITestBase {
    @Test
    public void testThatSearchingForInvalidItemReturnsNothing() {
        HomePage homePage = HomePage.load(getDriver());
        SearchResultsPage searchResultsPage = homePage.searchFor("invalid");
        List<Product> products = searchResultsPage.getSearchResults();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(searchResultsPage.hasWarning()).isTrue();
        softly.assertThat(searchResultsPage.getWarningMessage()).contains("No results were found");
        softly.assertThat(products).isEmpty();
        softly.assertAll();
    }

    @Test
    public void testThatSearchingForValidItemReturnsResults() {
        HomePage homePage = HomePage.load(getDriver());
        SearchResultsPage searchResultsPage = homePage.searchFor("dress");
        List<Product> products = searchResultsPage.getSearchResults();

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
