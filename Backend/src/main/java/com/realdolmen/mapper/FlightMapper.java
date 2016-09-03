package com.realdolmen.mapper;


import com.realdolmen.VO.CustomerFlightVO;
import com.realdolmen.domain.Flight;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * Created by WVDAZ49 on 1/09/2016.
 */
public class FlightMapper extends ConfigurableMapper {
    private MapperFacade mapper;

    public FlightMapper() {
        final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapper = mapperFactory.getMapperFacade();
    }
    protected void configure() {
        final MapperFactory factory = new DefaultMapperFactory.Builder().build();
        factory.classMap(Flight.class, CustomerFlightVO.class)
                .byDefault()
                .exclude("discounts")
                .register();
    }
}