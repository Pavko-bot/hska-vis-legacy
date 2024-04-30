package com.hka.iwi.productmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hka.iwi.productmanagement.Product;
import com.hka.iwi.productmanagement.ProductRepository;
import com.hka.iwi.productmanagement.ProductmanagementController;

import java.util.List;

@Service
public class ProductmanagementService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsForSearchValues(String searchDescription, Double searchMinPrice,
            Double searchMaxPrice) {
        return productRepository.getProductsForSearchValues(searchDescription, searchMinPrice, searchMaxPrice);
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).get();
    }

    public Product getProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Transactional
    public int addProduct(String name, double price, int categoryId, String details) {
        int productId = -1;

        Product product;
        if (details == null) {
            product = new Product(name, price, categoryId);
        } else {
            product = new Product(name, price, categoryId, details);
        }

        productRepository.save(product);
        productId = product.getId();

        return productId;
    }

    @Transactional
    public void deleteProductById(int id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public boolean deleteProductsByCategoryId(int categoryId) {
        return false;
    }

}