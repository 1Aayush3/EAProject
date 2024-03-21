package edu.miu.cs.cs544.integration.scheduler;

import edu.miu.cs.cs544.domain.Account;
import edu.miu.cs.cs544.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EmailScheduler{
    @Autowired
    private AccountRepository accountRepository;

    @Async
    @Scheduled(cron = "0 */1 * * * *")
    public void sendLowBalanceEmails() {
        System.out.println("Running every 1 mins check");
        try {
//            Account account = accountRepository.findAccountWithLowBalance();
//            emails.forEach(email -> {
//                String message = "Your account balance is low. Please consider adding funds.";
//                emailService.sendEmail(email, "Low Account Balance", message);
//            });
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
