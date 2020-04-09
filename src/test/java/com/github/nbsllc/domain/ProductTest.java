package com.github.nbsllc.domain;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {
    @Test
    public void testThatBuilderCreatesValidProducts() {
        Product product = Product.Builder()
            .withName("Unit Test Product")
            .withPrice(12.34)
            .withUrl("http://localhost/unit-test-product")
            .build();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(product.getName()).isEqualTo("Unit Test Product");
        softly.assertThat(product.getPrice()).isEqualTo(12.34);
        softly.assertThat(product.getUrl()).contains("/unit-test-product");
        softly.assertAll();
    }

    @Test
    public void testThatBuilderWithMethodsAreOptional() {
        Product product = Product.Builder()
            .withName("Unit Test Product")
            .withUrl("http://localhost/unit-test-product")
            .build();

        assertThat(product.getPrice()).isNull();
    }

    @Test
    public void testThatToStringIsMeaningful() {
        Product product = Product.Builder()
            .withName("Unit Test Product")
            .withPrice(12.34)
            .withUrl("http://localhost/unit-test-product")
            .build();

        assertThat(product.toString()).isEqualTo("Unit Test Product - 12.34");
    }
}