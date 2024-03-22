package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Attendance;
import edu.miu.cs.cs544.domain.Event;
import edu.miu.cs.cs544.domain.Session;
import edu.miu.cs.cs544.repository.EventRepository;
import edu.miu.cs.cs544.service.contract.SessionPayload;
import edu.miu.cs.cs544.service.mapper.EventPayloadToEventMapper;
import edu.miu.cs.cs544.service.mapper.EventToEventPayloadMapper;
import edu.miu.cs.cs544.service.mapper.SessionCustomMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventServiceImplTest {

    @MockBean
    private EventRepository eventRepository;

    @Mock
    private EventPayloadToEventMapper eventPayloadToEventMapper;

    @Mock
    private EventToEventPayloadMapper eventToEventPayloadMapper;

    @InjectMocks
    private EventServiceImpl eventService;

    @BeforeEach

    void setUp() {
        MockitoAnnotations.openMocks(this);
        eventService = new EventServiceImpl(eventRepository, eventPayloadToEventMapper, eventToEventPayloadMapper);
    }

    @Test
    void testGetAllSessionsForEvent_WithValidEventId() {

        Integer eventId = 1;
        List<Session> sessions = new ArrayList<>();
        sessions.add(new Session());
        Event event = new Event();
        event.setSessionList(sessions);
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));


        List<SessionPayload> sessionPayloadList = eventService.getAllSessionsForEvent(eventId);


        assertNotNull(sessionPayloadList);
        assertEquals(1, sessionPayloadList.size());
    }

    @Test
    void testGetSessionForEvent_WithValidEventIdAndSessionId() {
        Integer eventId = 1;
        Integer sessionId = 1;
        Event event = new Event();
        event.setSessionList(new ArrayList<>());
        Session session = new Session();
        session.setId(sessionId);
        event.getSessionList().add(session);
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));


        SessionPayload sessionPayload = eventService.getSessionForEvent(eventId, sessionId);


        assertNotNull(sessionPayload);
        assertEquals(sessionId, sessionPayload.getId());
    }

    @Test
    void testSaveSessionForEvent_WithValidEventId() {

        Integer eventId = 1;
        SessionPayload sessionPayload = new SessionPayload();
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(new Event()));


        SessionPayload savedSessionPayload = eventService.saveSessionForEvent(eventId, sessionPayload);


        assertNotNull(savedSessionPayload);
    }

    @Test
    void testUpdateSession_WithValidEventIdAndSessionPayload() {

        Integer eventId = 1;
        Integer sessionId = 1; // Assuming the session ID is 1
        SessionPayload sessionPayload = new SessionPayload();
        sessionPayload.setId(sessionId); // Set the session ID
        Event event = new Event();
        Session session = new Session();
        session.setId(sessionId); // Set the session ID
        event.setSessionList(Collections.singletonList(session)); // Initialize the session list
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));


        SessionPayload updatedSessionPayload = eventService.updateSession(eventId, sessionPayload);


        assertNotNull(updatedSessionPayload);
    }

    @Test
    void testDeleteSessionFromEvent_WithValidEventIdAndSessionId() {

        Integer eventId = 1;
        Integer sessionId = 1;
        Session session = new Session();
        session.setId(sessionId);
        Event event = new Event();
        event.setSessionList(new ArrayList<>());
        event.getSessionList().add(session);
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        doNothing().when(eventRepository).deleteSession(sessionId);


        String result = eventService.deleteSessionFromEvent(eventId, sessionId);


        assertEquals("Deleted", result);
    }
    @Test
    void testGetAllSessionsForEvent_WithInvalidEventId() {

        Integer eventId = 1;
        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());


        List<SessionPayload> sessionPayloadList = eventService.getAllSessionsForEvent(eventId);


        assertNotNull(sessionPayloadList);
        assertTrue(sessionPayloadList.isEmpty());
    }
    @Test
    void testCalculateAttendanceForEvent() {

        Event event = new Event();
        Session session1 = new Session();
        Session session2 = new Session();
        Session session3 = new Session();


        Attendance attendance1 = new Attendance();
        attendance1.setDate(LocalDate.now());
        attendance1.setTime(LocalTime.now());
        Attendance attendance2 = new Attendance();
        attendance2.setDate(LocalDate.now());
        attendance2.setTime(LocalTime.now());
        Attendance attendance3 = new Attendance();
        attendance3.setDate(LocalDate.now());
        attendance3.setTime(LocalTime.now());

        session1.setAttendanceList(Arrays.asList(attendance1, attendance2));
        session2.setAttendanceList(Collections.singletonList(attendance3));
        session3.setAttendanceList(Collections.emptyList());

        event.setSessionList(Arrays.asList(session1, session2, session3));


        when(eventRepository.findById(anyInt())).thenReturn(Optional.of(event));


        int eventId = 123; // example event ID
        int expectedAttendance = 3; // expected total attendance


        Integer actualAttendance = eventService.calculateAttendanceForEvent(eventId);


        assertEquals(expectedAttendance, actualAttendance);
    }

    @Test
    void testGetAllSession(){
        Event event = new Event();
        event.setId(1);
        event.setStartDate(LocalDate.now());
        event.setEndDate(LocalDate.now());
        event.setName("Event name");
        event.setDescription("Event desc");
        event.setStartTime(LocalTime.now());
        event.setEndTime(LocalTime.now());
        event.setSessionList(new ArrayList<>(Arrays.asList(
                new Session(1,LocalDate.now(),LocalTime.now(),LocalTime.now(),new ArrayList<>()),
                new Session(2,LocalDate.now(),LocalTime.now(),LocalTime.now(),new ArrayList<>()),
                new Session(3,LocalDate.now(),LocalTime.now(),LocalTime.now(),new ArrayList<>())

        )));
        when(eventRepository.findById(1)).thenReturn(Optional.of(event));
        List<SessionPayload> sessionPayloads = this.eventService.getAllSessionsForEvent(1);
        assertEquals(3,sessionPayloads.size());

    }

    @Test
    void testGetSession(){
        Event event = new Event();
        event.setId(1);
        event.setStartDate(LocalDate.now());
        event.setEndDate(LocalDate.now());
        event.setName("Event name");
        event.setDescription("Event desc");
        event.setStartTime(LocalTime.now());
        event.setEndTime(LocalTime.now());
        event.setSessionList(new ArrayList<>(Arrays.asList(
                new Session(1,LocalDate.now(),LocalTime.now(),LocalTime.now(),new ArrayList<>()),
                new Session(2,LocalDate.now(),LocalTime.now(),LocalTime.now(),new ArrayList<>()),
                new Session(3,LocalDate.now(),LocalTime.now(),LocalTime.now(),new ArrayList<>())

        )));
        when(eventRepository.findById(1)).thenReturn(Optional.of(event));
        SessionPayload sessionPayload = this.eventService.getSessionForEvent(1,1);
        assertEquals(sessionPayload.getId(),1);

    }
