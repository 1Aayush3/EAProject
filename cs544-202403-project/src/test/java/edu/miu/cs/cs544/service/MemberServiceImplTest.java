package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.DefaultAccountConfig;
import edu.miu.cs.cs544.domain.*;
import edu.miu.cs.cs544.repository.AccountRepository;
import edu.miu.cs.cs544.repository.EventRepository;
import edu.miu.cs.cs544.repository.MemberRepository;
import edu.miu.cs.cs544.service.contract.MemberPayload;
import edu.miu.cs.cs544.service.mapper.MemberPayloadToMemberMapper;
import edu.miu.cs.cs544.service.mapper.MemberToMemberPayloadMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
class MemberServiceImplTest {

    private MockMvc mockMvc;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private EventRepository eventRepository;

//    @Mock
//    private MemberService memberService;

    @InjectMocks
    private MemberServiceImpl memberService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(memberService).build();
        memberService = new MemberServiceImpl(memberRepository, accountRepository, eventRepository);
    }

    @Test
    void whenGetMemberAttendanceOverAccountsThenHashMap() {
        // Mock data
        int memberId = 1;
        List<AttendanceByMemberIdStatistics> statisticsList = Arrays.asList(
                createMockAttendance("DINING", 10),
                createMockAttendance("ATTENDANCE", 20)
        );
        when(memberRepository.calculateAttendanceByMemberId(memberId)).thenReturn(statisticsList);

        // Invoke the method
        HashMap<String, Integer> result = memberService.getMemberAttendanceOverAccounts(memberId);

        // Verify the result
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("DINING", 10);
        expected.put("ATTENDANCE", 20);
        assertEquals(expected, result);
    }

    private AttendanceByMemberIdStatistics createMockAttendance(String accountType, int no) {
        AttendanceByMemberIdStatistics mockAttendance = Mockito.mock(AttendanceByMemberIdStatistics.class);
        when(mockAttendance.getAccountType()).thenReturn(accountType);
        when(mockAttendance.getNo()).thenReturn(no);
        return mockAttendance;
    }

}
