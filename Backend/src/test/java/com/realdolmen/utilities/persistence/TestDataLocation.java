package com.realdolmen.utilities.persistence;

public enum TestDataLocation {

    DATA1("data1.xml"),
    DATA_CUSTOMERS("dataCustomers.xml");

    private String value;

    TestDataLocation(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
