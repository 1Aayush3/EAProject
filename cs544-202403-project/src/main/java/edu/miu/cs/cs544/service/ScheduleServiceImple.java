package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs.cs544.domain.Schedule;
import edu.miu.cs.cs544.service.contract.SchedulePayLoad;

import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImple extends BaseReadWriteServiceImpl<SchedulePayLoad, Schedule, Integer> implements ScheduleService {
}
