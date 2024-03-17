package edu.miu.cs.cs544.service.mapper;

import edu.miu.common.service.mapper.BaseMapper;
import edu.miu.cs.cs544.domain.Session;
import edu.miu.cs.cs544.service.contract.SessionPayload;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class SessionPayloadToSessionMapper extends BaseMapper<SessionPayload, Session> {
    public SessionPayloadToSessionMapper(MapperFactory mapperFactory) {
        super(mapperFactory, SessionPayload.class, Session.class);
    }
}
