package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

import hska.iwi.eShopMaster.client.ProductClient;
import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.database.dataobjects.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: Handle optional values

public class ProductManagerImpl implements ProductManager {

	private final ProductClient productClient;

	public ProductManagerImpl() {
		this.productClient = Feign.builder()
				.encoder(new GsonEncoder())
				.decoder(new GsonDecoder())
				.target(ProductClient.class, "http://product:8080");
	}

	public List<Product> getProducts() {
		return productClient.getProducts();
	}

	public List<Product> getProductsForSearchValues(String searchDescription, Double searchMinPrice,
			Double searchMaxPrice) {
		return productClient.getProductsForSearchValues(searchDescription, searchMinPrice, searchMaxPrice);
	}

	public Product getProductById(int id) {
		return productClient.getProductById(id);
	}

	public Product getProductByName(String name) {
		return productClient.getProductByName(name);
	}

	public int addProduct(String name, double price, int categoryId, String details) {
		return productClient.addProduct(name, price, categoryId, details);
	}

	public void deleteProductById(int id) {
		productClient.deleteProductById(id);
	}

	public boolean deleteProductsByCategoryId(int categoryId) {
		return productClient.deleteProductsByCategoryId(categoryId);
	}
}
