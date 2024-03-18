package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteService;
import edu.miu.cs.cs544.domain.Event;

import edu.miu.cs.cs544.service.contract.EventPayload;


public interface EventService extends BaseReadWriteService< EventPayload, Event,Integer> {
}
