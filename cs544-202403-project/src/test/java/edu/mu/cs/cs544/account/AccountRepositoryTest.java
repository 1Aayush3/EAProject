package edu.mu.cs.cs544.account;

import edu.miu.cs.cs544.domain.Account;
import edu.miu.cs.cs544.domain.AccountType;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.repository.AccountRepository;
import edu.miu.cs.cs544.service.AccountService;
import edu.miu.cs.cs544.service.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountRepositoryTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountServiceImpl;

    @Test
    public void testCountAccountsByTypeAndDateRange() {
        // Given
        AccountType accountType = AccountType.DINING;
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 1, 31);

        // Mock the behavior of the repository method
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account());
        accounts.add(new Account());
        when(accountRepository.countAccountsByTypeAndDateRange(accountType, startDate, endDate)).thenReturn(accounts.size());

        // When
        int count = accountServiceImpl.countAccountsByTypeAndDateRange(accountType, startDate, endDate);

        // Then
        assertEquals(accounts.size(), count);
        // Verify that the repository method was called with the correct parameters
        verify(accountRepository).countAccountsByTypeAndDateRange(accountType, startDate, endDate);
    }


    @Test
    public void testFindByMemberId() {
        // Given
        int memberId = 123; // Sample member ID
        List<Account> accounts = new ArrayList<>(); // Create a list of accounts
        accounts.add(new Account());
        accounts.add(new Account());
        Optional<List<Account>> optionalAccounts = Optional.of(accounts); // Wrap the list in an Optional

        // Mock the behavior of the repository method
        when(accountRepository.findByMemberId(memberId)).thenReturn(optionalAccounts);

        // When
        Optional<List<Account>> result = accountRepository.findByMemberId(memberId);

        // Then
        assertEquals(optionalAccounts, result); // Assert that the returned Optional<List<Account>> matches the mocked one
    }

    @Test
    public void testFindByMemberIdAndAccountType() {
        // Given
        int memberId = 123; // Sample member ID
        AccountType accountType = AccountType.DINING; // Sample account type
        Account expectedAccount = new Account(); // Create a sample account

        // Mock the behavior of the repository method
        when(accountRepository.findByMemberIdAndAccountType(memberId, accountType)).thenReturn(expectedAccount);

        // When
        Account result = accountRepository.findByMemberIdAndAccountType(memberId, accountType);

        // Then
        assertEquals(expectedAccount, result); // Assert that the returned account matches the expected one
    }

    @Test
    public void testFindAccountsLessThanFivePercentOfDefault() {
        // Given
        List<Account> expectedAccounts = new ArrayList<>();
        Account account1 = new Account();
        account1.setBalance(50);
        account1.setDefaultBalance(1000);
        expectedAccounts.add(account1);

        Account account2 = new Account();
        account2.setBalance(30);
        account2.setDefaultBalance(1000);
        expectedAccounts.add(account2);

        // Mock the behavior of the repository method
        when(accountRepository.findAccountsLessThanFivePercentOfDefault()).thenReturn(expectedAccounts);

        // When
        List<Account> result = accountRepository.findAccountsLessThanFivePercentOfDefault();

        // Then
        assertEquals(expectedAccounts, result);
    }
}
