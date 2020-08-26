package com.fruitnvegie.fruitnvegieapi.services;

import com.fruitnvegie.fruitnvegieapi.models.Products;

import java.util.List;

public interface ProductsService {
    String add(Products products);
    String update(Products products);
    String delete(Long id);
    List<Products> getAllProducts();
    Products getOne(long id);
    Products findByProductName(String name);

}
