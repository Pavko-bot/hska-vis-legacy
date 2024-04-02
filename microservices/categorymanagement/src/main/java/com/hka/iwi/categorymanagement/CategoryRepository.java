package com.hka.iwi.categorymanagement;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hka.iwi.categorymanagement.Category;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

// TODO: check if the interface is correct (see CategoryManager.java)
@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

    public List<Category> getCategories();

    public Category getCategory(int id);

    public Category getCategoryByName(String name);

    public void addCategory(String name);

    public void delCategory(Category cat);

    public void delCategoryById(int id);
}