package edu.miu.cs.cs544.service.mapper;


import edu.miu.common.service.mapper.BaseMapper;
import edu.miu.cs.cs544.domain.Event;
import edu.miu.cs.cs544.domain.Session;

import edu.miu.cs.cs544.service.contract.EventPayLoad;
import edu.miu.cs.cs544.service.contract.SessionPayLoad;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class SessionPayLoadToSessionMapper extends BaseMapper<SessionPayLoad, Session> {


    public SessionPayLoadToSessionMapper(MapperFactory mapperFactory) {
        super(mapperFactory, SessionPayLoad.class, Session.class);
    }
}
