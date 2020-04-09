package com.github.nbsllc.pages;

import org.apache.logging.log4j.Level;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePage extends BasePage {
    @FindBy(id = "search_query_top")
    private WebElement txtSearch;

    @FindBy(css = "button.button-search")
    private WebElement btnSearch;

    public HomePage(WebDriver driver) {
        super(driver);

        logger.log(Level.getLevel("STEP"), "Loading the site's home page.");
        assertThat(driver.getTitle()).isEqualTo("My Store");
    }

    /**
     * Loads the site's home page.
     *
     * @param driver The current Selenium web driver.
     * @return An instance of this class.
     */
    public static HomePage load(WebDriver driver) {
        driver.get("http://automationpractice.com/index.php");
        return new HomePage(driver);
    }

    /**
     * Search for items by keywords.
     *
     * @param keywords The keywords.
     * @return An instance of the search results page.
     */
    public SearchResultsPage searchFor(String keywords) {
        logger.log(Level.getLevel("STEP"), "Searching for: {}.", keywords);

        txtSearch.clear();
        txtSearch.sendKeys(keywords);
        btnSearch.click();

        return new SearchResultsPage(getDriver());
    }
}
