package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.service.MemberService;
import edu.miu.cs.cs544.service.mapper.MemberPayloadToMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.miu.common.controller.BaseReadWriteController;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.service.contract.MemberPayload;

import java.awt.print.Book;

@RestController
@RequestMapping("/members")
public class MemberController extends BaseReadWriteController<MemberPayload, Member, Integer> {
    @Autowired
    private  MemberService memberService;
    @Autowired
    private MemberPayloadToMemberMapper memberPayloadToMemberMapper;

    @PostMapping("create")
    public ResponseEntity<?> createMember(@RequestBody MemberPayload memberPayload) {
        Member member = memberPayloadToMemberMapper.map(memberPayload);
        memberService.createMember(member);
        return new ResponseEntity<Member>(member, HttpStatus.OK);
    }
}
