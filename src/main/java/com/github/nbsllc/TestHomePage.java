package com.github.nbsllc;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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
    public void testThatTheHomePageLoads() {
        driver.get("http://automationpractice.com/index.php");
        assertThat(driver.getTitle()).isEqualTo("My Store");
    }
}
