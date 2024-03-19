package edu.miu.cs.cs544.service.mapper;

import edu.miu.common.service.mapper.BaseMapper;
import edu.miu.cs.cs544.domain.Registration;
import edu.miu.cs.cs544.service.contract.RegistrationPayload;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class RegistrationToRegistrationPayloadMapper extends BaseMapper<Registration, RegistrationPayload> {
    public RegistrationToRegistrationPayloadMapper(MapperFactory mapperFactory) {
        super(mapperFactory, Registration.class, RegistrationPayload.class);
    }
}
