package edu.miu.cs.cs544.repository;

import edu.miu.common.repository.BaseRepository;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.domain.Session;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends BaseRepository<Member, Integer>{
    Optional<Member> findByMemberId(Integer memberId);


    @Query(value = "select a.session from Attendance a where a.member.memberId = :memberId")
    List<Session> memberAttendanceForEvent(Integer memberId);
}
