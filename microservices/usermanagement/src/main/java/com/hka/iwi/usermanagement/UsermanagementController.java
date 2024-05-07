package com.hka.iwi.usermanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity registerUser(
            @RequestParam(required = true, name = "username") String username,
            @RequestParam(required = true, name = "name") String name,
            @RequestParam(required = true, name = "lastname") String lastname,
            @RequestParam(required = true, name = "password") String password,
            @RequestParam(required = true, name = "level") int level) {
        try {
            usermanagementService.registerUser(username, name, lastname, password, level);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/user", params = { "username" })
    public ResponseEntity getUserByUsername(@RequestParam(required = true, name = "username") String username) {
        try {
            User user = usermanagementService.getUserByUsername(username);
            return ResponseEntity.ok(user);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity deleteUserById(@PathVariable int id) {
        try {
            Boolean isUserDeleted = usermanagementService.deleteUserById(id);
            return ResponseEntity.ok(isUserDeleted);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/role", params = { "level" })
    public ResponseEntity getRoleByLevel(@RequestParam(required = true, name = "level") int level) {
        try {
            Role role = usermanagementService.getRoleByLevel(level);
            return ResponseEntity.ok(role);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/users/exists", params = { "username" })
    public ResponseEntity doesUserAlreadyExist(
            @RequestParam(required = true, name = "username") String username) {
        try {
            Boolean doesUserAlreadyExist = usermanagementService.doesUserAlreadyExist(username);
            return ResponseEntity.ok(doesUserAlreadyExist);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

}
