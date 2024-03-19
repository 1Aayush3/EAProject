package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.domain.Role;
import edu.miu.cs.cs544.service.MemberService;
import edu.miu.cs.cs544.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.miu.common.controller.BaseReadWriteController;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.service.contract.MemberPayload;

import java.util.Set;

@RestController
@RequestMapping("/members")
public class MemberController extends BaseReadWriteController<MemberPayload, Member, Integer> {
    @Autowired
    private MemberServiceImpl memberService;
    @GetMapping("/{memberId}/roles")
    public Set<Role> getRolesForMember(@PathVariable Integer memberId) {
        return memberService.getRolesForMember(memberId);
    }



}
