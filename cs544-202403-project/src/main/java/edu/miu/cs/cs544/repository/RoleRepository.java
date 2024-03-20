package edu.miu.cs.cs544.repository;

import edu.miu.cs.cs544.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
