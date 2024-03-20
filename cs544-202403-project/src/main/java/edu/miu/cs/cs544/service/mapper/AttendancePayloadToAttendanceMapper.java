package edu.miu.cs.cs544.service.mapper;

import edu.miu.common.service.mapper.BaseMapper;

import edu.miu.cs.cs544.domain.Attendance;

import edu.miu.cs.cs544.service.contract.AttendancePayload;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class AttendancePayloadToAttendanceMapper extends BaseMapper<AttendancePayload, Attendance> {
    public AttendancePayloadToAttendanceMapper(MapperFactory mapperFactory) {
        super(mapperFactory, AttendancePayload.class, Attendance.class);
    }
}
