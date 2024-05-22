package hska.iwi.eShopMaster.client;

import feign.Param;
import feign.RequestLine;

import java.util.List;

import hska.iwi.eShopMaster.model.database.dataobjects.User;
import hska.iwi.eShopMaster.model.database.dataobjects.Role;

public interface UserClient {
    @RequestLine("POST /user/register?username={username}&name={name}&lastname={lastname}&password={password}&level={level}")
    void registerUser(@Param("username") String username, @Param("name") String name,
            @Param("lastname") String lastname,
            @Param("password") String password, @Param("level") int level);

    @RequestLine("GET /user?username={username}")
    User getUserByUsername(@Param("username") String username);

    @RequestLine("DELETE /user/{id}")
    boolean deleteUserById(@Param("id") int id);

    @RequestLine("GET /role?level={level}")
    Role getRoleByLevel(@Param("level") int level);

    @RequestLine("GET /users/exists?username={username}")
    Boolean doesUserAlreadyExist(@Param("username") String username);
}