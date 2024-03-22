package edu.mu.cs.cs544.account;

import edu.miu.cs.cs544.controller.AccountController;
import edu.miu.cs.cs544.domain.AccountType;
import edu.miu.cs.cs544.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ContextConfiguration(classes = AccountController.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @MockBean
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    void testCountAccountsByTypeAndDateRange() throws Exception {
        AccountType accountType = AccountType.DINING;
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        LocalDate endDate = LocalDate.of(2022, 12, 31);
        int expectedCount = 5;

        when(accountService.countAccountsByTypeAndDateRange(accountType, startDate, endDate)).thenReturn(expectedCount);

        mockMvc.perform(get("/accounts/{accountType}/count", accountType)
                        .param("startDate", startDate.toString())
                        .param("endDate", endDate.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(expectedCount)));
    }
}
