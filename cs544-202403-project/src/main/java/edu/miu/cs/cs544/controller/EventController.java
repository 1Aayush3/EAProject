package edu.miu.cs.cs544.controller;


import edu.miu.common.controller.BaseReadWriteController;
import edu.miu.cs.cs544.domain.Event;
import edu.miu.cs.cs544.service.contract.SessionPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import edu.miu.cs.cs544.service.contract.EventPayload;
import edu.miu.cs.cs544.service.EventService;


@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController extends BaseReadWriteController<EventPayload, Event, Integer> {

    private final EventService eventService;

    @GetMapping(path = "/{eventId}/sessions")
    public ResponseEntity<?> getAllSessionsForEvent(@PathVariable(value = "eventId") Integer eventId){
        return ResponseEntity.ok(this.eventService.getAllSessionsForEvent(eventId));
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