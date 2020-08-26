package com.fruitnvegie.fruitnvegieapi.services;

import com.fruitnvegie.fruitnvegieapi.dao.CustomerRepository;
import com.fruitnvegie.fruitnvegieapi.exceptions.UserNotFoundException;
import com.fruitnvegie.fruitnvegieapi.models.Customer;
import com.fruitnvegie.fruitnvegieapi.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private  final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private  final UserService userService;
    User user;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,PasswordEncoder passwordEncoder, UserService userService) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }


    @Override
    public String add(Customer customer) {
        customerRepository.save(customer);
        return "Customer details have been successfully updated.";

    }

    @Override
    public String update(Customer user) {
        Optional<Customer> userFromDatabase = customerRepository.findById(user.getId());
        if (!userFromDatabase.isPresent()) throw new UserNotFoundException("Customer does not exist!");
        // Carry date created timestamp
        customerRepository.save(user);
        return "Customer details with ID " + user.getId() + " has been successfully updated.";
    }

    @Override
    public String delete(Long id) {
        Optional<Customer> userToDelete = customerRepository.findById(id);
        if (!userToDelete.isPresent()){
            throw new UserNotFoundException("Customer details with ID " + id + " does not exist.");
        }
        customerRepository.deleteById(id);
        return "Customer details have been successfully deleted.";
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> users = customerRepository.findAll();
        if (users.isEmpty()){
            throw new UserNotFoundException("Customers not found!");
        }
        return users;    }

    @Override
    public Customer getOne(Long id) {
        Optional<Customer> user = customerRepository.findById(id);
        if (!user.isPresent()){
            throw new UserNotFoundException("Customer details with the ID " + id + " does not exist!");
        }
        return user.get();    }

    @Override
    public Customer findByEmailAddress(String emailAddress) {
        Customer user = customerRepository.findCustomerByContactEmail(emailAddress);
        if (user == null){
            throw new EntityNotFoundException("Customer with the email: " + emailAddress + " not found!");
        }
        return user;    }

    @Override
    public Customer findCustomerByName(String name) {
        Customer user = customerRepository.findCustomerByFirstName(name);
        if (user == null){
            throw new EntityNotFoundException("Customer details with the email: " + name + " not found!");
        }
        return user;    }
}
