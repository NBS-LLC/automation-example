package com.github.nbsllc.domain;

/**
 * User API data.
 */
public class User {
    private Long id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    private User(Builder builder) {
        name = builder.name;
        username = builder.username;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Address getAddress() {
        return address;
    }

    public Company getCompany() {
        return company;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public String getWebsite() {
        return website;
    }

    public static class Address {
        private String street;
        private String suite;
        private String city;
        private String zipcode;
        private Geo geo;

        public String getCity() {
            return city;
        }

        public Geo getGeo() {
            return geo;
        }

        public String getStreet() {
            return street;
        }

        public String getSuite() {
            return suite;
        }

        public String getZipcode() {
            return zipcode;
        }

        public static class Geo {
            private double lat;
            private double lng;

            public double getLat() {
                return lat;
            }

            public double getLng() {
                return lng;
            }
        }
    }

    public static class Company {
        private String name;
        private String catchPhrase;
        private String bs;

        public String getBs() {
            return bs;
        }

        public String getCatchPhrase() {
            return catchPhrase;
        }

        public String getName() {
            return name;
        }
    }

    public static final class Builder {
        private String name;
        private String username;

        private Builder() {
            /* Dose Nothing */
        }

        public User build() {
            return new User(this);
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }
    }
}
