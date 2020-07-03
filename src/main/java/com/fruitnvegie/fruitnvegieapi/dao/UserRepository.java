package com.fruitnvegie.fruitnvegieapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByEmailAddress(@Param("email") String email);
    List<User> findAllByActive(Boolean active);
}
