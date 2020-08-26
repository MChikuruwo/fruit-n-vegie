package com.fruitnvegie.fruitnvegieapi.services;

import com.fruitnvegie.fruitnvegieapi.models.ShoppingCart;

public interface ShoppingCartService {

    String addCart(ShoppingCart cart);

    ShoppingCart getCartById(long cartId);

    String update(ShoppingCart cart);

}
