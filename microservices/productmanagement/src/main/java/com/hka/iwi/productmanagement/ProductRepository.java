package com.hka.iwi.productmanagement;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hka.iwi.productmanagement.Product;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

// TODO: check if the interface is correct (see ProductManager.java)
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    public List<Product> getProducts();

    public Product getProductById(int id);

    public Product getProductByName(String name);

    public int addProduct(String name, double price, int categoryId, String details);

    public List<Product> getProductsForSearchValues(String searchValue, Double searchMinPrice, Double searchMaxPrice);

    public boolean deleteProductsByCategoryId(int categoryId);

    public void deleteProductById(int id);
}