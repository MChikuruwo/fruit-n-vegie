package com.fruitnvegie.fruitnvegieapi.services;


import com.fruitnvegie.fruitnvegieapi.models.ShippingDetails;

import java.util.List;

public interface ShippingDetailsService {

    String add(ShippingDetails shippingDetails);
    String update(ShippingDetails shippingDetails);
    String delete(Long id);
    List<ShippingDetails> getAll();
    ShippingDetails getOne(Long id);

    ShippingDetails findByUserId(Integer userId);
    List<ShippingDetails> findAllByCity(String city);
    List<ShippingDetails> findAllByCountry(String country);
    List<ShippingDetails> findAllByDeliveryMethods(String deliveryMethods);
    List<ShippingDetails> findAllByPaymentMethods(String paymentMethods);
}