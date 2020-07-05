package com.fruitnvegie.fruitnvegieapi.services;

import com.fruitnvegie.fruitnvegieapi.models.DeliveryMethods;

import java.util.List;

public interface DeliveryMethodsService {
    String add(DeliveryMethods deliveryMethods);
    String update(DeliveryMethods deliveryMethods);
    String delete(Long id);
    List<DeliveryMethods> getAll();
    DeliveryMethods getOne(Long id);
    DeliveryMethods findByDeliveryMethod(String deliveryMethod);

}
