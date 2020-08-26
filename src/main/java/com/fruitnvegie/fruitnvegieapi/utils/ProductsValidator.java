package com.fruitnvegie.fruitnvegieapi.utils;

import com.fruitnvegie.fruitnvegieapi.models.Products;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductsValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Products.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Products product = (Products) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name","error.not_empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.not_empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "error.not_empty");

        // Name must have from 2 characters to 32
        if (product.getProductName().length() <= 1) {
            errors.rejectValue("name", "product.error.name.less_2");
        }
        if (product.getProductName().length() > 50) {
            errors.rejectValue("name", "product.error.name.over_32");
        }

        // Quantity must be greater than zero for a product to be available in stock
        if (product.getQuantity()<=0){
            product.setInStock(Boolean.FALSE);
            errors.rejectValue("quantity", "product.error.quantity = null");
        }
        else {
            product.setInStock(Boolean.TRUE);
        }
    }
}

