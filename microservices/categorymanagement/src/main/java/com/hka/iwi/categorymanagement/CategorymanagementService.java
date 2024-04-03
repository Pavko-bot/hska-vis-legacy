package com.hka.iwi.categorymanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hka.iwi.categorymanagement.Category;
import com.hka.iwi.categorymanagement.CategoryRepository;
import com.hka.iwi.categorymanagement.CategorymanagementController;

import java.util.List;
import java.util.ArrayList;

@Service
public class CategorymanagementService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<Category>();
        categoryRepository.findAll().forEach(categories::add);
        return categories;
    }

    public Category getCategory(int id) {
        // TODO: Handle 'Optional'
        return categoryRepository.findById(id).get();
    }

    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Transactional
    public void addCategory(String name) {
        Category cat = new Category(name);
        categoryRepository.save(cat);
    }

    @Transactional
    public void delCategory(Category cat) {
        categoryRepository.deleteById(cat.getId());
    }

    @Transactional
    public void delCategoryById(int id) {
        categoryRepository.deleteById(id);
    }

}