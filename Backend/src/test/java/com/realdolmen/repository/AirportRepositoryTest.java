package com.realdolmen.repository;

import com.realdolmen.repository.beans.AirportRepositoryBean;
import com.realdolmen.utilities.persistence.TestData;
import com.realdolmen.utilities.persistence.TestDataLocation;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by WVDAZ49 on 3/09/2016.
 */

public class AirportRepositoryTest extends AbstractRepositoryTest<AirportRepositoryBean>{

    @Test
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void getAllAirportsTest(){
        assertEquals(5, getRepository().getAllAirports().size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void getFilteredAirportsForCountry(){
        assertEquals(1, getRepository().getFilteredAirports("België").size());
    }
    
    @Test
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void getFilteredAirportsForCountryPart(){
        assertEquals(1, getRepository().getFilteredAirports("lgië").size());
    }
    
    @Test
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void getFilteredAirportsForCountryPart2(){
        assertEquals(1, getRepository().getFilteredAirports("stanb").size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void getFilteredAirportsForLowerCaseCountry(){
        assertEquals(1, getRepository().getFilteredAirports("turkey").size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void getFilteredAirportsForLowerAndUpperCaseCountry(){
        assertEquals(2, getRepository().getFilteredAirports("ElGi").size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void getFilteredAirportsForCity(){
        assertEquals(1, getRepository().getFilteredAirports("Brussel").size());
    }
    
    @Test
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void getFilteredAirportsForCityPart(){
        assertEquals(1, getRepository().getFilteredAirports("ssel").size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void getFilteredAirportsForCityPart2(){
        assertEquals(1, getRepository().getFilteredAirports("russ").size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void getFilteredAirportsForLowerCaseCity(){
        assertEquals(1, getRepository().getFilteredAirports("paris").size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void getFilteredAirportsForLowerAndUpperCaseCity(){
        assertEquals(1, getRepository().getFilteredAirports("RusSE").size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void getFilteredAirportsForRegion(){
        assertEquals(4, getRepository().getFilteredAirports("Europa").size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void getFilteredAirportsForRegionPart(){
        assertEquals(4, getRepository().getFilteredAirports("ropa").size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void getFilteredAirportsForRegionPart2(){
        assertEquals(1, getRepository().getFilteredAirports("frika").size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void getFilteredAirportsForLowerCaseRegion(){
        assertEquals(4, getRepository().getFilteredAirports("europa").size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void getFilteredAirportsForLowerAndUpperCaseRegion(){
        assertEquals(4, getRepository().getFilteredAirports("eUroPa").size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void getFilteredAirportsForCode(){
        assertEquals(1, getRepository().getFilteredAirports("BBR-548").size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void getFilteredAirportsForCodePart(){
        assertEquals(1, getRepository().getFilteredAirports("627").size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void getFilteredAirportsForCodePart2(){
        assertEquals(1, getRepository().getFilteredAirports("L-34").size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void getFilteredAirportsForLowerCaseCode(){
        assertEquals(1, getRepository().getFilteredAirports("bbb-284").size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void getFilteredAirportsForLowerAndUpperCaseCode(){
        assertEquals(1, getRepository().getFilteredAirports("BbB-284").size());
    }


    @Test
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void getFilteredAirportsForName(){
        assertEquals(1, getRepository().getFilteredAirports("Amsterdam grote vlieg").size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void getFilteredAirportsForNamePart(){
        assertEquals(1, getRepository().getFilteredAirports("Ostend").size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void getFilteredAirportsForNamePart2(){
        assertEquals(1, getRepository().getFilteredAirports("van brussel").size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void getFilteredAirportsForLowerCaseName(){
        assertEquals(1, getRepository().getFilteredAirports("amsterdam grote vlieg").size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void getFilteredAirportsForLowerAndUpperCaseName(){
        assertEquals(1, getRepository().getFilteredAirports("BoEmcHakAlaKa").size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void getFilteredAirportsMixedInput(){
        assertEquals(3, getRepository().getFilteredAirports("st").size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void getFilteredAirportsEmpty(){
        assertEquals(5, getRepository().getFilteredAirports("").size());
    }

    @Test(expected = IllegalArgumentException.class)
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void getFilteredAirportsNull(){
        assertEquals(0, getRepository().getFilteredAirports(null).size());
    }


}