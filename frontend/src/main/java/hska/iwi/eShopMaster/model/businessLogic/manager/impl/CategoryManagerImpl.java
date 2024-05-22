package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

import hska.iwi.eShopMaster.client.CategoryClient;
import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.database.dataobjects.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: Handle optional values

public class CategoryManagerImpl implements CategoryManager {
	private final CategoryClient categoryClient;

	public CategoryManagerImpl() {
		this.categoryClient = Feign.builder()
				.encoder(new GsonEncoder())
				.decoder(new GsonDecoder())
				.target(CategoryClient.class, "http://category:8080");
	}

	public List<Category> getCategories() {
		return categoryClient.getCategories();
	}

	public Category getCategory(int id) {
		return categoryClient.getCategoryById(id);
	}

	public Category getCategoryByName(String name) {
		return categoryClient.getCategoryByName(name);
	}

	public void addCategory(String name) {
		Map<String, Object> requestBody = new HashMap<String, Object>();
		requestBody.put("name", name);
		categoryClient.addCategory(requestBody);
	}

	public void delCategory(Category cat) {
		categoryClient.deleteCategoryById(cat.getId());
	}

	public void delCategoryById(int id) {
		categoryClient.deleteCategoryById(id);
	}
}
