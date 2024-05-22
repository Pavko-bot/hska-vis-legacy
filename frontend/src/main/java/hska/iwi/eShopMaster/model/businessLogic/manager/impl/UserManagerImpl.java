package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

import hska.iwi.eShopMaster.client.UserClient;
import hska.iwi.eShopMaster.model.businessLogic.manager.UserManager;
import hska.iwi.eShopMaster.model.database.dataobjects.Role;
import hska.iwi.eShopMaster.model.database.dataobjects.User;

// TODO: Handle optional values

public class UserManagerImpl implements UserManager {

	private final UserClient userClient;

	public UserManagerImpl() {
		this.userClient = Feign.builder()
				.encoder(new GsonEncoder())
				.decoder(new GsonDecoder())
				.target(UserClient.class, "http://user:8080");
	}

	public void registerUser(String username, String name, String lastname, String password, Role role) {
		userClient.registerUser(username, name, lastname, password, role.getLevel());
	}

	public User getUserByUsername(String username) {
		if (username == null || username.isEmpty()) {
			return null;
		}
		return userClient.getUserByUsername(username);
	}

	public boolean deleteUserById(int id) {
		return userClient.deleteUserById(id);
	}

	public Role getRoleByLevel(int level) {
		return userClient.getRoleByLevel(level);
	}

	public boolean doesUserAlreadyExist(String username) {
		return userClient.doesUserAlreadyExist(username);
	}

	public boolean validate(User user) {
		return user != null && !user.getFirstname().isEmpty() && !user.getPassword().isEmpty()
				&& user.getRole() != null && user.getLastname() != null && user.getUsername() != null;
	}
}
