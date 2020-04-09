package com.github.nbsllc;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestHomePage extends UITestBase {
    @Test
    public void testThatTheHomePageLoads() {
        getDriver().get("http://automationpractice.com/index.php");
        assertThat(getDriver().getTitle()).isEqualTo("My Store");
    }
}
