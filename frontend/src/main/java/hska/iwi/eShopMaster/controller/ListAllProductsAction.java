package hska.iwi.eShopMaster.controller;

import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ProductManagerImpl;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.CategoryManagerImpl;
import hska.iwi.eShopMaster.model.database.dataobjects.Product;
import hska.iwi.eShopMaster.model.database.dataobjects.Category;
import hska.iwi.eShopMaster.model.database.dataobjects.User;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ListAllProductsAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -94109228677381902L;

	User user;
	private List<Product> products;

	public String execute() throws Exception {
		String result = "input";

		Map<String, Object> session = ActionContext.getContext().getSession();
		user = (User) session.get("webshop_user");

		if (user != null) {
			System.out.println("list all products!");
			ProductManager productManager = new ProductManagerImpl();
			CategoryManager categoryManager = new CategoryManagerImpl();
			List<Product> mappedProducts = new ArrayList<Product>();

			for (Product externalProduct : productManager.getProducts()) {
				Category category = categoryManager.getCategory(externalProduct.getCategoryId());
				Product mappedProduct = new Product(externalProduct.getName(), externalProduct.getPrice(), category,
						externalProduct.getDetails());
				mappedProduct.setId(externalProduct.getId());
				mappedProducts.add(mappedProduct);
			}
			this.products = mappedProducts;
			result = "success";
		}

		return result;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
