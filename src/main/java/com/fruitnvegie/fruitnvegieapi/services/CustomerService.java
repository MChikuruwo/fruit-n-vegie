package com.fruitnvegie.fruitnvegieapi.services;

import com.fruitnvegie.fruitnvegieapi.models.Customer;

import java.util.List;

public interface CustomerService {
    String add(Customer customer);
    String update(Customer user);
    String delete(Long id);
    List<Customer> getAll();
    Customer getOne(Long id);
    Customer findByEmailAddress(String emailAddress);
    Customer findCustomerByName(String name);


}
