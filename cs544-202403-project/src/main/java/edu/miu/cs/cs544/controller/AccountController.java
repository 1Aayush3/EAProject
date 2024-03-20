package edu.miu.cs.cs544.controller;

import edu.miu.common.controller.BaseReadWriteController;
import edu.miu.cs.cs544.domain.Account;
import edu.miu.cs.cs544.domain.AccountType;
import edu.miu.cs.cs544.service.AccountService;
import edu.miu.cs.cs544.service.contract.AccountPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController extends BaseReadWriteController<AccountPayload, Account, Integer> {

    @Autowired
    AccountService accountService;

    @GetMapping("/{accountType}/count")
    public int countAccountsByTypeAndDateRange(@PathVariable AccountType accountType, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return accountService.countAccountsByTypeAndDateRange(accountType, startDate, endDate);
    }

    //not important- first trial
    @GetMapping("/type/{accountType}")
    public List<Account> findAccountsByTypeAndDateRange(@PathVariable AccountType accountType, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return accountService.findAccountsByTypeAndDateRange(accountType, startDate, endDate);
    }
}


