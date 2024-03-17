package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteService;
import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs.cs544.domain.Event;
import edu.miu.cs.cs544.domain.Session;
import edu.miu.cs.cs544.service.contract.EventPayLoad;
import edu.miu.cs.cs544.service.contract.SessionPayLoad;

public interface SessionService extends BaseReadWriteService<SessionPayLoad, Session, Integer> {
}
