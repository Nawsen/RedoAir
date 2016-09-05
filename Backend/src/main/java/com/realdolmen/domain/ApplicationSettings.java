package com.realdolmen.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by vandorpe on 11/05/2016.
 */

@Table(name = ApplicationSettings.TABLE_NAME)
@Entity
public class ApplicationSettings {

    /**
     * constant that represents the real table name
     * constains the keys
     */
    public static final String KEY = "setting_name";

    /**
     * constant that represents the real table name
     * contains the values
     */
    public static final String VALUE = "setting_value";

    /**
     * constant that represents the real table name
     */
    public static final String VALID_FOR = "valid_for";

    /**
     * constant that represents the real table name
     */
    public static final String TABLE_NAME = "application_settings";

    @Id
    private Long id;

    @Column(name = KEY)
    private String key;

    @Column(name = VALUE)
    private String value;

    @Column(name = VALID_FOR, nullable = true)
    private String validFor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValidFor() {
        return validFor;
    }

    public void setValidFor(String validFor) {
        this.validFor = validFor;
    }
}
