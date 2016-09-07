package com.realdolmen.repository;

import com.realdolmen.repository.beans.AirportRepositoryBean;
import com.realdolmen.repository.beans.FlightRepositoryBean;
import com.realdolmen.utilities.persistence.TestData;
import com.realdolmen.utilities.persistence.TestDataLocation;
import org.dom4j.IllegalAddException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by WVDAZ49 on 3/09/2016.
 */

public class FlightRepositoryTest extends AbstractRepositoryTest<FlightRepositoryBean>{

    @Test
    @TestData(dataSet = TestDataLocation.DATA_FLIGHT)
    public void getFlightsForCorrectAirportCode(){
        assertEquals(3, getRepository().getFlightsForAirport("BBR-548", "QQL-228").size());
    }
    @Test
    @TestData(dataSet = TestDataLocation.DATA_FLIGHT)
    public void getFlightsForWrongAirportCode(){
        assertEquals(0, getRepository().getFlightsForAirport("BBR-548", "DDD-888").size());
    }
    @Test
    @TestData(dataSet = TestDataLocation.DATA_FLIGHT)
    public void getFlightsForWrongAirportCode2(){
        assertEquals(0, getRepository().getFlightsForAirport("BBB-548", "dd").size());
    }
    @Test
    @TestData(dataSet = TestDataLocation.DATA_FLIGHT)
    public void getFlightsForEmptyCode(){
        assertEquals(0, getRepository().getFlightsForAirport("", "").size());
    }
    @Test
    @TestData(dataSet = TestDataLocation.DATA_FLIGHT)
    public void getFlightsForNullCodes(){
        assertEquals(0, getRepository().getFlightsForAirport(null, null).size());
    }
    @Test
    @TestData(dataSet = TestDataLocation.DATA_FLIGHT)
    public void getFlightsForPartialCode(){
        assertEquals(0, getRepository().getFlightsForAirport("-", "-").size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.DATA_FLIGHT)
    public void getFlightByIdGoodTest(){
        assertEquals("25489756", getRepository().getFlightFromId(1000L).getFlightNumber());
    }
    @Test(expected = IllegalArgumentException.class)
    @TestData(dataSet = TestDataLocation.DATA_FLIGHT)
    public void getFlightByIdNull(){
        getRepository().getFlightFromId(null);
    }
    @Test(expected = IllegalArgumentException.class)
    @TestData(dataSet = TestDataLocation.DATA_FLIGHT)
    public void getFlightByIdWrongNumber(){
        getRepository().getFlightFromId(900L);
    }
}