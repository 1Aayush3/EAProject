package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteServiceImpl;

import edu.miu.cs.cs544.domain.Attendance;

import edu.miu.cs.cs544.domain.Event;
import edu.miu.cs.cs544.domain.Scanner;
import edu.miu.cs.cs544.repository.ScannerRepository;
import edu.miu.cs.cs544.service.contract.AttendancePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl extends BaseReadWriteServiceImpl<AttendancePayload, Attendance, Integer> implements AttendanceService{

    @Autowired
    ScannerRepository scannerRepository;

    @Override
    public List<AttendancePayload> getAllSessionsForEvent(Integer scannerCode) {
        Optional<Scanner> scannerOptional = scannerRepository.findById(scannerCode);

        if (scannerOptional.isPresent()) {
            Scanner scanner = scannerOptional.get();
            Event event = scanner.getEvent();

            if (event != null) {
                List<Attendance> attendanceList = event.getSessionList().stream()
                        .flatMap(session -> session.getAttendanceList().stream())
                        .collect(Collectors.toList());

                return ResponseEntity.ok(attendanceList);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
        return null;
    }
}
