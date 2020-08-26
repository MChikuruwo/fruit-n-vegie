package com.fruitnvegie.fruitnvegieapi.services;

import com.fruitnvegie.fruitnvegieapi.dao.ShoppingCartRepository;
import com.fruitnvegie.fruitnvegieapi.models.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@Transactional
public class ShoppingCartServiceImpl implements  ShoppingCartService {

    private ShoppingCartRepository cartDao;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepository cartDao){
        this.cartDao = cartDao;
    }

    @Override
    public String addCart(ShoppingCart cart) {
        cartDao.save(cart);
        return "Shopping Cart has been successfully added";
    }

    public ShoppingCart getCartById(long cartId) {

        return cartDao.getCartById(cartId);
    }

    public String update(ShoppingCart cart) {

        Optional<ShoppingCart> userFromDatabase = cartDao.findById(cart.getId());
        if (!userFromDatabase.isPresent()) throw new EntityNotFoundException("Shopping Cart does not exist!");
        // Carry date created timestamp
        cart.setDateCreated(userFromDatabase.get().getDateCreated());
        cartDao.save(cart);
        return "Shopping Cart with ID :" + cart.getId() + " has been successfully updated.";
    }

}
