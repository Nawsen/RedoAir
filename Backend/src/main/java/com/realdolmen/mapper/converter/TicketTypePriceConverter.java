package com.realdolmen.mapper.converter;

import com.realdolmen.VO.AvailableSeatsVO;
import com.realdolmen.VO.TicketTypePriceVO;
import com.realdolmen.domain.SeatType;
import com.realdolmen.domain.Ticket;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WVDAZ49 on 8/09/2016.
 */
public class TicketTypePriceConverter extends BidirectionalConverter<List<Ticket>, List<TicketTypePriceVO>> {
    @Override
    public List<TicketTypePriceVO> convertTo(List<Ticket> tickets, Type<List<TicketTypePriceVO>> type) {
        List<TicketTypePriceVO> list = new ArrayList<>();
        Map<SeatType, Integer> map = new HashMap<>();
        Map<SeatType, Double> mapPrice = new HashMap<>();
        Map<SeatType, Double> mapOverridePrice = new HashMap<>();
        for(Ticket t:tickets){
            mapPrice.putIfAbsent(t.getSeatType(), t.getBasePrice().doubleValue());
            if (t.getOverRidePrice() != null){
                mapOverridePrice.putIfAbsent(t.getSeatType(), t.getOverRidePrice().doubleValue());
            }
            if (map.get(t.getSeatType()) != null){
                map.put(t.getSeatType(),map.get(t.getSeatType()) + 1);
            } else {
                map.put(t.getSeatType(), 1);
            }

        }
        for(SeatType seat: map.keySet()){
            TicketTypePriceVO vo = new TicketTypePriceVO();
            vo.setType(seat);
            vo.setSeats(map.get(seat));
            vo.setAmount(mapPrice.get(seat));
            vo.setOverrideAmount(mapOverridePrice.get(seat));
            list.add(vo);
        }
        return list;
    }

    @Override
    public List<Ticket> convertFrom(List<TicketTypePriceVO> ticketTypePriceVOs, Type<List<Ticket>> type) {
        return null;
    }
}
