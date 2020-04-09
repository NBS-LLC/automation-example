package com.github.nbsllc;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.Objects;

public class UITestBase {
    protected static Logger logger = LogManager.getLogger();

    private WebDriver driver;

    @BeforeTest
    public void configureWebDriver() {
        WebDriverManager.chromedriver().setup();
    }

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void startWebDriver() {
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void stopWebDriver() {
        if (Objects.nonNull(getDriver())) {
            getDriver().close();
        }
    }
}
