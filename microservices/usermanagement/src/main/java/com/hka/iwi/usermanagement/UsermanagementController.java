package com.hka.iwi.usermanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import com.hka.iwi.usermanagement.UsermanagementService;

import java.util.List;

@RestController
public class UsermanagementController {

    @Autowired
    private UsermanagementService usermanagementService;

    @PostMapping(value = "/user/register", params = { "username", "name", "lastname", "password", "level" })
    public void registerUser(
            @RequestParam(required = true, name = "username") String username,
            @RequestParam(required = true, name = "name") String name,
            @RequestParam(required = true, name = "lastname") String lastname,
            @RequestParam(required = true, name = "password") String password,
            @RequestParam(required = true, name = "level") int level) {
        usermanagementService.registerUser(username, name, lastname, password, level);
    }

    @GetMapping(value = "/user", params = { "username" })
    public User getUserByUsername(@RequestParam(required = true, name = "username") String username) {
        return usermanagementService.getUserByUsername(username);
    }

    @DeleteMapping(value = "/user/{id}")
    public boolean deleteUserById(@PathVariable int id) {
        return usermanagementService.deleteUserById(id);
    }

    @GetMapping(value = "/role", params = { "level" })
    public Role getRoleByLevel(@RequestParam(required = true, name = "level") int level) {
        return usermanagementService.getRoleByLevel(level);
    }

    @GetMapping(value = "/users/exists", params = { "username" })
    public boolean doesUserAlreadyExist(@RequestParam(required = true, name = "username") String username) {
        return usermanagementService.doesUserAlreadyExist(username);
    }

}
