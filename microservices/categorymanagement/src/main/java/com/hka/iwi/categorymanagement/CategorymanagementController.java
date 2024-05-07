package com.hka.iwi.categorymanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import com.hka.iwi.categorymanagement.CategorymanagementService;

import java.util.List;

@RestController
public class CategorymanagementController {

    @Autowired
    private CategorymanagementService categorymanagementService;

    @GetMapping(value = "/categories")
    public ResponseEntity getCategories() {
        try {
            List<Category> categories = categorymanagementService.getCategories();
            return ResponseEntity.ok(categories);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/category/{id}")
    public ResponseEntity getCategoryById(@PathVariable int id) {
        try {
            Category category = categorymanagementService.getCategory(id);
            return ResponseEntity.ok(category);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/category", params = { "name" })
    public ResponseEntity getCategoryByName(
            @RequestParam(required = true, name = "name") String name) {
        try {
            Category category = categorymanagementService.getCategoryByName(name);
            return ResponseEntity.ok(category);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PostMapping(value = "/category")
    public ResponseEntity addCategory(@RequestParam(required = true, name = "name") String name) {
        try {
            categorymanagementService.addCategory(name);
            return ResponseEntity.ok("Category added successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @DeleteMapping(value = "/category")
    public ResponseEntity delCategory(@RequestBody(required = true) Category category) {
        try {
            categorymanagementService.delCategory(category);
            return ResponseEntity.ok("Category deleted successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @DeleteMapping(value = "/category/{id}")
    public ResponseEntity delCategoryById(@PathVariable int id) {
        try {
            categorymanagementService.delCategoryById(id);
            return ResponseEntity.ok("Category deleted successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
}
