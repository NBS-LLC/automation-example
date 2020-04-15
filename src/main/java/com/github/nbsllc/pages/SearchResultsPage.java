package com.github.nbsllc.pages;

import com.github.nbsllc.domain.Product;
import org.apache.logging.log4j.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchResultsPage extends BasePage {
    @FindBy(css = "ul.product_list > li")
    private List<WebElement> dblProducts;

    @FindBy(css = "p.alert-warning")
    private WebElement lblWarning;

    public SearchResultsPage(WebDriver driver) {
        super(driver);

        logger.log(Level.getLevel("STEP"), "Loading the site's search results page.");
        assertThat(driver.getTitle()).isEqualTo("Search - My Store");
    }

    /**
     * Get a collection of search results (AKA products).
     *
     * @return The collection, which could be empty (no results).
     */
    public List<Product> getSearchResults() {
        logger.log(Level.getLevel("STEP"), "Getting the search results.");

        List<Product> products = new ArrayList<>();

        for (WebElement webElement : dblProducts) {
            String url = webElement.findElement(By.cssSelector("a.product_img_link")).getAttribute("href");
            String name = webElement.findElement(By.cssSelector("a.product-name")).getText().trim();

            WebElement lblPrice = webElement.findElement(By.cssSelector("span.price"));
            double price = currencyToDouble(lblPrice.getAttribute("innerHTML").trim());

            products.add(
                Product.builder()
                    .withUrl(url)
                    .withName(name)
                    .withPrice(price)
                    .build()
            );
        }

        logger.info(products);
        return products;
    }

    /**
     * Try to get the page's warning message, if it exists.
     *
     * @return The warning message or null.
     */
    public String getWarningMessage() {
        logger.log(Level.getLevel("STEP"), "Getting the page's warning message.");

        if (!hasWarning()) {
            logger.info("The page did not contain a warning message.");
            return null;
        }

        String message = lblWarning.getText().trim();
        logger.info(message);
        return message;
    }

    /**
     * Determine if the page is displaying a warning message.
     *
     * @return True if a warning is displayed.
     */
    public boolean hasWarning() {
        logger.log(Level.getLevel("STEP"), "Determining if the page contained a warning message.");

        boolean hasWarning;
        try {
            hasWarning = lblWarning.isDisplayed();
        } catch (NoSuchElementException e) {
            hasWarning = false;
        }

        logger.info(hasWarning);
        return hasWarning;
    }

    private double currencyToDouble(String price) {
        // TODO: Handle non-US currencies.
        return Double.parseDouble(price.replace("$", ""));
    }
}
