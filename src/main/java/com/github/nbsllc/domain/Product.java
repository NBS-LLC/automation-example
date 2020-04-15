package com.github.nbsllc.domain;

/**
 * Search result data (AKA products).
 */
public class Product {
    private String url;
    private String name;
    private Double price;

    public Product(Builder builder) {
        url = builder.url;
        name = builder.name;
        price = builder.price;
    }

    public static Builder builder() {
        return new Builder();
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

    @Override
    public String toString() {
        return getName() + " - " + getPrice();
    }

    public static final class Builder {
        private String url;
        private String name;
        private Double price;

        private Builder() {
            /* Does Nothing */
        }

        public Product build() {
            return new Product(this);
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withPrice(Double price) {
            this.price = price;
            return this;
        }

        public Builder withUrl(String url) {
            this.url = url;
            return this;
        }
    }
}
