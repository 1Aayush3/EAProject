package edu.miu.cs.cs544.controller;

import edu.miu.common.controller.BaseReadWriteController;
import edu.miu.cs.cs544.domain.Registration;
import edu.miu.cs.cs544.service.contract.RegistrationPayload;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registrations")
public class RegistrationController extends BaseReadWriteController<RegistrationPayload, Registration, Integer> {
}
