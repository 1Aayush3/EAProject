package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteServiceImpl;

import edu.miu.cs.cs544.domain.Attendance;

import edu.miu.cs.cs544.service.contract.AttendancePayload;
import org.springframework.stereotype.Service;

@Service
public class AttendanceServiceImpl extends BaseReadWriteServiceImpl<AttendancePayload, Attendance, Integer> implements AttendanceService{
}
