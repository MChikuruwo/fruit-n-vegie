package com.fruitnvegie.fruitnvegieapi.exceptions;

import com.fruitnvegie.fruitnvegieapi.models.Products;

public class NotEnoughProductsInStockException extends Exception {

    private static final String DEFAULT_MESSAGE = "Not enough products in stock";

    public NotEnoughProductsInStockException() {
        super(DEFAULT_MESSAGE);
    }

    public NotEnoughProductsInStockException(Products product) {
        super(String.format("Not enough %s products in stock. Only %d left", product.getProductName()));
    }

}