package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.service.MemberService;
import edu.miu.cs.cs544.service.mapper.MemberPayloadToMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Override
    @PostMapping
    public ResponseEntity<?> create(@RequestBody MemberPayload memberPayload) {
        Member member = memberPayloadToMemberMapper.map(memberPayload);
        memberService.createMember(member);
        return new ResponseEntity<String>("Created Successfully!", HttpStatus.OK);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody MemberPayload memberPayload) {
        Member member = memberPayloadToMemberMapper.map(memberPayload);
        memberService.updateMember(id, member);
        return new ResponseEntity<String>("Updated Successfully!", HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        memberService.deleteMember(id);
        return new ResponseEntity<String>("Deletedt Successfully!", HttpStatus.OK);
    }
}
