package edu.miu.cs.cs544.repository;

import edu.miu.common.repository.BaseRepository;
import edu.miu.cs.cs544.domain.Event;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.domain.Registration;
import edu.miu.cs.cs544.domain.Session;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface SessionRepository extends BaseRepository<Session, Integer> {
    public Optional<Session> findById(Integer sessionId);
}
