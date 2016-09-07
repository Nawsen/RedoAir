package com.realdolmen.service.beans;

import com.realdolmen.VO.ApplicationSettingsVO;
import com.realdolmen.domain.ApplicationSettings;
import com.realdolmen.repository.ApplicationSettingsRepository;
import com.realdolmen.service.ApplicationService;

import javax.inject.Inject;

/**
 * Created by WVDAZ49 on 7/09/2016.
 */
public class ApplicationSettingsBean implements ApplicationService {

    @Inject
    private ApplicationSettingsRepository applicationSettingsRepository;


    @Override
    public ApplicationSettingsVO getAllSettings() {
        ApplicationSettingsVO vo = new ApplicationSettingsVO();
        vo.setDefaultProfit(Integer.parseInt(applicationSettingsRepository.findValue("PROFIT_PERCENTAGE")));
        return vo;
    }

    @Override
    public void setAllSettings(ApplicationSettingsVO applicationSettingsVO) {
        ApplicationSettings appset = applicationSettingsRepository.find("PROFIT_PERCENTAGE");
        appset.setValue(applicationSettingsVO.getDefaultProfit().toString());
        applicationSettingsRepository.saveDefault(appset);
    }
}
