package com.fruitnvegie.fruitnvegieapi.services;

import com.fruitnvegie.fruitnvegieapi.dao.ProductsRepository;
import com.fruitnvegie.fruitnvegieapi.models.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductsServiceImpl implements ProductsService {
    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsServiceImpl(ProductsRepository productsRepository){
        this.productsRepository = productsRepository;
    }

    @Override
    public String add(Products product) {
        productsRepository.save(product);
        return "Product has been successfully added";
    }

    @Override
    public String update(Products product) {
        Optional<Products> productFromDatabase = productsRepository.findById(product.getId());
        if (!productFromDatabase.isPresent()) throw new EntityNotFoundException("Product does not exist");
        productsRepository.save(product);
        return "Product with ID " + product.getId() + " has been successfully updated";
    }

    @Override
    public String delete(Long id) {
        Optional<Products> productToDelete = productsRepository.findById(id);
        if (!productToDelete.isPresent()){
            throw new EntityNotFoundException("Product with ID " + id + " does not exist");
        }
        productsRepository.deleteById(id);
        return "Product has been successfully deleted";
    }
    @Override
    public List<Products> getAllProducts() {
        return productsRepository.findAll();    }


    @Override
    public Products getOne(long id)  {
        Optional<Products> product = productsRepository.findById(id);
        if (!product.isPresent()){
            throw new EntityNotFoundException("Product with the ID " + id + " does not exist");
        }
        return product.get();
    }


    @Override
    public  Products findByProductName(String name){
        Products products = productsRepository.findByProductName(name);
        if (products == null){
            throw new EntityNotFoundException("Product " + name + " is not  available.");
        }
        return products;
    }

}
