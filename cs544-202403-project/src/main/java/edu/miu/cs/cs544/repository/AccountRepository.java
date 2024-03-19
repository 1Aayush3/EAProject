package edu.miu.cs.cs544.repository;

import edu.miu.common.repository.BaseRepository;
import edu.miu.cs.cs544.domain.Account;

import java.util.List;

public interface AccountRepository extends BaseRepository<Account, Integer>{

    /*
    @Query("SELECT a FROM Attendance a WHERE a.accountId = :accountId AND a.eventDate BETWEEN :startDate AND :endDate")
    List<Attendance> findAttendanceByAccountIdAndDateRange(@Param("accountId") String accountId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
     */
}
