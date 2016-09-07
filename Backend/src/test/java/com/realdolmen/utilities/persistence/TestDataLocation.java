package com.realdolmen.utilities.persistence;

public enum TestDataLocation {

    DATA_AIRPORT("dataAirports.xml"),
    DATA_CUSTOMERS("dataCustomers.xml"),
    DATA_FLIGHT("dataFlights.xml");

    private String value;

    TestDataLocation(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
