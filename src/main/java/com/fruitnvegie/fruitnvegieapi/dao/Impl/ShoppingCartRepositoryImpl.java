package com.fruitnvegie.fruitnvegieapi.dao.Impl;

/*import com.fruitnvegie.fruitnvegieapi.dao.ShoppingCartRepository;
import com.fruitnvegie.fruitnvegieapi.models.ShoppingCart;
import com.fruitnvegie.fruitnvegieapi.services.OrderService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Repository
@Transactional
public class ShoppingCartRepositoryImpl implements ShoppingCartRepository {

    private SessionFactory sessionFactory;
    private OrderService customerOrderService;

    @Autowired
    public ShoppingCartRepositoryImpl(SessionFactory sessionFactory, OrderService customerOrderService){
        this.sessionFactory = sessionFactory;
        this.customerOrderService = customerOrderService;
    }


    @Override
    public ShoppingCart getCartById(long cartId) {
        return (ShoppingCart) sessionFactory.getCurrentSession().get(ShoppingCart.class,cartId);    }

    @Override
    public ShoppingCart validate(long cartId) throws IOException {
        ShoppingCart cart = getCartById(cartId);

        if(cart == null || cart.getCartItems().size() == 0){
            throw new IOException(cartId + "");
        }

        update(cart);
        return cart;    }

    @Override
    public void update(ShoppingCart cart) {

        long cartId = cart.getId();

        double grandTotal = customerOrderService.getCustomerOrderGrandTotal(cartId);

        cart.setSubTotal(grandTotal);

        sessionFactory.getCurrentSession().saveOrUpdate(cart);
    }
}

 */
