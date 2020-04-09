package com.github.nbsllc.pages;

import com.github.nbsllc.domain.Product;
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
        assertThat(driver.getTitle()).isEqualTo("Search - My Store");
    }

    /**
     * Get a collection of search results (AKA products).
     *
     * @return The collection, which could be empty (no results).
     */
    public List<Product> getSearchResults() {
        List<Product> products = new ArrayList<>();

        for (WebElement webElement : dblProducts) {
            String url = webElement.findElement(By.cssSelector("a.product_img_link")).getAttribute("href");
            String name = webElement.findElement(By.cssSelector("a.product-name")).getText().trim();

            WebElement lblPrice = webElement.findElement(By.cssSelector("span.price"));
            double price = currencyToDouble(lblPrice.getAttribute("innerHTML").trim());

            products.add(
                Product.Builder()
                    .withUrl(url)
                    .withName(name)
                    .withPrice(price)
                    .build()
            );
        }

        return products;
    }

    /**
     * Try to get the page's warning message, if it exists.
     *
     * @return The warning message or null.
     */
    public String getWarningMessage() {
        if (!hasWarning()) {
            return null;
        }

        return lblWarning.getText().trim();
    }

    /**
     * Determine if the page is displaying a warning message.
     *
     * @return True if a warning is displayed.
     */
    public boolean hasWarning() {
        try {
            return lblWarning.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private double currencyToDouble(String price) {
        // TODO: Handle non-US currencies.
        return Double.parseDouble(price.replace("$", ""));
    }
}
