package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.DefaultAccountConfig;
import edu.miu.cs.cs544.domain.*;
import edu.miu.cs.cs544.repository.AccountRepository;
import edu.miu.cs.cs544.repository.EventRepository;
import edu.miu.cs.cs544.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs.cs544.service.contract.MemberPayload;

import java.util.*;

@Service
public class MemberServiceImpl extends BaseReadWriteServiceImpl<MemberPayload, Member, Integer> implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EventRepository eventRepository;

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
                    account.setStatus(true);
                    accountRepository.save(account);
                }
            }
        }
    }

    @Transactional
    public void updateMember(Integer memberId, Member member) {
        memberRepository.save(member);
        List<Account> existingAccounts = accountRepository.findByMember(member).orElse(new ArrayList<>());
        Set<AccountType> accountTypeSet = new HashSet<>();
        for (Role role : member.getRoles()) {
            accountTypeSet.addAll(DefaultAccountConfig.value.get(role.getRoleType()));
        }
        for (Account account : existingAccounts) {
            if (accountTypeSet.contains(account.getAccountType())) {
                account.setStatus(true);
                accountTypeSet.remove(account.getAccountType());
            } else {
                account.setStatus(false);
            }
            accountRepository.save(account);
        }
        for (AccountType accountType : accountTypeSet) {
            Account account = new Account();
            account.setMember(member);
            account.setAccountType(accountType);
            account.setBalance(1000.00);
            account.setStatus(true);
            accountRepository.save(account);
        }
    }

    @Transactional
    public void deleteMember(Integer memberId) {
        Member member = memberRepository.findByMemberId(memberId).get();
        List<Account> accounts = accountRepository.findByMemberId(memberId).orElse(new ArrayList<>());
        for (Account account : accounts) {
            accountRepository.delete(account);
        }
        memberRepository.delete(member);
    }

    @Override
    public Integer memberAttendanceForEvent(Integer memberId, Integer eventId) {
        int result = 0;
        Optional<Event> eventOptional = this.eventRepository.findById(eventId);
        if (eventOptional.isPresent()) {
            List<Session> sessions = this.memberRepository.memberAttendanceForEvent(memberId);
            for (Session eventSession : eventOptional.get().getSessionList()) {
                long count = sessions.stream().filter(session -> session.getId().equals(eventSession.getId())).count();
                if(count>=1){
                    result++;
                }
            }
            return result;
        }
        return result;
    }

    public HashMap<String, Integer> getMemberAttendanceOverAccounts(Integer memberId) {
        HashMap<String, Integer> result = new HashMap<>();
        List<AttendanceByMemberIdStatistics> dataFromRepo = memberRepository.calculateAttendanceByMemberId(memberId);
        for (AttendanceByMemberIdStatistics data : dataFromRepo) {
            result.put(data.getAccountType(), data.getNo());
        }
        return result;
    }
}
