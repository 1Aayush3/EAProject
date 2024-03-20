package edu.miu.cs.cs544.controller;

import edu.miu.common.controller.BaseReadWriteController;
import edu.miu.cs.cs544.domain.Event;
import edu.miu.cs.cs544.repository.EventRepository;
import edu.miu.cs.cs544.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import edu.miu.cs.cs544.service.contract.EventPayload;
import edu.miu.cs.cs544.service.mapper.EventPayloadToEventMapper;

@RestController
@RequestMapping("/events")
public class EventController extends BaseReadWriteController<EventPayload, Event, Integer> {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventPayloadToEventMapper eventPayloadToEventMapper;

    @GetMapping("/{eventId}/attendance")
    public ResponseEntity<Long> getEventAttendance(@PathVariable Integer eventId) {
        Long attendanceCount = calculateAttendanceForEvent(eventId);
        return ResponseEntity.ok(attendanceCount);
    }

    public Long calculateAttendanceForEvent(Integer eventId) {
        // Retrieve the event from the service
        Event event = eventRepository.findById(eventId).orElse(null);

        if (event == null) {
            throw new EventNotFoundException("Event not found with ID: " + eventId);
        }

        return event.getSessionList().stream()
                .flatMap(session -> session.getAttendanceList().stream())
                .distinct() // Ensure distinct attendances
                .count();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class EventNotFoundException extends RuntimeException {
        public EventNotFoundException(String message) {
            super(message);
        }
    }
}