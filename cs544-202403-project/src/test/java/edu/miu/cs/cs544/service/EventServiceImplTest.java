package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Attendance;
import edu.miu.cs.cs544.domain.Event;
import edu.miu.cs.cs544.domain.Session;
import edu.miu.cs.cs544.repository.EventRepository;
import edu.miu.cs.cs544.service.mapper.EventPayloadToEventMapper;
import edu.miu.cs.cs544.service.mapper.EventToEventPayloadMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventServiceImplTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private EventPayloadToEventMapper eventPayloadToEventMapper;

    @Mock
    private EventToEventPayloadMapper eventToEventPayloadMapper;

    @InjectMocks
    private EventServiceImpl eventService;

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
}
