package edu.miu.cs.cs544.repository;

import edu.miu.common.repository.BaseRepository;
import edu.miu.cs.cs544.domain.Registration;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends BaseRepository<Registration, Integer> {
}
