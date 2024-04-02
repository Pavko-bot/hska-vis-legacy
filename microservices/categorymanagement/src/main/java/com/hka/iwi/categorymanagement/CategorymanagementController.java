package com.hka.iwi.categorymanagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController // This means that this class is a RestController
public class CategorymanagementController {

    @Autowired // This means to get the bean called userRepository which is auto-generated
    private CategoryService categoryService;

    @GetMapping(value = "/categories")
    public List<Category> getCategories() {
        // TODO: call service
    }

    @GetMapping(value = "/category", params = { "id" })
    public Category getCategoryById(
            @RequestParam(required = true, name = "id") int id) {
        // TODO: call service
    }

    @GetMapping(value = "/category", params = { "name" })
    public Category getCategoryByName(
            @RequestParam(required = true, name = "name") String name) {
        // TODO: call service
    }

    @PostMapping(value = "/category/add", params = { "name" })
    public void addCategory(
            @RequestParam(required = true, name = "name") String name) {
        // TODO: call service
    }

    @DeleteMapping(value = "/category/delete")
    public void delCategory(
            @RequestBody(required = true) Category category) {
        // TODO: call service
    }

    @DeleteMapping(value = "/category/delete", params = { "id" })
    public void delCategoryById(
            @RequestBody(required = true, name = "id") int id) {
        // TODO: call service
    }
}
