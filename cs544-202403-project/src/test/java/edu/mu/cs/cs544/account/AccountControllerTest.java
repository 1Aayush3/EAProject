//package edu.mu.cs.cs544.account;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//@WebMvcTest(AccountController.class)
//@ContextConfiguration(classes = Application.class)
//public class AccountControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private AccountService accountService;
//
//    @MockBean
//    private MemberService memberService;
//
//    @Test
//    public void testCountAccountsByTypeAndDateRange() throws Exception {
//        // Arrange
//        AccountType accountType = AccountType.DINING;
//        LocalDate startDate = LocalDate.of(2022, 1, 1);
//        LocalDate endDate = LocalDate.of(2022, 12, 31);
//        int expectedCount = 5;
//
//        when(accountService.countAccountsByTypeAndDateRange(accountType, startDate, endDate)).thenReturn(expectedCount);
//
//        // Act & Assert
//        mockMvc.perform(get("/accounts/{accountType}/count", accountType)
//                        .param("startDate", startDate.toString())
//                        .param("endDate", endDate.toString()))
//                .andExpect(status().isOk())
//                .andExpect(content().string(String.valueOf(expectedCount)));
//    }
//}