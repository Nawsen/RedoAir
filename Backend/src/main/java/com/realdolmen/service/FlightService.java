package com.realdolmen.service;


import com.realdolmen.VO.CustomerFlightVO;
import com.realdolmen.VO.EmployeeFlightVO;
import com.realdolmen.domain.Flight;
import com.realdolmen.domain.SeatType;

import java.util.Date;
import java.util.List;

/**
 * Created by WVDAZ49 on 2/09/2016.
 */
public interface FlightService {
    List<CustomerFlightVO> findFlights(String arrivalId, String departureId, Date startDate, Date endDate, SeatType type, Integer free);

    List<CustomerFlightVO> findFlights(String departureCode, String arrivalCode);
    boolean checkForFreeTickets(Flight flight, SeatType type, Integer free);
    List<EmployeeFlightVO> findAvailableFlights();
    void setFlightTicketOverridePrices(EmployeeFlightVO flight);
}
