package edu.miu.cs.cs544.repository;

import edu.miu.common.repository.BaseRepository;
import edu.miu.cs.cs544.domain.Account;
import edu.miu.cs.cs544.domain.AccountType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import edu.miu.cs.cs544.domain.Member;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AccountRepository extends BaseRepository<Account, Integer> {
    @Query("SELECT COUNT(a) FROM Account a JOIN a.member m JOIN m.attendanceList at WHERE a.accountType = :accountType AND at.date BETWEEN :startDate AND :endDate")
    int countAccountsByTypeAndDateRange(@Param("accountType") AccountType accountType, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT a FROM Account a JOIN a.member m JOIN m.attendanceList at WHERE a.accountType = :accountType AND at.date BETWEEN :startDate AND :endDate")
    List<Account> findAccountsByTypeAndDateRange(@Param("accountType") AccountType accountType, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    Optional<List<Account>> findByMember(Member member);

    @Query("select a from Account a where a.member.memberId = :memberId")
    Optional<List<Account>> findByMemberId(@Param("memberId") Integer memberId);

    //List<Account> findByAccountType(AccountType accountType);

    @Query("SELECT a FROM Account a WHERE a.member.memberId = :memberId AND a.accountType = :accountType")
    Account findByMemberIdAndAccountType(@Param("memberId") Integer memberId, @Param("accountType") AccountType accountType);

    @Query("SELECT a FROM Account a WHERE a.balance < a.defaultBalance * 0.05")
    List<Account> findAccountsLessThanFivePercentOfDefault();

}
