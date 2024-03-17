package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.service.mapper.EventToEventPayLoadMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.miu.cs.cs544.service.contract.EventPayLoad;
import edu.miu.cs.cs544.domain.Event;
import edu.miu.cs.cs544.service.mapper.EventPayLoadToEventMapper;

import edu.miu.cs.cs544.service.EventService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;
    private final EventPayLoadToEventMapper eventPayLoadToEventMapper;
    private final EventToEventPayLoadMapper eventToEventPayLoadMapper;

    public EventController(EventService eventService, EventPayLoadToEventMapper eventPayLoadToEventMapper, EventToEventPayLoadMapper eventToEventPayLoadMapper) {
        this.eventService = eventService;
        this.eventPayLoadToEventMapper = eventPayLoadToEventMapper;
        this.eventToEventPayLoadMapper = eventToEventPayLoadMapper;
    }


//    @PostMapping("/create")
//    public ResponseEntity<String> createEvent(@RequestBody EventPayLoad eventPayload) {
//        try {
//            // Map EventPayLoad to Event
//            Event eventToCreate =eventPayLoadToEventMapper.mapToEntity(eventPayload);
//
//            // Create event using the mapped eventToCreate
//            eventService.create(eventToCreate);
//
//            return new ResponseEntity<>("Event created successfully", HttpStatus.CREATED);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>("Failed to create event", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping("/all")
    public ResponseEntity<List<EventPayLoad>> getAllEvents() {

        Collection<Event> eventsCollection = eventService.findAll();

        List<Event> events = eventsCollection.stream().collect(Collectors.toList());

        List<EventPayLoad> eventPayloads = events.stream()
                .map(eventToEventPayLoadMapper::mapToDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(eventPayloads, HttpStatus.OK);
    }
    @GetMapping("/{eventId}")
    public ResponseEntity<EventPayLoad> getEventById(@PathVariable("eventId") Integer eventId) {
        Event event = eventService.findById(eventId); // Assuming eventService returns an Event
        if (event != null) {
            EventPayLoad eventPayload = eventToEventPayLoadMapper.mapToDTO(event);
            return new ResponseEntity<>(eventPayload, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/update/{eventId}")
    public ResponseEntity<String> updateEvent(@PathVariable("eventId") Integer eventId, @RequestBody EventPayLoad eventPayload) {
        try {
            // Map EventPayLoad to Event
            Event eventToUpdate = eventPayLoadToEventMapper.mapToEntity(eventPayload);

            // Perform update operation using the eventId and the eventToUpdate
            eventService.update(eventId, eventToUpdate);

            return new ResponseEntity<>("Event updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to update event", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
