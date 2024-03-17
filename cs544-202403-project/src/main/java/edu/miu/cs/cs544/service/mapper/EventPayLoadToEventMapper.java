package edu.miu.cs.cs544.service.mapper;

import edu.miu.common.service.mapper.BaseMapper;
import edu.miu.cs.cs544.domain.Event;


import edu.miu.cs.cs544.service.contract.EventPayLoad;

import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;


@Component
public class EventPayLoadToEventMapper extends BaseMapper<EventPayLoad, Event> {


    public EventPayLoadToEventMapper(MapperFactory mapperFactory) {
        super(mapperFactory, EventPayLoad.class, Event.class);
    }

    public Event mapToEntity(EventPayLoad eventPayload) {
        Event event = new Event();
        // Map properties from eventPayload to event
        event.setName(eventPayload.getName());
        event.setDescription(eventPayload.getDescription());
        event.setEndDate(eventPayload.getEndDate());
        event.setEndTime(eventPayload.getEndTime());
        event.setLocation(eventPayload.getLocation());
        event.setStartDate(eventPayload.getStartDate());
        event.setStartTime(eventPayload.getStartTime());

        return event;
    }



}
