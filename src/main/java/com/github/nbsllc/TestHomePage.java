package com.github.nbsllc;

import com.github.nbsllc.domain.Product;
import com.github.nbsllc.pages.HomePage;
import com.github.nbsllc.pages.SearchResultsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class TestHomePage {
    WebDriver driver;

    @BeforeTest
    public void configureWebDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void startWebDriver() {
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void stopWebDriver() {
        if (Objects.nonNull(driver)) {
            driver.close();
        }
    }

    @Test
    public void testThatSearchingForValidItemReturnsResults() {
        HomePage homePage = HomePage.load(driver);
        SearchResultsPage searchResultsPage = homePage.searchFor("dress");
        List<Product> products = searchResultsPage.getSearchResults();

        SoftAssertions softly = new SoftAssertions();
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

    @Test
    public void testThatTheHomePageLoads() {
        driver.get("http://automationpractice.com/index.php");
        assertThat(driver.getTitle()).isEqualTo("My Store");
    }
}
