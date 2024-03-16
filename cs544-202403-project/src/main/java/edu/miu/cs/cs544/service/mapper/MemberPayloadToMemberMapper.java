package edu.miu.cs.cs544.service.mapper;

import org.springframework.stereotype.Component;

import edu.miu.common.service.mapper.BaseMapper;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.service.contract.MemberPayload;
import ma.glasnost.orika.MapperFactory;

@Component
public class MemberPayloadToMemberMapper extends BaseMapper<MemberPayload, Member>{

	public MemberPayloadToMemberMapper(MapperFactory mapperFactory) {
		super(mapperFactory, MemberPayload.class, Member.class);
	}
}
