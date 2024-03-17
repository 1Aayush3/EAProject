package edu.miu.cs.cs544.controller;

import edu.miu.common.controller.BaseReadWriteController;

import edu.miu.cs.cs544.domain.Schedule;

import edu.miu.cs.cs544.service.contract.SchedulePayLoad;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedule")
public class ScheduleController extends BaseReadWriteController<SchedulePayLoad, Schedule, Integer> {

}
