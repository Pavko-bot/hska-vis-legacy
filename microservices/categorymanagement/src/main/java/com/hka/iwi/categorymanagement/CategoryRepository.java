package com.hka.iwi.categorymanagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hka.iwi.categorymanagement.Category;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

// TODO: check if the repository suffices
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}