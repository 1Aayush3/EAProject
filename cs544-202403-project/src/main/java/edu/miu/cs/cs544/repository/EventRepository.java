package edu.miu.cs.cs544.repository;

import edu.miu.common.repository.BaseRepository;
import edu.miu.cs.cs544.domain.Event;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends BaseRepository<Event, Integer> {
}
