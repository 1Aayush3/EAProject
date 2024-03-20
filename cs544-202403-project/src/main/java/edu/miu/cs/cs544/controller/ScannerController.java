package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.domain.*;
import edu.miu.cs.cs544.repository.*;
import edu.miu.cs.cs544.service.MemberService;
import edu.miu.cs.cs544.service.contract.AttendanceDTO;
import edu.miu.cs.cs544.service.contract.AttendancePayload;
import edu.miu.cs.cs544.service.contract.RecordDto;
import edu.miu.cs.cs544.service.mapper.AttendancePayloadToAttendanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.miu.common.controller.BaseReadWriteController;
import edu.miu.cs.cs544.service.contract.ScannerPayload;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/scanners")
public class ScannerController extends BaseReadWriteController<ScannerPayload, Scanner, Integer> {

    @Autowired
    ScannerRepository scannerRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    RegistrationRepository registrationRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    AttendancePayloadToAttendanceMapper attendancePayloadToAttendanceMapper;

    @GetMapping("/{scannerCode}/records")
    public ResponseEntity<?> getScannerRecords(@PathVariable Integer scannerCode) {

        Optional<Scanner> scannerOptional = scannerRepository.findById(scannerCode);

        Scanner scanner = scannerOptional.orElse(null);
        if (scanner != null) {
            Event event = scanner.getEvent();
            List<Session> sessionList = event.getSessionList();

            // Using streams to retrieve attendance from sessions
            List<Attendance> attendanceList = sessionList.stream()
                    .flatMap(session -> session.getAttendanceList().stream())
                    .collect(Collectors.toList());

            return new ResponseEntity<>(attendanceList, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("No Records", HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/{scannerCode}/records")
    public ResponseEntity<String> createScannerRecord(@PathVariable Integer scannerCode, @RequestBody AttendanceDTO attendanceDTO) {
        Integer barcode = attendanceDTO.getBarCode();

        // Find member by barcode
        Optional<Member> memberOptional = memberRepository.findByBarCode(barcode);
        if (!memberOptional.isPresent()) {
            return new ResponseEntity<>("Member not found", HttpStatus.NOT_FOUND);
        }
        Member member = memberOptional.get();

        // Find scanner by code
        Optional<Scanner> scannerOptional = scannerRepository.findById(scannerCode);
        if (!scannerOptional.isPresent()) {
            return new ResponseEntity<>("Scanner not found", HttpStatus.NOT_FOUND);
        }
        Scanner scanner = scannerOptional.get();

        Event event = scanner.getEvent();
        if(event == null){
            return new ResponseEntity<>("Event not found", HttpStatus.NOT_FOUND);
        }

        // Check if the member is registered for the event
        Optional<Registration> registrationOptional = registrationRepository.findByMemberAndEvent(member, event);
        if (!registrationOptional.isPresent()) {
            return new ResponseEntity<>("Member is not registered for the event", HttpStatus.BAD_REQUEST);
        }

        // Get current time
        LocalTime currentTime = LocalTime.now();
        LocalDate currentDate = LocalDate.now();

        // Find session with time between now and end time
        Optional<Session> sessionOptional = event.getSessionList().stream()
                .filter(session -> session.getDate().isEqual(currentDate) &&
                        session.getStartTime().isBefore(currentTime) &&
                        session.getEndTime().isAfter(currentTime))
                .findFirst();

        if (!sessionOptional.isPresent()) {
            return new ResponseEntity<>("No active session found", HttpStatus.NOT_FOUND);
        }
        Session session = sessionOptional.get();

        // Create attendance for the member
        Attendance attendance = new Attendance();
        attendance.setDate(currentDate);
        attendance.setTime(currentTime);
        attendance.setMember(member);
        attendance.setSession(session);

        attendanceRepository.save(attendance);

        return new ResponseEntity<>("Attendance added successfully", HttpStatus.OK);
    }

    @PutMapping("/{scannerCode}/records/{recordId}")
    public ResponseEntity<?> updateScannerRecord(@PathVariable Integer scannerCode,
                                      @PathVariable Integer recordId,
                                      @RequestBody RecordDto recordDto ) {
        // Find the scanner
        Optional<Scanner> scannerOptional = scannerRepository.findById(scannerCode);
        if (!scannerOptional.isPresent()) {
            return new ResponseEntity<>("Scanner not found", HttpStatus.NOT_FOUND);
        }
        Scanner scanner = scannerOptional.get();

        // Retrieve the event associated with the scanner
        Event event = scanner.getEvent();
        if (event == null) {
            return new ResponseEntity<>("Event not found for the scanner", HttpStatus.NOT_FOUND);
        }

        // Get the list of attendance records associated with the event
        List<Attendance> attendanceList = event.getSessionList().stream()
                .flatMap(session -> session.getAttendanceList().stream())
                .collect(Collectors.toList());

        // Check if the recordId belongs to the attendanceList
        boolean recordIdFound = attendanceList.stream()
                .anyMatch(attendance -> attendance.getAttendanceId().equals(recordId));

        if (!recordIdFound){
            return new ResponseEntity<>("Attendance record not found", HttpStatus.OK);
        }

        Optional<Attendance> attendanceOptional = attendanceRepository.findByAttendanceId(recordId);
        Attendance attendance = attendanceOptional.get();

        Optional<Member> memberOptional = memberRepository.findByMemberId(recordDto.getMemberId());

        Optional<Session> session = sessionRepository.findById(recordDto.getSessionId());

        attendance.setMember(memberOptional.get());
        attendance.setSession(session.get());
        attendance.setDate(recordDto.getDate());
        attendance.setTime(recordDto.getTime());

        attendanceRepository.save(attendance);

        return new ResponseEntity<>("Attendance record updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{scannerCode}/records/{recordId}")
    public ResponseEntity<String> deleteScannerRecord(@PathVariable Integer scannerCode, @PathVariable Integer recordId) {
        // Check if the scanner exists
        Optional<Scanner> scannerOptional = scannerRepository.findById(scannerCode);
        if (!scannerOptional.isPresent()) {
            return new ResponseEntity<>("Scanner not found", HttpStatus.NOT_FOUND);
        }

        // Retrieve the attendance record from the repository
        Optional<Attendance> attendanceOptional = attendanceRepository.findByAttendanceId(recordId);
        if (!attendanceOptional.isPresent()) {
            return new ResponseEntity<>("Attendance record not found", HttpStatus.NOT_FOUND);
        }

        // Delete the attendance record
        attendanceRepository.delete(attendanceOptional.get());

        return new ResponseEntity<>("Attendance record deleted successfully", HttpStatus.OK);
    }


}
