package com.fruitnvegie.fruitnvegieapi.services;

import com.fruitnvegie.fruitnvegieapi.models.CustomerOrder;

public interface OrderService {
    String addCustomerOrder(CustomerOrder customerOrder);

    double getCustomerOrderGrandTotal(long cartId);
}
