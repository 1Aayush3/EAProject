package edu.miu.cs.cs544.repository;

import edu.miu.common.repository.BaseRepository;
import edu.miu.cs.cs544.domain.Account;
import edu.miu.cs.cs544.domain.Session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AccountRepository extends BaseRepository<Account, Integer> {

    // AccountRepository.java
    @Query("SELECT m.sessions FROM Member m JOIN m.sessions s WHERE m.account.id = :accountId AND s.date BETWEEN :startDate AND :endDate")
    List<Session> findSessionsByAccountIdAndDateRange(@Param("accountId") Integer accountId,
                                                      @Param("startDate") LocalDate startDate,
                                                      @Param("endDate") LocalDate endDate);
}
