package com.github.nbsllc;

import org.apache.logging.log4j.Level;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestHomePage extends UITestBase {
    @Test
    public void testThatTheHomePageLoads() {
        logger.log(Level.getLevel("STEP"), "Testing that the site's home page loads.");

        getDriver().get("http://automationpractice.com/index.php");
        assertThat(getDriver().getTitle()).isEqualTo("My Store");
    }
}
