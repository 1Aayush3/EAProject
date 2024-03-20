package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteService;
import edu.miu.cs.cs544.domain.Event;

import edu.miu.cs.cs544.service.contract.EventPayload;
import edu.miu.cs.cs544.service.contract.SessionPayload;

import java.util.List;


public interface EventService extends BaseReadWriteService< EventPayload, Event,Integer> {
    List<SessionPayload> getAllSessionsForEvent(Integer eventId);

    SessionPayload getSessionForEvent(Integer eventId,Integer sessionId);

    SessionPayload saveSessionForEvent(Integer eventId, SessionPayload sessionPayload);

    SessionPayload updateSession(Integer eventId, SessionPayload sessionPayload);

    String deleteSessionFromEvent(Integer eventId, Integer sessionId);
     int calculateAttendanceForEvent(Integer eventId);
}
