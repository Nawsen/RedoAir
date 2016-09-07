package com.realdolmen.repository.beans;


import com.realdolmen.domain.Ticket;
import com.realdolmen.repository.TicketRepository;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */
@Stateless
public class TicketRepositoryBean extends AbstractBaseRepository<Ticket, Long> implements TicketRepository{

    @Override
    public void create(Ticket ticket) {
     insert(ticket);
    }
}
