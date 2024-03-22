package edu.miu.cs.cs544.service;

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

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class EventControllerTest {
    @MockBean
    private EventRepository eventRepository;

    @Mock
    private EventPayloadToEventMapper eventPayloadToEventMapper;

    @MockBean
    private EventService eventService;

    @InjectMocks
    private EventController eventController;

    @BeforeEach

    void setUp() {
        MockitoAnnotations.openMocks(this);
        eventController = new EventController(eventService, eventRepository, eventPayloadToEventMapper);
    }


    @Test
    void testGetSessionForEvent() {

        Integer eventId = 1;
        Integer sessionId = 2;
        SessionPayload sessionPayload = new SessionPayload();


        when(eventService.getSessionForEvent(eventId, sessionId)).thenReturn(sessionPayload);


        ResponseEntity<?> responseEntity = eventController.getSessionForEvent(eventId, sessionId);


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(sessionPayload, responseEntity.getBody());
        verify(eventService, times(1)).getSessionForEvent(eventId, sessionId);
    }

    @Test
    void testCreateSession() {

        Integer eventId = 1;
        SessionPayload sessionPayload = new SessionPayload();


        when(eventService.saveSessionForEvent(eventId, sessionPayload)).thenReturn(sessionPayload);

        ResponseEntity<?> responseEntity = eventController.createSession(eventId, sessionPayload);


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(sessionPayload, responseEntity.getBody());
        verify(eventService, times(1)).saveSessionForEvent(eventId, sessionPayload);
    }

    @Test
    void testUpdateSession() {

        Integer eventId = 1;
        SessionPayload sessionPayload = new SessionPayload();


        when(eventService.updateSession(eventId, sessionPayload)).thenReturn(sessionPayload);


        ResponseEntity<?> responseEntity = eventController.updateSession(eventId, sessionPayload);


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(sessionPayload, responseEntity.getBody());
        verify(eventService, times(1)).updateSession(eventId, sessionPayload);
    }

    @Test
    void testDeleteSession() {

        Integer eventId = 1;
        Integer sessionId = 2;


        when(eventService.deleteSessionFromEvent(eventId, sessionId)).thenReturn("Session deleted successfully");


        ResponseEntity<?> responseEntity = eventController.deleteSession(eventId, sessionId);


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Session deleted successfully", responseEntity.getBody());
        verify(eventService, times(1)).deleteSessionFromEvent(eventId, sessionId);
    }
}