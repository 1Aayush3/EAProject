package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs.cs544.domain.Account;
import edu.miu.cs.cs544.domain.AccountType;
import edu.miu.cs.cs544.domain.Session;
import edu.miu.cs.cs544.repository.AccountRepository;
import edu.miu.cs.cs544.service.contract.AccountPayload;
import edu.miu.cs.cs544.service.contract.SessionPayload;
import edu.miu.cs.cs544.service.mapper.SessionToSessionPayloadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl extends BaseReadWriteServiceImpl<AccountPayload, Account, Integer> implements AccountService {

    @Autowired
    AccountRepository accountRepository;


    @Override
    public int countAccountsByTypeAndDateRange(AccountType accountType, LocalDate startDate, LocalDate endDate) {
        return accountRepository.countAccountsByTypeAndDateRange(accountType, startDate, endDate);
    }

    @Override
    public List<Account> findAccountsByTypeAndDateRange(AccountType accountType, LocalDate startDate, LocalDate endDate) {
        return accountRepository.findAccountsByTypeAndDateRange(accountType, startDate, endDate);
    }
}
