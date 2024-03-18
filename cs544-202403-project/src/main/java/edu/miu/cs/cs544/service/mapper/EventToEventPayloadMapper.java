package edu.miu.cs.cs544.service.mapper;

import edu.miu.common.service.mapper.BaseMapper;
import edu.miu.cs.cs544.domain.Event;
import edu.miu.cs.cs544.service.contract.EventPayload;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class EventToEventPayloadMapper extends BaseMapper< Event,EventPayload> {
    public EventToEventPayloadMapper(MapperFactory mapperFactory){
        super( mapperFactory, Event.class, EventPayload.class);
    }


}