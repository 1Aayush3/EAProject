package edu.miu.cs.cs544.controller;

import edu.miu.common.controller.BaseReadWriteController;
import edu.miu.cs.cs544.domain.Event;
import edu.miu.cs.cs544.repository.EventRepository;
import edu.miu.cs.cs544.service.EventService;
import edu.miu.cs.cs544.service.EventServiceImpl;
import edu.miu.cs.cs544.service.contract.SessionPayload;
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

    @Autowired
    private EventService eventService;


    @GetMapping("/{eventId}/attendance")
    public ResponseEntity<?> getEventAttendance(@PathVariable Integer eventId) {
        try {
            Integer attendanceCount = eventService.calculateAttendanceForEvent(eventId);
            return ResponseEntity.ok((long) attendanceCount);
        } catch (NullPointerException e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found");
        }
    }


    @GetMapping(path = "/{eventId}/sessions/{sessionId}")
    public ResponseEntity<?> getSessionForEvent(@PathVariable(value = "eventId") Integer eventId,@PathVariable(value = "sessionId") Integer sessionId){
        return ResponseEntity.ok(this.eventService.getSessionForEvent(eventId,sessionId));
    }

    @PostMapping(path = "/{eventId}/sessions")
    public ResponseEntity<?> createSession(@PathVariable(value = "eventId") Integer eventId, @RequestBody SessionPayload sessionPayload){
        return ResponseEntity.ok(this.eventService.saveSessionForEvent(eventId,sessionPayload));
    }

    @PutMapping(path = "/{eventId}/sessions")
    public ResponseEntity<?> updateSession(@PathVariable(value = "eventId") Integer eventId, @RequestBody SessionPayload sessionPayload){
        return ResponseEntity.ok(this.eventService.updateSession(eventId,sessionPayload));
    }

    @DeleteMapping(path = "/{eventId}/sessions/{sessionId}")
    public ResponseEntity<?> deleteSession(@PathVariable(value = "eventId") Integer eventId,@PathVariable(value = "sessionId") Integer sessionId){
        return ResponseEntity.ok(this.eventService.deleteSessionFromEvent(eventId,sessionId));
    }


}
