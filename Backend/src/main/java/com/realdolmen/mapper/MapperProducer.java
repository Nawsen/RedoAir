package com.realdolmen.mapper;

import com.realdolmen.VO.CustomerFlightVO;
import com.realdolmen.domain.Flight;
import com.realdolmen.domain.MapperType;
import com.realdolmen.qualifiers.EntityMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by WVDAZ49 on 5/09/2016.
 */

@ApplicationScoped
public class MapperProducer {
    private Map<MapperType, MapperFacade> mappers;

    @PostConstruct
    public void init() {
        mappers = new HashMap<>();
    }

    @Produces
    @EntityMapper(type = MapperType.UNDEFINED)
    public MapperFacade getMapper(InjectionPoint ip) {
        MapperType type = ip.getAnnotated().getAnnotation(EntityMapper.class).type();
        if (!mappers.containsKey(type)) {
            MapperFactory factory = new DefaultMapperFactory.Builder().build();
            ConverterFactory converterFactory = factory.getConverterFactory();

            //register Custom converters here
            //converterFactory.registerConverter("fullNameConverter", new FullNameConverter());

            configureMapperFactory(factory, type);
            mappers.put(type, factory.getMapperFacade());
        }

        return mappers.get(type);
    }

    public void configureMapperFactory(MapperFactory factory, MapperType type) {
        switch (type) {
            case CUSTOMER_FLIGHTS:
                configureCustomerFlightsMapper(factory);
                break;
        }
    }

    private void configureCustomerFlightsMapper(MapperFactory factory) {
        factory.classMap(Flight.class, CustomerFlightVO.class)
                .exclude("discounts")
                .byDefault()
                .register();
    }
}