package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MemberControllerTests {
    @Autowired
    MockMvc mock;
    @Mock
    private MemberService memberService;

    @Test
    public void testGetMemberAttendance() throws Exception {

    }
}
