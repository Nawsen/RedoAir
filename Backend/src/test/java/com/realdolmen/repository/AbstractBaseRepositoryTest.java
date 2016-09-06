package com.realdolmen.repository;

import com.realdolmen.domain.Airport;
import com.realdolmen.domain.Flight;
import com.realdolmen.repository.beans.AirportRepositoryBean;
import com.realdolmen.utilities.persistence.TestData;
import com.realdolmen.utilities.persistence.TestDataLocation;
import org.junit.Test;

import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.assertEquals;


/**
 * Created by WVDAZ49 on 3/09/2016.
 */
public class AbstractBaseRepositoryTest extends AbstractRepositoryTest<AirportRepositoryBean>{

    @Test
    public void testGetEntityClass(){
        assert(getRepository().getEntityClass() == Airport.class);
    }
    @Test
    public void testSetEntityClass(){
        assert(getRepository().getEntityClass() == Airport.class);
        getRepository().setEntityClass(Flight.class);
        assert(getRepository().getEntityClass() == Flight.class);
    }
    @Test
    public void testGetEntityClassNull(){
        getRepository().setEntityClass(null);
        assert(getRepository().getEntityClass() == Airport.class);
    }

    @Test
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void testFindByPrimaryKey(){
        assertEquals("BBR-548", getRepository().findByPrimaryKey(1000L).getCode());
    }

    @Test(expected = IllegalArgumentException.class)
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void testFindByPrimaryKeyNotValid(){
        getRepository().findByPrimaryKey(900L);
    }

    @Test
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void testFindAll(){
        assertEquals(5, getRepository().findAll().size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void testUpdate(){
        Airport a = getRepository().findByPrimaryKey(1000L);
        a.setCode("CHANGED");
        getRepository().update(a);
        assertEquals("CHANGED", getRepository().findByPrimaryKey(1000L).getCode());
    }

    @Test
    public void testInsert(){
        Airport a = new Airport();
        a.setCode("DDL-985");
        a.setName("test vlieghaven");
        beginTransaction();
        getRepository().insert(a);
        commitTransaction();
        assertEquals(1, getRepository().findAll().size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void testDeleteById(){
        beginTransaction();
        getRepository().deleteById(1001L);
        commitTransaction();
        assertEquals(4, getRepository().findAll().size());
    }

    @Test(expected = EntityNotFoundException.class)
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void testDeleteByIdWrong(){
        getRepository().deleteById(900L);
    }

    @Test
    @TestData(dataSet = TestDataLocation.DATA_AIRPORT)
    public void testDeleteByEntity(){
        Airport a = getRepository().findByPrimaryKey(1000L);
        beginTransaction();
        getRepository().delete(a);
        getRepository().flush();
        commitTransaction();
        assertEquals(4, getRepository().findAll().size());
    }



}
