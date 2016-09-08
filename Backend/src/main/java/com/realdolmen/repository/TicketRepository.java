package com.realdolmen.repository;

import com.realdolmen.domain.Ticket;

import java.util.List;

/**
 * Created by WVDAZ49 on 3/09/2016.
 */
public interface TicketRepository extends Repository<Ticket, Long> {

    void create(Ticket ticket);

}
