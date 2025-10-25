package org.example.userservice.Repositories;

import org.example.userservice.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long>
{
    Optional<Role> findById(Long Id);

    Optional<Role> findByRoleName(String roleName);
}
