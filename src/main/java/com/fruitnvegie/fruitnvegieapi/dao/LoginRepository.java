package com.fruitnvegie.fruitnvegieapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {
    List<Login> findAllByUser(User user);
    List<Login> findAllByDate(Date date);
}
