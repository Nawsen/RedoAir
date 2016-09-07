package com.realdolmen.service;

import com.realdolmen.VO.ApplicationSettingsVO;

/**
 * Created by WVDAZ49 on 7/09/2016.
 */
public interface ApplicationService {
    ApplicationSettingsVO getAllSettings();
    void setAllSettings(ApplicationSettingsVO applicationSettingsVO);
}
