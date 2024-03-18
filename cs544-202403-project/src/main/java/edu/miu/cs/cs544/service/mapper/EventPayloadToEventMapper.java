package edu.miu.cs.cs544.service.mapper;

import edu.miu.common.service.mapper.BaseMapper;
import edu.miu.cs.cs544.domain.Event;


import edu.miu.cs.cs544.service.contract.EventPayload;

import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;


@Component
public class EventPayloadToEventMapper extends BaseMapper<EventPayload, Event> {


    public EventPayloadToEventMapper(MapperFactory mapperFactory) {
        super(mapperFactory, EventPayload.class, Event.class);
    }




}