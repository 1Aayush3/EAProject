package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs.cs544.domain.Event;


import edu.miu.cs.cs544.service.EventService;
import edu.miu.cs.cs544.service.contract.EventPayLoad;
import org.springframework.stereotype.Service;

@Service

public class EventServiceImple extends BaseReadWriteServiceImpl< Event,EventPayLoad, Integer> implements EventService {
}
