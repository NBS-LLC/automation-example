package com.github.nbsllc.pages;

import com.github.nbsllc.domain.Product;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchResultsPage extends BasePage {
    public SearchResultsPage(WebDriver driver) {
        super(driver);
        assertThat(driver.getTitle()).isEqualTo("Search - My Store");
    }

    public List<Product> getSearchResults() {
        return null;
    }
}
