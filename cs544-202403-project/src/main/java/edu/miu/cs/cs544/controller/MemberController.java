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

import java.util.HashMap;

@RestController
@RequestMapping("/members")
public class MemberController extends BaseReadWriteController<MemberPayload, Member, Integer> {
    @Autowired
    private  MemberService memberService;
    @Autowired
    private MemberPayloadToMemberMapper memberPayloadToMemberMapper;

    public MemberController() {
    }

    public MemberController(MemberService memberService, MemberPayloadToMemberMapper memberToMemberPayloadMapper) {
        this.memberService = memberService;
        this.memberPayloadToMemberMapper = memberToMemberPayloadMapper;
    }

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
        return new ResponseEntity<String>("Deleted Successfully!", HttpStatus.OK);
    }

    @GetMapping(path = "/{memberId}/events/{eventId}/attendance")
    public ResponseEntity<Integer> memberAttendanceForEvent(
            @PathVariable(value = "memberId") Integer memberId,
            @PathVariable(value = "eventId") Integer eventId
            ){
        return ResponseEntity.ok(this.memberService.memberAttendanceForEvent(memberId,eventId));
    }

    @GetMapping("/{memberId}/attendance")
    public ResponseEntity<?> getMemberAttendance(@PathVariable Integer memberId) {
        HashMap<String, Integer> result = memberService.getMemberAttendanceOverAccounts(memberId);
        return new ResponseEntity<HashMap<String, Integer>>(result, HttpStatus.OK);
    }
}
