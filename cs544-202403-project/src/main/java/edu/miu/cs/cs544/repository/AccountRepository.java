package edu.miu.cs.cs544.repository;

import edu.miu.common.repository.BaseRepository;
import edu.miu.cs.cs544.domain.Account;
import edu.miu.cs.cs544.domain.AccountType;
import edu.miu.cs.cs544.domain.Attendance;
import edu.miu.cs.cs544.domain.Session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AccountRepository extends BaseRepository<Account, Integer> {
    @Query("SELECT COUNT(a) FROM Account a JOIN a.member m JOIN m.attendanceList at WHERE a.accountType = :accountType AND at.date BETWEEN :startDate AND :endDate")
    int countAccountsByTypeAndDateRange(@Param("accountType") AccountType accountType, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT a FROM Account a JOIN a.member m JOIN m.attendanceList at WHERE a.accountType = :accountType AND at.date BETWEEN :startDate AND :endDate")
    List<Account> findAccountsByTypeAndDateRange(@Param("accountType") AccountType accountType, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
