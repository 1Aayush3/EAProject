package edu.miu.cs.cs544.service.mapper;

import edu.miu.cs.cs544.domain.Session;
import edu.miu.cs.cs544.service.contract.SessionPayload;

public class SessionCustomMapper {

    private SessionCustomMapper(){}

    public static SessionPayload toSessionPayload(Session session){
        SessionPayload sessionPayload = new SessionPayload();
        sessionPayload.setId(session.getId());
        sessionPayload.setDate(session.getDate());
        sessionPayload.setStartTime(session.getStartTime());
        sessionPayload.setEndTime(session.getEndTime());
        return sessionPayload;
    }

    public static Session toSession(SessionPayload sessionPayload){
        Session session = new Session();
        session.setId(sessionPayload.getId());
        session.setDate(sessionPayload.getDate());
        session.setStartTime(sessionPayload.getStartTime());
        session.setEndTime(sessionPayload.getEndTime());
        return session;
    }
}
