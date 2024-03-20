package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteService;

import edu.miu.cs.cs544.domain.Attendance;

import edu.miu.cs.cs544.service.contract.AttendancePayload;

public interface AttendanceService extends BaseReadWriteService<AttendancePayload, Attendance, Integer> {
}
