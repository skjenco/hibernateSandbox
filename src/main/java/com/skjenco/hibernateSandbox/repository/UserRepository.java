package com.skjenco.hibernateSandbox.repository;

import com.skjenco.hibernateSandbox.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}
