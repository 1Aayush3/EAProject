package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteService;
;
import edu.miu.cs.cs544.domain.Schedule;

import edu.miu.cs.cs544.service.contract.SchedulePayLoad;

public interface ScheduleService extends BaseReadWriteService<SchedulePayLoad, Schedule, Integer> {
}
