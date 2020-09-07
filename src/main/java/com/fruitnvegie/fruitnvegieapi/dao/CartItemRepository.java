package com.fruitnvegie.fruitnvegieapi.dao;

import com.fruitnvegie.fruitnvegieapi.models.CartItem;
import com.fruitnvegie.fruitnvegieapi.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    CartItem findByProductsId(long productId);
    CartItem deleteByProductsId(long productId);
    ShoppingCart deleteAllById(CartItem cartItem);
    CartItem findByShoppingCart(long cartId);
    CartItem findAllByShoppingCart(long cartId);
}
