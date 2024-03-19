package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs.cs544.domain.Registration;
import edu.miu.cs.cs544.service.contract.RegistrationPayload;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl extends BaseReadWriteServiceImpl<RegistrationPayload, Registration, Integer> implements RegistrationService {
}
