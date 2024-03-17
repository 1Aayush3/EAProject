package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs.cs544.domain.Session;
import edu.miu.cs.cs544.service.contract.SessionPayload;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl extends BaseReadWriteServiceImpl<SessionPayload, Session, Integer> implements SessionService {
}
