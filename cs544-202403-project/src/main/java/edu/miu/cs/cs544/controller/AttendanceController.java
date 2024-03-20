package edu.miu.cs.cs544.controller;

import edu.miu.common.controller.BaseReadWriteController;


import edu.miu.cs.cs544.domain.Attendance;
import edu.miu.cs.cs544.service.contract.AttendancePayload;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

 @RestController
@RequestMapping("/attendances")
public class AttendanceController extends BaseReadWriteController<AttendancePayload, Attendance, Integer> {
}
