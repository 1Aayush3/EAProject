package edu.miu.cs.cs544.repository;

import edu.miu.common.repository.BaseRepository;
import edu.miu.cs.cs544.domain.Event;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends BaseRepository<Event, Integer> {

    @Query(value = "delete from Session s where s.id = :sessionId")
    @Transactional
    @Modifying
    void deleteSession(@Param(value = "sessionId") Integer sessionId);
}
