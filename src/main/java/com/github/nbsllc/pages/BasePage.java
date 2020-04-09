package com.github.nbsllc.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Common configuration and initialization for all pages.
 * <p>
 * See https://tinyurl.com/w774s4s for element prefixes.
 */
public abstract class BasePage {
    protected static Logger logger = LogManager.getLogger();

    private WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(getDriver(), this);
    }

    public WebDriver getDriver() {
        return driver;
    }
}