//    @Test
//    void testSaveSession(){
//        Event event = new Event();
//        event.setId(1);
//        event.setStartDate(LocalDate.now());
//        event.setEndDate(LocalDate.now());
//        event.setName("Event name");
//        event.setDescription("Event desc");
//        event.setStartTime(LocalTime.now());
//        event.setEndTime(LocalTime.now());
//        event.setSessionList(new ArrayList<>(Arrays.asList(
//                new Session(1,LocalDate.now(),LocalTime.now(),LocalTime.now(),new ArrayList<>()),
//                new Session(2,LocalDate.now(),LocalTime.now(),LocalTime.now(),new ArrayList<>()),
//                new Session(3,LocalDate.now(),LocalTime.now(),LocalTime.now(),new ArrayList<>())
//
//        )));
//        Session session = new Session(1,LocalDate.now(),LocalTime.now(),LocalTime.now(),new ArrayList<>());
//        SessionPayload sessionPayload = new SessionPayload(1,LocalDate.now(),LocalTime.now(),LocalTime.now());
//        when(this.eventRepository.findById(1)).thenReturn(Optional.of(event));
//        when(this.eventService.saveSessionForEvent(1,sessionPayload)).thenReturn(
//                SessionCustomMapper.toSessionPayload(session)
//        );
//        assertEquals(sessionPayload.getId(),session.getId());
//    }
}
