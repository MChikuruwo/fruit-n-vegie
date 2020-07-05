package com.fruitnvegie.fruitnvegieapi.dao;

import com.fruitnvegie.fruitnvegieapi.models.DeliveryMethods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryMethodsRepository extends JpaRepository<DeliveryMethods, Long> {
    DeliveryMethods findByDeliveryMethod(String deliveryMethod);
}
