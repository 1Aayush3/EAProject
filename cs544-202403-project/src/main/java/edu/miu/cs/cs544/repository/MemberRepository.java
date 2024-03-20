package edu.miu.cs.cs544.repository;

import edu.miu.common.repository.BaseRepository;
import edu.miu.cs.cs544.domain.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends BaseRepository<Member, Integer>{
    Optional<Member> findByMemberId(Integer memberId);

    @Query(value = "select s.AccountType, count(s.AccountType) from Attendance a join Session s on a.sessionId = s.sessionId " +
                    "join Event e on s.event_id = e.id join Scan sc on e.id = sc.event_id " +
                    "where a.memberId = :memberId group by s.account_type", nativeQuery = true)
    List<Object[]> calculateAttendanceByMemberId(@Param("memberId") Integer memberId);
}
