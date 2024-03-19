package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.DefaultAccountConfig;
import edu.miu.cs.cs544.domain.*;
import edu.miu.cs.cs544.repository.AccountRepository;
import edu.miu.cs.cs544.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs.cs544.service.contract.MemberPayload;

@Service
public class MemberServiceImpl extends BaseReadWriteServiceImpl<MemberPayload, Member, Integer> implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public void createMember(Member member) {
        memberRepository.save(member);
        if (!member.getRoles().isEmpty()) {
            for (Role role : member.getRoles()) {
                for (AccountType accountType : DefaultAccountConfig.value.get(role.getRoleType())) {
                    Account account = new Account();
                    account.setMember(member);
                    account.setAccountType(accountType);
                    account.setBalance(1000.00);
                    accountRepository.save(account);
                }
            }
        }
    }
}
