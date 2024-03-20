package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteServiceImpl;

import edu.miu.cs.cs544.domain.Attendance;

import edu.miu.cs.cs544.domain.Event;
import edu.miu.cs.cs544.domain.Scanner;
import edu.miu.cs.cs544.repository.ScannerRepository;
import edu.miu.cs.cs544.service.contract.AttendancePayload;
import edu.miu.cs.cs544.service.mapper.AttendanceToAttendancePayloadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl extends BaseReadWriteServiceImpl<AttendancePayload, Attendance, Integer> implements AttendanceService{

    @Autowired
    ScannerRepository scannerRepository;

    @Autowired
    AttendanceToAttendancePayloadMapper attendanceToAttendancePayloadMapper;

    @Override
    public List<AttendancePayload> getAllSessionsForEvent(Integer scannerCode) {
        Optional<Scanner> scannerOptional = scannerRepository.findById(scannerCode);

        if (scannerOptional.isPresent()) {
            Scanner scanner = scannerOptional.get();
            Event event = scanner.getEvent();

            if (event != null) {

                return event.getSessionList().stream()
                        .flatMap(session -> session.getAttendanceList().stream().map(attendance -> attendanceToAttendancePayloadMapper.map(attendance)))
                        .collect(Collectors.toList());
            } else {
                return Collections.emptyList();
            }
        } else {
            return Collections.emptyList();
        }
    }
}
