package edu.miu.cs.cs544.service.mapper;

import edu.miu.common.service.mapper.BaseMapper;
import edu.miu.cs.cs544.domain.Session;
import edu.miu.cs.cs544.service.contract.SessionPayload;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class SessionToSessionPayloadMapper extends BaseMapper<Session, SessionPayload> {
    public SessionToSessionPayloadMapper(MapperFactory mapperFactory) {
        super(mapperFactory, Session.class, SessionPayload.class);
    }
}
