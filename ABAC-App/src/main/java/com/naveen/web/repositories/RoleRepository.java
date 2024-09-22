package com.naveen.web.repositories;

import com.naveen.web.model.Role;
import com.naveen.web.model.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByName(UserRole name);

    Set<Role> findAllByName(UserRole name);
}
