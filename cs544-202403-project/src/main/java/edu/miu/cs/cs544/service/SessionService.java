package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteService;
import edu.miu.cs.cs544.domain.Session;
import edu.miu.cs.cs544.service.contract.SessionPayload;

public interface SessionService extends BaseReadWriteService<SessionPayload, Session, Integer> {
}
