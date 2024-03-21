package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.*;
import edu.miu.cs.cs544.repository.AccountRepository;
import edu.miu.cs.cs544.repository.EventRepository;
import edu.miu.cs.cs544.repository.MemberRepository;
import edu.miu.cs.cs544.service.contract.MemberPayload;
import edu.miu.cs.cs544.service.mapper.MemberToMemberPayloadMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
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

    @InjectMocks
    private MemberServiceImpl memberServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(memberServiceImpl).build();
        memberServiceImpl = new MemberServiceImpl(memberRepository, accountRepository, eventRepository);
    }

    @Test
    void testCreateMember() {
        // Mock
        Integer memberId = 1;
        Member member = new Member();
        member.setMemberId(memberId);
        member.setBarCode(100);
        member.setEmail("test@gmail.com");

        Role role = new Role();
        role.setRoleType(RoleType.Student);
        member.setRoles(new HashSet<>(){{
            add(role);
        }});

        Account account1 = new Account();
        account1.setAccountType(AccountType.DINING);
        account1.setMember(member);
        account1.setStatus(true);

        Account account2 = new Account();
        account2.setAccountType(AccountType.ATTENDANCE);
        account2.setMember(member);
        account2.setStatus(true);

        Account account3 = new Account();
        account3.setAccountType(AccountType.GYM);
        account3.setMember(member);
        account3.setStatus(true);

        // Act
        memberServiceImpl.updateMember(memberId, member);

        // Assert
        verify(memberRepository).save(Mockito.<Member>any());
        verify(accountRepository, times(3)).save(Mockito.<Account>any());

    }

    @Test
    void testUpdateMember() {
        // Mock
        Integer memberId = 1;
        Member member = new Member();
        member.setMemberId(memberId);
        member.setBarCode(100);
        member.setEmail("test@gmail.com");

        Role role = new Role();
        role.setRoleType(RoleType.Student);
        member.setRoles(new HashSet<>(){{
            add(role);
        }});

        Account account1 = new Account();
        account1.setAccountType(AccountType.DINING);
        account1.setMember(member);
        account1.setStatus(true);

        Account account2 = new Account();
        account2.setAccountType(AccountType.ATTENDANCE);
        account2.setMember(member);
        account2.setStatus(true);

        Account account3 = new Account();
        account3.setAccountType(AccountType.GYM);
        account3.setMember(member);
        account3.setStatus(false);

        Role role1 = new Role();
        role1.setRoleType(RoleType.Student);
        member.setRoles(new HashSet<>(){{
            add(role1);
        }});

        account1.setStatus(true);
        account2.setStatus(true);
        account3.setStatus(false);

        // Act
        memberServiceImpl.createMember(member);

        // Assert
        verify(memberRepository).save(Mockito.<Member>any());
        verify(accountRepository, times(3)).save(Mockito.<Account>any());

    }

    @Test
    void deleteMember() {
        // Mock
        Integer memberId = 1;
        Member member = new Member();
        member.setMemberId(memberId);
        member.setBarCode(100);
        member.setEmail("test@gmail.com");

        Role role = new Role();
        role.setRoleType(RoleType.Student);
        member.setRoles(new HashSet<>(){{
            add(role);
        }});

        Account account1 = new Account();
        account1.setAccountType(AccountType.DINING);
        account1.setMember(member);
        account1.setStatus(true);

        Account account2 = new Account();
        account2.setAccountType(AccountType.ATTENDANCE);
        account2.setMember(member);
        account2.setStatus(true);

        Account account3 = new Account();
        account3.setAccountType(AccountType.GYM);
        account3.setMember(member);
        account3.setStatus(true);

        // Act
        when(memberRepository.findByMemberId(memberId).get()).thenReturn(member);

        memberServiceImpl.deleteMember(memberId);

        // Assert
        verify(memberRepository).delete(Mockito.<Member>any());
        verify(accountRepository, times(3)).delete(Mockito.<Account>any());

    }

    @Test
    void testGetMemberAttendanceOverAccounts() {
        // Mock
        Integer memberId = 1;
        List<AttendanceByMemberIdStatistics> statisticsList = Arrays.asList(
                createMockAttendance("DINING", 10),
                createMockAttendance("ATTENDANCE", 20)
        );
        when(memberRepository.calculateAttendanceByMemberId(memberId)).thenReturn(statisticsList);
        HashMap<String, Integer> expected = new HashMap<>();
        for (AttendanceByMemberIdStatistics data : statisticsList) {
            expected.put(data.getAccountType(), data.getNo());
        }

        // Act
        HashMap<String, Integer> result = memberServiceImpl.getMemberAttendanceOverAccounts(memberId);

        // Assert
        assertEquals(expected, result);
    }

    private AttendanceByMemberIdStatistics createMockAttendance(String accountType, int no) {
        AttendanceByMemberIdStatistics mockAttendance = Mockito.mock(AttendanceByMemberIdStatistics.class);
        when(mockAttendance.getAccountType()).thenReturn(accountType);
        when(mockAttendance.getNo()).thenReturn(no);
        return mockAttendance;
    }
}