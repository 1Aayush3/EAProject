package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.domain.AccountType;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.service.MemberService;
import edu.miu.cs.cs544.service.contract.MemberPayload;
import edu.miu.cs.cs544.service.mapper.MemberPayloadToMemberMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.springframework.http.HttpStatus;

import java.util.HashMap;


@RunWith(SpringRunner.class)
class MemberControllerTest {
    @Mock
    private MemberService memberService;

    @Mock
    private MemberPayloadToMemberMapper memberPayloadToMemberMapper;

    @InjectMocks
    private MemberController memberController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        memberController = new MemberController(memberService, memberPayloadToMemberMapper);
    }

    @Test
    void testCreate_getStringResponse() {
        // Mock
        Member member = new Member();
        MemberPayload memberPayload = new MemberPayload();

        when(memberPayloadToMemberMapper.map(memberPayload)).thenReturn(member);

        // Act
        ResponseEntity<?> responseEntity = memberController.create(memberPayload);

        // Assert
        verify(memberService, times(1)).createMember(member);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Created Successfully!", responseEntity.getBody());
    }

    @Test
    void testUpdate_getStringResponse() {
        // Mock
        Member member = new Member();
        MemberPayload memberPayload = new MemberPayload();

        when(memberPayloadToMemberMapper.map(memberPayload)).thenReturn(member);

        // Act
        ResponseEntity<?> responseEntity = memberController.update(memberPayload.getMemberId(), memberPayload);

        // Assert
        verify(memberService, times(1)).updateMember(memberPayload.getMemberId(), member);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Updated Successfully!", responseEntity.getBody());
    }

    @Test
    void testDelete_getStringResponse() {
        // Mock
        Integer memberId = 1;

        // Act
        ResponseEntity<?> responseEntity = memberController.delete(memberId);

        // Assert
        verify(memberService, times(1)).deleteMember(memberId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Deleted Successfully!", responseEntity.getBody());
    }

    @Test
    void testGetMemberAttendance_getHashMap() {
        // Mock
        Integer memberId = 1;
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put(AccountType.DINING.name(), 10);
        expected.put(AccountType.ATTENDANCE.name(), 20);

        when(memberService.getMemberAttendanceOverAccounts(1)).thenReturn(expected);

        // Act
        ResponseEntity<?> responseEntity = memberController.getMemberAttendance(memberId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expected, responseEntity.getBody());
    }
}