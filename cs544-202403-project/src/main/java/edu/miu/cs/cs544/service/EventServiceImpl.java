package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs.cs544.domain.Event;


import edu.miu.cs.cs544.service.EventService;
import edu.miu.cs.cs544.service.contract.EventPayload;
import org.springframework.stereotype.Service;

@Service

public class EventServiceImpl extends BaseReadWriteServiceImpl<EventPayload,  Event,Integer> implements EventService {
}
