package com.fruitnvegie.fruitnvegieapi.services;

import com.fruitnvegie.fruitnvegieapi.dao.OrderRepository;
import com.fruitnvegie.fruitnvegieapi.dao.ShoppingCartRepository;
import com.fruitnvegie.fruitnvegieapi.models.CartItem;
import com.fruitnvegie.fruitnvegieapi.models.CustomerOrder;
import com.fruitnvegie.fruitnvegieapi.models.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private OrderRepository customerOrderDao;
    private ShoppingCartRepository cartDao;

    @Autowired
    @Lazy
    public OrderServiceImpl(OrderRepository customerOrderDao,ShoppingCartRepository cartDao){
        this.customerOrderDao = customerOrderDao;
        this.cartDao = cartDao;

    }
    public String addCustomerOrder(CustomerOrder customerOrder) {
       // Optional<CustomerOrder> detailsFromDatabase = Optional.ofNullable(customerOrderDao.findByShoppingCart(customerOrder.getShoppingCart().getId()));
       // if (detailsFromDatabase.isPresent())throw new OrderAlreadyProcessedException("Order has been already processed, please kindly check your e-mail.");
        customerOrderDao.save(customerOrder);
        return "Customer Order has been successfully processed";

    }

    public double getCustomerOrderGrandTotal(long cartId) {

        double grandTotal = 0;

        ShoppingCart cart = cartDao.getCartById(cartId);

        for(CartItem c: cart.getCartItems()){
            grandTotal += c.getSubTotal();
        }

        return grandTotal;
    }
}
