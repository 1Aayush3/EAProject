package edu.miu.cs.cs544.repository;

import edu.miu.common.repository.BaseRepository;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.domain.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface MemberRepository extends BaseRepository<Member, Integer>{
    @Query("SELECT m.roleTypes FROM Member m WHERE m.memberId = :memberId")
    Set<Role> findRoleTypesByMemberId(@Param("memberId") Integer memberId);




}
