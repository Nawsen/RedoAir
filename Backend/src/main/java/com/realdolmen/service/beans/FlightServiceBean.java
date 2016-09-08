package com.realdolmen.service.beans;

import com.realdolmen.VO.AvailableSeatsVO;
import com.realdolmen.VO.CustomerFlightVO;
import com.realdolmen.VO.EmployeeFlightVO;
import com.realdolmen.domain.Flight;
import com.realdolmen.domain.MapperType;
import com.realdolmen.domain.SeatType;
import com.realdolmen.domain.Ticket;
import com.realdolmen.qualifiers.EntityMapper;
import com.realdolmen.repository.FlightRepository;
import com.realdolmen.service.FlightService;
import ma.glasnost.orika.MapperFacade;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.*;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */
@Stateless
public class FlightServiceBean implements FlightService {

    @Inject
    private FlightRepository flightRepo;

    @Inject
    @EntityMapper(type = MapperType.CUSTOMER_FLIGHTS)
    private MapperFacade customerFlightMapper;

    @Inject
    @EntityMapper(type = MapperType.AVAILABLEFLIGHTS)
    private MapperFacade availableFlightMapper;

    @Override
    public List<CustomerFlightVO> findFlights(String arrivalId, String departureId, Date startDate, Date endDate, SeatType type, Integer free) {
        List<Flight> vos = flightRepo.getFlightsForAirport(arrivalId, departureId);
        vos = filterFlights(vos, startDate, endDate, type, free);

        return customerFlightMapper.mapAsList(vos, CustomerFlightVO.class);
    }

    @Override
    public List<CustomerFlightVO> findFlights(String departureCode, String arrivalCode) {
        List<Flight> vos = flightRepo.getFlightsForAirport(arrivalCode, departureCode);

        return customerFlightMapper.mapAsList(vos, CustomerFlightVO.class);
    }

    private List<Flight> filterFlights(List<Flight> flights, Date startDate, Date endDate, SeatType type, Integer free) {
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight f : flights) {
            if (f.getDepartureTime().after(startDate) &&
                    f.getDepartureTime().before(endDate) &&
                    checkForFreeTickets(f, type, free)) {
                filteredFlights.add(f);
            }
        }
        return filteredFlights;
    }

    public boolean checkForFreeTickets(Flight flight, SeatType type, Integer free) {
        Map<SeatType, Integer> map = new HashMap<>();
        map.put(SeatType.BUSINESS, 0);
        map.put(SeatType.ECONOMY, 0);
        map.put(SeatType.FIRST_CLASS, 0);
        for (Ticket t : flight.getTickets()) {
            if (!t.getSold()) {
                map.put(t.getSeatType(), map.get(t.getSeatType()) + 1);
            }
        }
        if (map.containsKey(type)) {
            return map.get(type) >= free;
        }
        return false;
    }

    @Override
    public List<EmployeeFlightVO> findAvailableFlights() {
        return availableFlightMapper.mapAsList(flightRepo.findAllAvailableFlights(), EmployeeFlightVO.class);
    }

}
