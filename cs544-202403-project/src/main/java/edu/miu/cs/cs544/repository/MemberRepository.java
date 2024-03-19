package edu.miu.cs.cs544.repository;

import edu.miu.common.repository.BaseRepository;
import edu.miu.cs.cs544.domain.Member;
import feign.Param;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends BaseRepository<Member, Integer> {
    @Query(value = "select count(*) from Attendance a where a.memberId = :memberId", nativeQuery = true)
    Integer calculateAttendanceByMemberId(@Param("memberId") Integer memberId);
}
