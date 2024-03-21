package edu.miu.cs.cs544;

import edu.miu.cs.cs544.config.AccountConfig;
import edu.miu.cs.cs544.domain.Account;
import edu.miu.cs.cs544.domain.AccountType;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.domain.RoleType;
import edu.miu.cs.cs544.repository.AccountRepository;
import edu.miu.cs.cs544.repository.MemberRepository;
import edu.miu.cs.cs544.service.AccountServiceImpl;
import edu.miu.cs.cs544.service.MemberService;
import edu.miu.cs.cs544.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication(scanBasePackages = {"edu.miu.common", "edu.miu.cs.cs544"})
public class Application implements CommandLineRunner {

    @Autowired
    private MemberService service;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    AccountConfig accountConfig;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Create and save some test accounts
        Member member = new Member();
        member.setFname("hellen"); // Set first name
        member.setLname("Doe"); // Set last name
        member.setEmail("hellen.gen@example.com"); // Set email
        member.setBarCode(7777); // Set barcode
        memberRepository.save(member);

        Account account1 = new Account();
        account1.setAccountType(AccountType.DINING);
        account1.setBalance(10); // Set balance to 10 for testing
        account1.setMember(member); // Set member for testing
        accountRepository.save(account1);

        Account account2 = new Account();
        account2.setAccountType(AccountType.ATTENDANCE);
        account2.setBalance(2); // Set balance to 20 for testing
        account2.setMember(member); // Set member for testing
        accountRepository.save(account2);

        Account account3 = new Account();
        account3.setAccountType(AccountType.LIBRARY);
        account3.setBalance(5); // Set balance to 5 for testing
        account3.setMember(member); // Set member for testing
        accountRepository.save(account3);

        // Call the findAccountsLessThanFivePercentOfDefault method and print the results
        List<Account> accounts = accountService.findAccountsLessThanFivePercentOfDefault();
        for (Account account : accounts) {
            System.out.println("Account ID: " + account.getAccountId() + ", Account Type: " + account.getAccountType() + ", Balance: " + account.getBalance());
        }
    }
}