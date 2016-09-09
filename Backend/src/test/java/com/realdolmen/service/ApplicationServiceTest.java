package com.realdolmen.service;

import com.realdolmen.VO.ApplicationSettingsVO;
import com.realdolmen.domain.ApplicationSettings;
import com.realdolmen.repository.ApplicationSettingsRepository;
import com.realdolmen.service.beans.ApplicationSettingsBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by WVDAZ49 on 9/09/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ApplicationServiceTest {
    @InjectMocks
    private ApplicationSettingsBean service;

    @Mock
    private ApplicationSettingsRepository applicationSettingsRepository;

    @Test
    public void getAllSettingsTest(){
        when(applicationSettingsRepository.findValue("PROFIT_PERCENTAGE")).thenReturn("1");
        service.getAllSettings();
        verify(applicationSettingsRepository, times(1)).findValue("PROFIT_PERCENTAGE");
        verifyNoMoreInteractions(applicationSettingsRepository);
    }

    @Test
    public void getAllSettingsVal(){
        when(applicationSettingsRepository.findValue("PROFIT_PERCENTAGE")).thenReturn("1");
        ApplicationSettingsVO vo = service.getAllSettings();
        assertEquals(new Integer(1), vo.getDefaultProfit());
    }

    @Test
    public void setAllSettings(){
        ApplicationSettings applicationSettings = new ApplicationSettings();
        applicationSettings.setKey("PROFIT_PERCENTAGE");
        applicationSettings.setValue("1");
        when(applicationSettingsRepository.find("PROFIT_PERCENTAGE")).thenReturn(applicationSettings);
        ApplicationSettingsVO vo = new ApplicationSettingsVO();
        vo.setDefaultProfit(5);
        service.setAllSettings(vo);
        verify(applicationSettingsRepository, times(1)).find("PROFIT_PERCENTAGE");
        verify(applicationSettingsRepository, times(1)).saveDefault(applicationSettings);
        verifyNoMoreInteractions(applicationSettingsRepository);
        assertEquals("5", applicationSettings.getValue());
    }

}
