package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteService;
import edu.miu.cs.cs544.domain.Account;
import edu.miu.cs.cs544.domain.AccountType;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.service.contract.AccountPayload;
import edu.miu.cs.cs544.service.contract.SessionPayload;

import java.time.LocalDate;
import java.util.List;

public interface AccountService extends BaseReadWriteService<AccountPayload, Account, Integer> {

    int countAccountsByTypeAndDateRange(AccountType accountType, LocalDate startDate, LocalDate endDate);

    List<Account> findAccountsByTypeAndDateRange(AccountType accountType, LocalDate startDate, LocalDate endDate);


    void reduceBalanceOnAttendance(Integer memberId, AccountType accountType);

    List<Account> findAccountsLessThanFivePercentOfDefault();
}
