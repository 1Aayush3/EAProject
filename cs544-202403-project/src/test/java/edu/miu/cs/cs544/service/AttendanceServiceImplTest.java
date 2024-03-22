package edu.miu.cs.cs544.service;

import edu.miu.common.service.mapper.BaseMapper;
import edu.miu.cs.cs544.domain.*;
import edu.miu.cs.cs544.repository.*;
import edu.miu.cs.cs544.service.contract.AttendancePayload;
import edu.miu.cs.cs544.service.contract.RecordDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AttendanceServiceImplTest {
    LocalDate currentDate = LocalDate.now();
    LocalTime currentTime = LocalTime.now();
    @MockBean
    private ScannerRepository scannerRepository;

    @MockBean
    private AttendanceRepository attendanceRepository;

    @MockBean
    private MemberRepository memberRepository;

    @MockBean
    private RegistrationRepository registrationRepository;

    @MockBean
    private SessionRepository sessionRepository;

    @Autowired
    private AttendanceService attendanceService;

    @TestConfiguration
    static class AttendanceServiceImplTestContextConfiguration {

        @Bean
        public AttendanceService attendanceService(ScannerRepository scannerRepository,
                                                   AttendanceRepository attendanceRepository,
                                                   MemberRepository memberRepository,
                                                   RegistrationRepository registrationRepository,
                                                   SessionRepository sessionRepository) {
            return new AttendanceServiceImpl(scannerRepository, attendanceRepository, memberRepository, registrationRepository, sessionRepository);
        }
    }

    @MockBean
    private BaseMapper<AttendancePayload, Attendance> attendancePayloadAttendanceBaseMapper;

    @MockBean
    private BaseMapper<Attendance, AttendancePayload> attendanceAttendancePayloadBaseMapper;

    @Test
    void getAllRecordsOfScanner() {
        Integer scannerCode = 123;
        Scanner scanner = new Scanner();
        scanner.setScannerCode(scannerCode);
        Event event = new Event();
        Session session = new Session();
        Attendance attendance = new Attendance();
        session.setAttendanceList(Collections.singletonList(attendance));
        attendance.setSession(session);
        event.setSessionList(Collections.singletonList(session));
        scanner.setEvent(event);


        when(scannerRepository.findById(scannerCode)).thenReturn(Optional.of(scanner));

        List<Attendance> actualList = attendanceService.getAllRecordsOfScanner(scannerCode);

        assertEquals(1, actualList.size());
        assertEquals(attendance, actualList.get(0));
    }

    @Test
    void getRecordOfScanner() {
        Integer scannerCode = 123;
        Integer recordId = 456;
        Attendance expectedAttendance = new Attendance();

        Scanner scanner = new Scanner();
        when(scannerRepository.findById(scannerCode)).thenReturn(Optional.of(scanner));
        when(attendanceRepository.findByAttendanceId(recordId)).thenReturn(Optional.of(expectedAttendance));

        Attendance actualAttendance = attendanceService.getRecordOfScanner(scannerCode, recordId);

        assertEquals(expectedAttendance, actualAttendance);
    }

    @Test
    void createRecord() {
        Integer barCode = 789;
        Integer scannerCode = 123;
        Member member = new Member();
        Scanner scanner = new Scanner();
        Event event = new Event();
        Session session = new Session();
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        // Setting up member, scanner, event, and session
        member.setBarCode(barCode);
        scanner.setScannerCode(scannerCode);
        scanner.setEvent(event);
        session.setDate(currentDate);
        session.setStartTime(currentTime.minusHours(1));
        session.setEndTime(currentTime.plusHours(1));

        // Set session list for the event
        event.setSessionList(Collections.singletonList(session));

        // Mocking repository behavior
        when(memberRepository.findByBarCode(barCode)).thenReturn(Optional.of(member));
        when(scannerRepository.findById(scannerCode)).thenReturn(Optional.of(scanner));
        when(registrationRepository.findByMemberAndEvent(member, event)).thenReturn(Optional.of(new Registration()));

        // Mock the behavior to directly return the session that fits the criteria
        when(sessionRepository.save(any(Session.class))).thenReturn(session);

        // Calling the method under test
        Attendance actualAttendance = attendanceService.createRecord(barCode, scannerCode);

        // Assertions
        assertEquals(currentDate, actualAttendance.getDate());
        assertTrue(currentTime.isBefore(actualAttendance.getTime().plusSeconds(1))); // Allow 1 second difference
        assertTrue(currentTime.isAfter(actualAttendance.getTime().minusSeconds(1))); // Allow 1 second difference
        assertEquals(member, actualAttendance.getMember());
        assertEquals(session, actualAttendance.getSession());
    }

    @Test
    void editRecord() {
        Integer scannerCode = 123;
        Integer recordId = 456;
        RecordDto recordDto = new RecordDto();
        recordDto.setMemberId(789);
        recordDto.setSessionId(101);

        Member member = new Member();
        Session session = new Session();
        Attendance attendance = new Attendance();
        attendance.setAttendanceId(recordId);

        when(scannerRepository.findById(scannerCode)).thenReturn(Optional.of(new Scanner()));
        when(attendanceRepository.findByAttendanceId(recordId)).thenReturn(Optional.of(attendance));
        when(memberRepository.findByMemberId(recordDto.getMemberId())).thenReturn(Optional.of(member));
        when(sessionRepository.findById(recordDto.getSessionId())).thenReturn(Optional.of(session));

        Attendance actualAttendance = attendanceService.editRecord(scannerCode, recordId, recordDto);

        // Assertions
        assertEquals(currentDate, actualAttendance.getDate());
        assertEquals(currentTime.truncatedTo(ChronoUnit.SECONDS), actualAttendance.getTime().truncatedTo(ChronoUnit.SECONDS));
        assertEquals(member, actualAttendance.getMember());
        assertEquals(session, actualAttendance.getSession());
    }

    @Test
    void deleteRecord() {
        Integer recordId = 456;
        Attendance attendance = new Attendance();
        attendance.setAttendanceId(recordId);

        when(attendanceRepository.findByAttendanceId(recordId)).thenReturn(Optional.of(attendance));

        String result = attendanceService.deleteRecord(123, recordId);

        assertEquals("Record Deleted Successfully!", result);
    }
}