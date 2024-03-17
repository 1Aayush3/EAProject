package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteService;
import edu.miu.cs.cs544.domain.Event;

import edu.miu.cs.cs544.service.contract.EventPayLoad;
import edu.miu.cs.cs544.service.contract.MemberPayload;

public interface EventService extends BaseReadWriteService< Event,EventPayLoad, Integer> {
}
