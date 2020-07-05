package com.fruitnvegie.fruitnvegieapi.dao;

import com.fruitnvegie.fruitnvegieapi.models.ShippingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShippingDetailsRepository extends JpaRepository<ShippingDetails,Long> {
    ShippingDetails findByUserId(Integer userId);
    List<ShippingDetails> findAllByCity(String city);
    List<ShippingDetails> findAllByCountry(String country);
    List<ShippingDetails> findAllByDeliveryMethods(String deliveryMethods);
    List<ShippingDetails> findAllByPaymentMethods(String paymentMethods);
}