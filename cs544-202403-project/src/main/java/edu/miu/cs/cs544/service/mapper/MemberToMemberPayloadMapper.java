package edu.miu.cs.cs544.service.mapper;

import org.springframework.stereotype.Component;

import edu.miu.common.service.mapper.BaseMapper;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.service.contract.MemberPayload;
import ma.glasnost.orika.MapperFactory;

@Component
public class MemberToMemberPayloadMapper extends BaseMapper<Member, MemberPayload> {

	public MemberToMemberPayloadMapper(MapperFactory mapperFactory) {
		super(mapperFactory, Member.class, MemberPayload.class);
	}

}
