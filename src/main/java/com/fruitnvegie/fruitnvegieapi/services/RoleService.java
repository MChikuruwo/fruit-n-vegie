package com.fruitnvegie.fruitnvegieapi.services;

import java.util.List;

public interface RoleService {
    List<Role> getAll();
    Role getOne(Integer id);
    Role findByName(String name);
}
