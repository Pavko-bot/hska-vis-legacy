package com.hka.iwi.usermanagement;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hka.iwi.usermanagement.User;
import com.hka.iwi.usermanagement.Role;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

// TODO: check if the interface is correct (see UserManager.java)
@Repository
public interface UserRepository extends CrudRepository<Product, Integer> {

    public void registerUser(String username, String name, String lastname, String password, Role role);

    public User getUserByUsername(String username);

    public boolean deleteUserById(int id);

    public Role getRoleByLevel(int level);

    public boolean doesUserAlreadyExist(String username);
}