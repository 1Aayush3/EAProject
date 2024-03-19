package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteService;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.domain.Role;
import edu.miu.cs.cs544.service.contract.MemberPayload;

import java.util.List;

public interface MemberService extends BaseReadWriteService <MemberPayload, Member, Integer>{

}
