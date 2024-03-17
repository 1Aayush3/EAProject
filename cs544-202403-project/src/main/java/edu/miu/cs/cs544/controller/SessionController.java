package edu.miu.cs.cs544.controller;

import edu.miu.common.controller.BaseReadWriteController;
import edu.miu.cs.cs544.domain.Session;
import edu.miu.cs.cs544.service.contract.SessionPayload;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sessions")
public class SessionController extends BaseReadWriteController<SessionPayload, Session, Integer> {
}
