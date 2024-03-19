package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteService;
import edu.miu.cs.cs544.domain.Role;
import edu.miu.cs.cs544.service.contract.RolePayload;

public interface RoleService extends BaseReadWriteService<RolePayload, Role, Integer> {

}
