package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs.cs544.domain.Account;
import edu.miu.cs.cs544.domain.AccountType;
import edu.miu.cs.cs544.exceptions.LowBalanceException;
import edu.miu.cs.cs544.repository.AccountRepository;
import edu.miu.cs.cs544.repository.MemberRepository;
import edu.miu.cs.cs544.service.contract.AccountPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AccountServiceImpl extends BaseReadWriteServiceImpl<AccountPayload, Account, Integer> implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    MemberRepository memberRepository;


    @Override
    public int countAccountsByTypeAndDateRange(AccountType accountType, LocalDate startDate, LocalDate endDate) {
        return accountRepository.countAccountsByTypeAndDateRange(accountType, startDate, endDate);
    }

    @Override
    public List<Account> findAccountsByTypeAndDateRange(AccountType accountType, LocalDate startDate, LocalDate endDate) {
        return accountRepository.findAccountsByTypeAndDateRange(accountType, startDate, endDate);
    }


    @Override
    public void reduceBalanceOnAttendance(Integer memberId, AccountType accountType) {
        Account account = accountRepository.findByMemberIdAndAccountType(memberId, accountType);

        if (account == null) {
            throw new IllegalArgumentException("No account found for member with id " + memberId + " and account type " + accountType);
        }

        if (account.getBalance() > 0) {
            account.setBalance(account.getBalance() - 1);
            accountRepository.save(account);
        } else {
            throw new LowBalanceException("Cannot reduce balance. Account balance is zero.");
        }
    }

    @Override
    public List<Account> findAccountsLessThanFivePercentOfDefault() {
        return accountRepository.findAccountsLessThanFivePercentOfDefault();
    }


}



