package com.realdolmen.repository;

import com.realdolmen.domain.ApplicationSettings;

import javax.ejb.Local;
import java.util.Map;

/**
 * Created by WVDAZ49 on 5/09/2016.
 */
@Local
public interface ApplicationSettingsRepository {

    ApplicationSettings find(String key);

    String findValue(String key);

    Map<String, String> findValues(String... keys);

    void saveDefault(ApplicationSettings applicationSettings);
}

