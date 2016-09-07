package com.realdolmen.repository.beans;


import com.realdolmen.domain.Airport;
import com.realdolmen.repository.AirportRepository;
import com.realdolmen.repository.Repository;
import com.realdolmen.service.AirportService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */
@Stateless
public class AirportRepositoryBean extends AbstractBaseRepository<Airport, Long> implements AirportRepository{

    @Override
    public List<Airport> getAllAirports(){
        return findAll();
    }

    @Override
    public List<Airport> getFilteredAirports(String filter){
        if (filter == null){
            throw new IllegalArgumentException("Filter may NOT be null!");
        }
        return getEntityManager().createNamedQuery("getFilteredAirports", Airport.class)
                .setParameter("filter", "%" + filter + "%")
                .getResultList();
    }
}
