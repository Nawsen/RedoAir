package com.realdolmen.mapper.converter;

import com.realdolmen.VO.AvailableSeatsVO;
import com.realdolmen.domain.SeatType;
import com.realdolmen.domain.Ticket;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

import java.util.*;

/**
 * Created by WVDAZ49 on 6/09/2016.
 */
public class AvailableSeatConverter extends BidirectionalConverter<List<Ticket>, List<AvailableSeatsVO>> {

    @Override
    public List<AvailableSeatsVO> convertTo(List<Ticket> tickets, Type<List<AvailableSeatsVO>> type) {
        List<AvailableSeatsVO> list = new ArrayList<>();
        Map<SeatType, Integer> map = new HashMap<>();
        map.put(SeatType.FIRST_CLASS, 0);
        map.put(SeatType.BUSINESS, 0);
        map.put(SeatType.ECONOMY, 0);
        for(Ticket t:tickets){
            if (map.get(t.getSeatType()) != null && !t.getSold()){
                map.put(t.getSeatType(),map.get(t.getSeatType()) + 1);
            } else {
                map.put(t.getSeatType(), 1);
            }

        }
        for(SeatType seat: map.keySet()){
            AvailableSeatsVO vo = new AvailableSeatsVO();
            vo.setType(seat);
            vo.setAmount(map.get(seat));
            list.add(vo);
        }
        return list;
    }

    @Override
    public List<Ticket> convertFrom(List<AvailableSeatsVO> availableSeatsVOs, Type<List<Ticket>> type) {
        return null;
    }
}