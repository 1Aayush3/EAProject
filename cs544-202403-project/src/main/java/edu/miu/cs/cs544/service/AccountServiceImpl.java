package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs.cs544.domain.Account;
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

    @Autowired
    private SessionToSessionPayloadMapper sessionToSessionPayloadMapper;

    public List<SessionPayload> getAttendanceForAccount(Integer accountId, LocalDate startDate, LocalDate endDate) {
        List<Session> sessions = accountRepository.findSessionsByAccountIdAndDateRange(accountId, startDate, endDate);
        return sessions.stream()
                .map(sessionToSessionPayloadMapper::map)
                .collect(Collectors.toList());
    }
}
