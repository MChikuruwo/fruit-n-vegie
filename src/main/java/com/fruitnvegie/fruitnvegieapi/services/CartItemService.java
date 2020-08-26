package com.fruitnvegie.fruitnvegieapi.services;

import com.fruitnvegie.fruitnvegieapi.models.CartItem;
import com.fruitnvegie.fruitnvegieapi.models.ShoppingCart;

import java.util.List;

public interface CartItemService {
    String add(CartItem cartItem);
    String update(CartItem cartItem);
    String removeCartItem(long productId);
    List<CartItem> getAll();
    CartItem findByProductId(Long productId);
    String removeAllCartItems(ShoppingCart shoppingCart);
}
