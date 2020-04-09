package com.github.nbsllc.domain;

/**
 * User API data.
 */
public class User {
    private long id;
    private String name;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    public Address getAddress() {
        return address;
    }

    public Company getCompany() {
        return company;
    }

    public String getEmail() {
        return email;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
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
}
