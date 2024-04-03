package com.hka.iwi.usermanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hka.iwi.usermanagement.User;
import com.hka.iwi.usermanagement.Role;
import com.hka.iwi.usermanagement.UserRepository;
import com.hka.iwi.usermanagement.UsermanagementController;

import java.util.List;

@Service
public class UsermanagementService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void registerUser(
            String username,
            String name,
            String lastname,
            String password,
            Role role) {
        User user = new User(username, name, lastname, password, role);
        userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        if (username == null || username.equals("")) {
            return null;
        }
        return userRepository.findByName(username);
    }

    @Transactional
    public boolean deleteUserById(int id) {
        userRepository.deleteById(id);
        return true;
    }

    public Role getRoleByLevel(int level) {
        return userRepository.findByLevel(level);
    }

    public boolean doesUserAlreadyExist(String username) {
        User dbUser = this.getUserByUsername(username);

        return dbUser != null;
    }

    public boolean validate(User user) {
        return !user.getFirstname().isEmpty()
                || !user.getPassword().isEmpty()
                || !user.getRole() == null
                || !user.getLastname() == null
                || !user.getUsername() == null;
    }

}