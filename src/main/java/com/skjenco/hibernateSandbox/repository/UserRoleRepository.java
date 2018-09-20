package com.skjenco.hibernateSandbox.repository;

import com.skjenco.hibernateSandbox.model.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
}
