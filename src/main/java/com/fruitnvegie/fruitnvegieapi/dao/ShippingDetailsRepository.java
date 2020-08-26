package com.fruitnvegie.fruitnvegieapi.dao;

import com.fruitnvegie.fruitnvegieapi.models.DeliveryMethods;
import com.fruitnvegie.fruitnvegieapi.models.PaymentMethods;
import com.fruitnvegie.fruitnvegieapi.models.ShippingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShippingDetailsRepository extends JpaRepository<ShippingDetails,Long> {
    ShippingDetails findByCustomerId(Long customerId);
    ShippingDetails findByAvailableCountriesId(Long countryId);
    List<ShippingDetails> findAllByCity(String city);
    //List<ShippingDetails> findAllByCountry(String country);
    List<ShippingDetails> findAllByDeliveryMethods(DeliveryMethods deliveryMethods);
    List<ShippingDetails> findAllByPaymentMethods(PaymentMethods paymentMethods);
}
