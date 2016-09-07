package com.realdolmen.mapper;

import com.realdolmen.VO.*;
import com.realdolmen.domain.*;
import com.realdolmen.mapper.converter.AvailableSeatConverter;
import com.realdolmen.qualifiers.EntityMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.criteria.Order;
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
            converterFactory.registerConverter("availableSeatConverter", new AvailableSeatConverter());

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
            case CUSTOMER_LOGIN:
                configureCustomerLoginMapper(factory);
                break;
            case CUSTOMER_REGISTER:
                configureCustomerRegisterMapper(factory);
                break;
            case FLIGHT_TICKET_DETAILS:
                configureTicketDetails(factory);
                break;
            case BOOKING_CREATE:
                configureBookingCreateMapper(factory);
                break;
        }
    }

    private void configureBookingCreateMapper(MapperFactory factory) {
        factory.classMap(Booking.class, BookingVO.class)
                .byDefault()
                .register();
    }

    private void configureTicketDetails(MapperFactory factory) {
        factory.classMap(Ticket.class, TicketOrderDetailsVO.class)
                .field("seatType", "seatType")
                .field("soldPrice", "price")
                .register();
    }

    private void configureCustomerRegisterMapper(MapperFactory factory) {
        factory.classMap(Customer.class, CustomerRegisterVO.class)
                .field("firstName", "firstName")
                .field("lastName", "lastName")
                .field("email", "email")
                .field("password", "password")
                .register();
    }

    private void configureCustomerLoginMapper(MapperFactory factory) {
        factory.classMap(Customer.class, CustomerLoginVO.class)
                .field("email", "email")
                .field("password", "password")
                .register();
    }

    private void configureCustomerFlightsMapper(MapperFactory factory) {
        factory.classMap(Flight.class, CustomerFlightVO.class)
                .field("flightId", "flightId")
                .field("flightNumber", "flightNumber")
                .field("departureTime", "departureTime")
                .field("arrivalTime", "arrivalTime")
                .field("departedFrom", "departedFrom")
                .field("arrivalIn", "arrivalIn")
                .fieldMap("tickets" ,"availableSeats").converter("availableSeatConverter").add()
                .register();
    }
}