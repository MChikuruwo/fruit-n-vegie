package com.fruitnvegie.fruitnvegieapi.services;


import com.fruitnvegie.fruitnvegieapi.models.Login;
import com.fruitnvegie.fruitnvegieapi.models.User;

import java.util.Date;
import java.util.List;

public interface LoginService {
    Login add(Login login);
    List<Login> getAll();
    Login getOne(Integer id);

    List<Login> findAllByUser(User user);
    List<Login> findAllByDate(Date date);
}
