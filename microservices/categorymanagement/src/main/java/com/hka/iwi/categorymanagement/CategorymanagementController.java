package com.hka.iwi.categorymanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.hka.iwi.categorymanagement.CategorymanagementService;

import java.util.List;

// TODO: check whether implementation is correct

@RestController
public class CategorymanagementController {

    @Autowired
    private CategorymanagementService categorymanagementService;

    @GetMapping(value = "/categories")
    public List<Category> getCategories() {
        return categorymanagementService.getCategories();
    }

    @GetMapping(value = "/category", params = { "id" })
    public Category getCategoryById(
            @RequestParam(required = true, name = "id") int id) {
        return categorymanagementService.getCategory(id);
    }

    @GetMapping(value = "/category", params = { "name" })
    public Category getCategoryByName(
            @RequestParam(required = true, name = "name") String name) {
        return categorymanagementService.getCategoryByName(name);
    }

    @PostMapping(value = "/category/add", params = { "name" })
    public void addCategory(
            @RequestParam(required = true, name = "name") String name) {
        categorymanagementService.addCategory(name);
    }

    @DeleteMapping(value = "/category/delete")
    public void delCategory(
            @RequestBody(required = true) Category category) {
        categorymanagementService.delCategory(category);
    }

    @DeleteMapping(value = "/category/delete", params = { "id" })
    public void delCategoryById(
            @RequestBody(required = true) int id) {
        categorymanagementService.delCategoryById(id);
    }
}
