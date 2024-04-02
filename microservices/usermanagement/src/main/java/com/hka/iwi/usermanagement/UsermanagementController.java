package com.hka.iwi.usermanagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController // This means that this class is a RestController
public class UsermanagementController {

    @Autowired // This means to get the bean called userRepository which is auto-generated
    private UserService userService;

    @PostMapping(value = "/user/register", params = { "username", "name", "lastname", "password", "role" })
    void registerUser(
            @RequestParam(required = true, name = "username") String username,
            @RequestParam(required = true, name = "name") String name,
            @RequestParam(required = true, name = "lastname") String lastname,
            @RequestParam(required = true, name = "password") String password,
            @RequestParam(required = true, name = "role") Role role) {
        // TODO: call service
    }

    @GetMapping(value = "/user", params = { "username" })
    User getUserByUsername(@RequestParam(required = true, name = "username") String username) {
        // TODO: call service
    }

    @DeleteMapping(value = "/user", params = { "id" })
    boolean deleteUserById(@RequestParam(required = true, name = "id") int id) {
        // TODO: call service
    }

    @GetMapping(value = "/user/role", params = { "level" })
    Role getRoleByLevel(@RequestParam(required = true, name = "level") int level) {
        // TODO: call service
    }

    @GetMapping(value = "/users/exists", params = { "username" })
    boolean doesUserAlreadyExist(@RequestParam(required = true, name = "username") String username) {
        // TODO : call service
    }

}
