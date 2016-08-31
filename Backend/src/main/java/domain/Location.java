package domain;

import javax.persistence.Embeddable;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */
@Embeddable
public class Location {
    private String city;
    private String country;
    private String region;

    public Location() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
