package edu.miu.cs.cs544.service.mapper;

import edu.miu.common.service.mapper.BaseMapper;
import edu.miu.cs.cs544.domain.Session;
import edu.miu.cs.cs544.service.contract.SessionPayLoad;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class SessionToSessionPayLoadMapper extends BaseMapper<Session, SessionPayLoad> {
    public SessionToSessionPayLoadMapper(MapperFactory mapperFactory) {
        super( mapperFactory,Session.class, SessionPayLoad.class);
    }
}
