package edu.miu.cs.cs544.integration.Scheduler;
import edu.miu.cs.cs544.domain.Account;
import edu.miu.cs.cs544.integration.email.EmailService;
import edu.miu.cs.cs544.repository.AccountRepository;
import edu.miu.cs.cs544.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmailScheduler{
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EmailService emailService;

    @Async
    @Scheduled(cron = "0 */1 * * * *")
    public void sendLowBalanceEmails() {
        System.out.println("Running every 1 min..");
        try {
            List<Account> accounts = accountRepository.findAccountsLessThanFivePercentOfDefault();
            Set<String> distinctEmails = accounts.stream()
                    .map(account -> account.getMember().getEmail())
                    .collect(Collectors.toSet());

            distinctEmails.forEach(email -> emailService.sendEmail(email, "Miu Balance","Hi, Your balance is getting low. See you soon!" ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
