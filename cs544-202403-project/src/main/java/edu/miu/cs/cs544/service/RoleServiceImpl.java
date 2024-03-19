package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs.cs544.domain.Role;
import edu.miu.cs.cs544.repository.RoleRepository;

import edu.miu.cs.cs544.service.contract.RolePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseReadWriteServiceImpl<RolePayload,Role,Integer> implements RoleService {

}
