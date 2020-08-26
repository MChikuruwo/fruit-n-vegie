package com.fruitnvegie.fruitnvegieapi.services;

import com.fruitnvegie.fruitnvegieapi.models.User;

import java.util.List;

public interface UserService {
    String add(User user);
    String update(User user);
    String delete(Integer id);
    List<User> getAll();
    User getOne(Integer id);
    User authUser(String emailAddress, String password) throws Exception;
    User findByEmailAddress(String emailAddress);

}
