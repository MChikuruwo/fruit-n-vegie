package com.fruitnvegie.fruitnvegieapi.services;

import com.fruitnvegie.fruitnvegieapi.models.PaymentMethods;

import java.util.List;

public interface PaymentMethodsService {
    String add(PaymentMethods paymentMethods);
    String update(PaymentMethods paymentMethods);
    String delete(Long id);
    List<PaymentMethods> getAll();
    PaymentMethods getOne(Long id);
    PaymentMethods findByPaymentMethod(String paymentMethod);

}
