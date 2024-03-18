package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Role;
import edu.miu.cs.cs544.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public void create(String type) {
        Role role = new Role();
        role.setRoleType(type);
       roleRepository.save(role);
    }
}
