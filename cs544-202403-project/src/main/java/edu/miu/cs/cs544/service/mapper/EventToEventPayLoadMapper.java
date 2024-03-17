package edu.miu.cs.cs544.service.mapper;

import edu.miu.common.service.mapper.BaseMapper;
import edu.miu.cs.cs544.domain.Event;
import edu.miu.cs.cs544.service.contract.EventPayLoad;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class EventToEventPayLoadMapper extends BaseMapper< Event,EventPayLoad> {
    public EventToEventPayLoadMapper(MapperFactory mapperFactory){
        super( mapperFactory, Event.class, EventPayLoad.class);
    }
    public EventPayLoad mapToDTO(Event event) {
        EventPayLoad eventPayload = new EventPayLoad();
        eventPayload.setName(event.getName());
        eventPayload.setDescription(event.getDescription());
        eventPayload.setEndDate(event.getEndDate());
        eventPayload.setEndTime(event.getEndTime());
        eventPayload.setLocation(event.getLocation());
        eventPayload.setStartDate(event.getStartDate());
        eventPayload.setStartTime(event.getStartTime());
        return eventPayload;
    }

}
