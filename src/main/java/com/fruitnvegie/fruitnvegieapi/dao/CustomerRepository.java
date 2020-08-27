package com.fruitnvegie.fruitnvegieapi.dao;

import com.fruitnvegie.fruitnvegieapi.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Customer findCustomerByContactEmail(@Param("email") String email);
    List<Customer> findAllByActive(Boolean active);
    Customer findCustomerByFirstName(@Param("name") String name);
    Customer findByPhoneNumber(@Param("phoneNumber") String phoneNumber);

}
