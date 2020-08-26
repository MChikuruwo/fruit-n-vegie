package com.fruitnvegie.fruitnvegieapi.dao.Impl;

/*import com.fruitnvegie.fruitnvegieapi.dao.CartItemRepository;
import com.fruitnvegie.fruitnvegieapi.models.CartItem;
import com.fruitnvegie.fruitnvegieapi.models.ShoppingCart;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CartItemRepoImpl implements CartItemRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public CartItemRepoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void addCartItem(CartItem cartItem) {
        sessionFactory.getCurrentSession().saveOrUpdate(cartItem);
    }

    public void removeCartItem(CartItem cartItem) {
        sessionFactory.getCurrentSession().delete(cartItem);

    }

    public void removeAllCartItems(ShoppingCart cart) {

        List<CartItem> cartItems = cart.getCartItems();

        for(CartItem cartItem : cartItems){
            removeCartItem(cartItem);
        }

    }

    public CartItem getCartItemByProductId(long productId) {

        Query query = sessionFactory.getCurrentSession().createQuery("from CartItem where products = ?");
        query.setLong(0, productId);

        CartItem cartItem = (CartItem) query.uniqueResult();
        return cartItem;
    }

}

 */

