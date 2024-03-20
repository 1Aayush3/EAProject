package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.domain.Event;
import edu.miu.cs.cs544.repository.EventRepository;
import edu.miu.cs.cs544.service.mapper.EventPayloadToEventMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventControllerTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private EventPayloadToEventMapper eventPayloadToEventMapper;

    @InjectMocks
    private EventController eventController;

    @Test
    void testGetEventAttendance() {

        Integer eventId = 1;
        Event event = new Event();
        event.setId(eventId);


        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));



        ResponseEntity<Long> responseEntity = eventController.getEventAttendance(eventId);


        assertEquals(200, responseEntity.getStatusCodeValue());

        verify(eventRepository, times(1)).findById(eventId);

    }

    @Test
    void testGetEventAttendance_EventNotFound() {

        Integer eventId = 1;


        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());


        try {
            eventController.getEventAttendance(eventId);
        } catch (EventController.EventNotFoundException e) {

            assertEquals("Event not found with ID: " + eventId, e.getMessage());
        }

        verify(eventRepository, times(1)).findById(eventId);

    }
}
