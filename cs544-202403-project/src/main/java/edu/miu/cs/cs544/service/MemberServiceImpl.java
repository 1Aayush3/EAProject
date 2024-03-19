package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Role;
import edu.miu.cs.cs544.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.service.contract.MemberPayload;

import java.util.List;
import java.util.Set;

@Service
public class MemberServiceImpl extends BaseReadWriteServiceImpl<MemberPayload, Member, Integer> implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public Set<Role> getRolesForMember(Integer memberId) {
        return memberRepository.findRoleTypesByMemberId(memberId);
    }


}
