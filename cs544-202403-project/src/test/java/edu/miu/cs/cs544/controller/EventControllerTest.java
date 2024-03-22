package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.controller.EventController;
import edu.miu.cs.cs544.repository.EventRepository;
import edu.miu.cs.cs544.service.EventService;
import edu.miu.cs.cs544.service.contract.SessionPayload;
import edu.miu.cs.cs544.service.mapper.EventPayloadToEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
public class EventControllerTest {
    @Mock
    private EventRepository eventRepository;

   @Mock
    private EventPayloadToEventMapper eventPayloadToEventMapper;

    @Mock
    private EventService eventService;

    @InjectMocks
    private EventController eventController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        eventController = new EventController(eventService, eventRepository, eventPayloadToEventMapper);
    }


    @Test
    void testGetEventAttendance_WithValidEventId() {

        Integer eventId = 1;
        Integer attendanceCount = 100;


        when(eventService.calculateAttendanceForEvent(eventId)).thenReturn(attendanceCount);


        ResponseEntity<?> responseEntity = eventController.getEventAttendance(eventId);


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals((long) attendanceCount, responseEntity.getBody());
        verify(eventService, times(1)).calculateAttendanceForEvent(eventId);
    }

    @Test
    void testGetEventAttendance_WithInvalidEventId() {

        Integer eventId = 1;


        when(eventService.calculateAttendanceForEvent(eventId)).thenThrow(NullPointerException.class);


        ResponseEntity<?> responseEntity = eventController.getEventAttendance(eventId);


        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Event not found", responseEntity.getBody());
        verify(eventService, times(1)).calculateAttendanceForEvent(eventId);
    }

}

