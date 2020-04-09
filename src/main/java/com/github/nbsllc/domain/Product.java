package com.github.nbsllc.domain;

/**
 * Search result data (AKA products).
 */
public class Product {
    private String url;
    private String name;
    private Double price;

    public Product(ProductBuilder builder) {
        this.url = builder.url;
        this.name = builder.name;
        this.price = builder.price;
    }

    public static ProductBuilder Builder() {
        return new ProductBuilder();
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }

    public static class ProductBuilder {
        private String url;
        private String name;
        private Double price;

        protected ProductBuilder() {
            /* Does Nothing */
        }

        public Product build() {
            return new Product(this);
        }

        public ProductBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder withPrice(Double price) {
            this.price = price;
            return this;
        }

        public ProductBuilder withUrl(String url) {
            this.url = url;
            return this;
        }
    }
}
