package com.fruitnvegie.fruitnvegieapi.dto;

import com.fruitnvegie.fruitnvegieapi.models.enums.ProductSize;

public class AddCartItemsDto {
    private Integer quantity;
    private ProductSize productSize;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductSize getProductSize() {
        return productSize;
    }

    public void setProductSize(ProductSize productSize) {
        this.productSize = productSize;
    }

}
