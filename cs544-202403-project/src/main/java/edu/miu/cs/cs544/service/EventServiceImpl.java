package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs.cs544.domain.Event;


import edu.miu.cs.cs544.domain.Session;
import edu.miu.cs.cs544.repository.EventRepository;
import edu.miu.cs.cs544.service.contract.EventPayload;
import edu.miu.cs.cs544.service.contract.SessionPayload;
import edu.miu.cs.cs544.service.mapper.SessionCustomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl extends BaseReadWriteServiceImpl<EventPayload,  Event,Integer> implements EventService {

    private final EventRepository eventRepository;

    @Override
    public List<SessionPayload> getAllSessionsForEvent(Integer eventId) {
        Optional<Event> eventOptional = this.eventRepository.findById(eventId);
        return eventOptional.map(event -> event.getSessionList().stream().map(SessionCustomMapper::toSessionPayload)
                .collect(Collectors.toList()))
                .orElseGet(ArrayList::new);
    }

    @Override
    public SessionPayload getSessionForEvent(Integer eventId,Integer sessionId) {
        Optional<Event> eventOptional = this.eventRepository.findById(eventId);
        if(eventOptional.isPresent()) {
            Optional<Session> sessionOptional = eventOptional.get().getSessionList().stream().filter(session -> session.getId().equals(sessionId))
                    .findFirst();
            if (sessionOptional.isPresent()) {
                return SessionCustomMapper.toSessionPayload(sessionOptional.get());
            }
        }
        throw new RuntimeException("Event or Session not found");
    }

    @Override
    public SessionPayload saveSessionForEvent(Integer eventId, SessionPayload sessionPayload) {
        Optional<Event> eventOptional = this.eventRepository.findById(eventId);
        if(eventOptional.isPresent()){
            Event event = eventOptional.get();
            event.getSessionList().add(SessionCustomMapper.toSession(sessionPayload));
            this.eventRepository.save(event);
            return sessionPayload;
        }
        return null;
    }

    @Override
    public SessionPayload updateSession(Integer eventId, SessionPayload sessionPayload) {
        Optional<Event> eventOptional = this.eventRepository.findById(eventId);
        if(eventOptional.isPresent()){
            Event event = eventOptional.get();
            Optional<Session> sessionOptional = event.getSessionList()
                    .stream()
                    .filter(session -> session.getId().equals(sessionPayload.getId()))
                    .findFirst();
            if(sessionOptional.isPresent()){
                Session session = sessionOptional.get();
                session.setTime(sessionPayload.getTime());
                session.setDate(sessionPayload.getDate());
                this.eventRepository.save(event);
                return sessionPayload;
            }
        }
        return null;
    }

    @Override
    public String deleteSessionFromEvent(Integer eventId, Integer sessionId) {
        Optional<Event> eventOptional = this.eventRepository.findById(eventId);
        if(eventOptional.isPresent()) {
            this.eventRepository.deleteSession(sessionId);
            return "Deleted";
        }
        return "Event not found";
    }
}
