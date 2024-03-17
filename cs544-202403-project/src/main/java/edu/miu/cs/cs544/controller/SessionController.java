package edu.miu.cs.cs544.controller;

import edu.miu.common.controller.BaseReadWriteController;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.domain.Session;
import edu.miu.cs.cs544.service.contract.MemberPayload;
import edu.miu.cs.cs544.service.contract.SessionPayLoad;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")

public class SessionController  extends BaseReadWriteController<SessionPayLoad, Session, Integer> {



}
