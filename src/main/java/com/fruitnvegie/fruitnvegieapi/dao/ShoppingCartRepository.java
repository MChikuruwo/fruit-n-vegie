package com.fruitnvegie.fruitnvegieapi.dao;

import com.fruitnvegie.fruitnvegieapi.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    ShoppingCart getCartById(long cartId) ;

}
