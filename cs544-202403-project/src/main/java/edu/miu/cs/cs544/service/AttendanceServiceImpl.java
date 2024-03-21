package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteServiceImpl;

import edu.miu.cs.cs544.domain.*;

import edu.miu.cs.cs544.repository.*;
import edu.miu.cs.cs544.service.contract.AttendancePayload;
import edu.miu.cs.cs544.service.contract.RecordDto;
import edu.miu.cs.cs544.service.mapper.AttendanceToAttendancePayloadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl extends BaseReadWriteServiceImpl<AttendancePayload, Attendance, Integer> implements AttendanceService{

    @Autowired
    ScannerRepository scannerRepository;

    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    RegistrationRepository registrationRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Override
    public List<Attendance> getAllRecordsOfScanner(Integer scannerCode) {
        Optional<Scanner> scannerOptional = scannerRepository.findById(scannerCode);

        if (scannerOptional.isPresent()) {
            Scanner scanner = scannerOptional.get();
            Event event = scanner.getEvent();

            if (event != null) {
                return event.getSessionList().stream()
                        .flatMap(session -> session.getAttendanceList().stream())
                        .collect(Collectors.toList());
            } else {
                return Collections.emptyList();
            }
        } else {
            return Collections.emptyList();
        }
    }

    public Attendance getRecordOfScanner(Integer scannerCode, Integer recordId){
        Optional<Scanner> scannerOptional = scannerRepository.findById(scannerCode);
        if (scannerOptional.isEmpty()) {
            return null;
        }

        // Retrieve the attendance record
        Optional<Attendance> attendanceOptional = attendanceRepository.findByAttendanceId(recordId);
        return attendanceOptional.orElse(null);
    }

    @Override
    public Attendance createRecord(Integer barCode, Integer scannerCode) {

        // Find member by barcode
        Optional<Member> memberOptional = memberRepository.findByBarCode(barCode);
        if (memberOptional.isEmpty()) {
            System.out.println("no Member");
            return null;
        }
        Member member = memberOptional.get();

        // Find scanner by code
        Optional<Scanner> scannerOptional = scannerRepository.findById(scannerCode);
        if (scannerOptional.isEmpty()) {
            System.out.println("no Scanner");
            return null;
        }
        Scanner scanner = scannerOptional.get();

        Event event = scanner.getEvent();
        if (event == null) {
            System.out.println("no event");
            return null;
        }

        // Check if the member is registered for the event
        if (!registrationRepository.findByMemberAndEvent(member, event).isPresent()) {
            System.out.println("Not registered");
            return null;
        }

        // Get current time and date
        LocalTime currentTime = LocalTime.now();
        LocalDate currentDate = LocalDate.now();
        System.out.println(currentTime);

        // Find session with time between now and end time
        Optional<Session> sessionOptional = event.getSessionList().stream()
                .filter(session -> session.getDate().isEqual(currentDate) &&
                        session.getStartTime().isBefore(currentTime) &&
                        session.getEndTime().isAfter(currentTime))
                .findFirst();

        if (sessionOptional.isEmpty()) {
            System.out.println("Session not found");
            return null;
        }
        Session session = sessionOptional.get();

        // Create attendance for the member
        Attendance attendance = new Attendance();
        attendance.setDate(currentDate);
        attendance.setTime(currentTime);
        attendance.setMember(member);
        attendance.setSession(session);

        attendanceRepository.save(attendance);

        return attendance;
    }

    @Override
    public Attendance editRecord(Integer scannerCode, Integer recordId, RecordDto recordDto) {

        // Find the scanner
        Optional<Scanner> scannerOptional = scannerRepository.findById(scannerCode);
        if (!scannerOptional.isPresent()) {
            return null;
        }
        Scanner scanner = scannerOptional.get();

        // Retrieve the event associated with the scanner
        Event event = scanner.getEvent();
        if (event == null) {
            return null;
        }

        // Get the list of attendance records associated with the event
        List<Attendance> attendanceList = event.getSessionList().stream()
                .flatMap(session -> session.getAttendanceList().stream())
                .toList();

        // Check if the recordId belongs to the attendanceList
        boolean recordIdFound = attendanceList.stream()
                .anyMatch(attendance -> attendance.getAttendanceId().equals(recordId));

        if (!recordIdFound) {
            return null;
        }

        Optional<Attendance> attendanceOptional = attendanceRepository.findByAttendanceId(recordId);
        Attendance attendance = attendanceOptional.get();

        Optional<Member> memberOptional = memberRepository.findByMemberId(recordDto.getMemberId());
        Optional<Session> sessionOptional = sessionRepository.findById(recordDto.getSessionId());

        attendance.setMember(memberOptional.get());
        attendance.setSession(sessionOptional.get());
        attendance.setDate(recordDto.getDate());
        attendance.setTime(recordDto.getTime());

        attendanceRepository.save(attendance);
        return attendance;
    }

    @Override
    public String deleteRecord(Integer scannerCode, Integer recordId) {
        // Check if the attendance record exists
        Optional<Attendance> attendanceOptional = attendanceRepository.findByAttendanceId(recordId);
        if (attendanceOptional.isEmpty()) {
            return null;
        }

        // Delete the attendance record
        attendanceRepository.delete(attendanceOptional.get());
        return "Record Deleted Successfully!";
    }

}
