package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.domain.Session;
import edu.miu.cs.cs544.service.contract.MemberPayload;
import edu.miu.cs.cs544.service.contract.SessionPayLoad;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImple extends BaseReadWriteServiceImpl<SessionPayLoad, Session, Integer> implements SessionService {

}
