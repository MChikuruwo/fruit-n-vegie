package com.fruitnvegie.fruitnvegieapi.dao;

import com.fruitnvegie.fruitnvegieapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface UserRoleRepository extends JpaRepository<User, Integer> {
}
