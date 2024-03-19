package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteService;
import edu.miu.cs.cs544.domain.Registration;
import edu.miu.cs.cs544.service.contract.RegistrationPayload;

public interface RegistrationService extends BaseReadWriteService<RegistrationPayload, Registration, Integer> {
}