package com.realdolmen.domain;

import javax.inject.Named;
import javax.persistence.*;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "getFilteredAirports", query = "SELECT a FROM Airport a WHERE" +
                " UPPER(a.code) LIKE UPPER(:filter) OR" +
                " UPPER(a.name) LIKE UPPER(:filter) OR" +
                " UPPER(a.location.country) LIKE UPPER(:filter) OR" +
                " UPPER(a.location.city) LIKE UPPER(:filter) OR" +
                " UPPER(a.location.region) LIKE UPPER(:filter)")
})

public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    @Embedded
    private Location location;

    public Airport() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
