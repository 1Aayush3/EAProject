package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteService;

import edu.miu.cs.cs544.domain.Attendance;

import edu.miu.cs.cs544.service.contract.AttendancePayload;

import java.util.List;

public interface AttendanceService extends BaseReadWriteService<AttendancePayload, Attendance, Integer> {
    List<AttendancePayload> getAllSessionsForEvent(Integer scannerCode);
}
