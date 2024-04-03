package com.hka.iwi.usermanagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hka.iwi.usermanagement.User;
import com.hka.iwi.usermanagement.Role;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

// TODO: check if the repository suffices
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query("SELECT r FROM Role r WHERE r.level = :level")
    Role findByLevel(@Param("level") int level);

}