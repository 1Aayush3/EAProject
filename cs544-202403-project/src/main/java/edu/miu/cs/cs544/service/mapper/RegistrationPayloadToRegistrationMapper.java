package edu.miu.cs.cs544.service.mapper;

import edu.miu.common.service.mapper.BaseMapper;
import edu.miu.cs.cs544.domain.Registration;
import edu.miu.cs.cs544.service.contract.RegistrationPayload;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class RegistrationPayloadToRegistrationMapper extends BaseMapper<RegistrationPayload, Registration> {
    public RegistrationPayloadToRegistrationMapper(MapperFactory mapperFactory) {
        super(mapperFactory, RegistrationPayload.class, Registration.class);
    }
}