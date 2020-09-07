package com.fruitnvegie.fruitnvegieapi.api;

import com.fruitnvegie.fruitnvegieapi.dto.AddCustomerDto;
import com.fruitnvegie.fruitnvegieapi.dto.UpdateCustomerDto;
import com.fruitnvegie.fruitnvegieapi.models.Customer;
import com.fruitnvegie.fruitnvegieapi.models.ShoppingCart;
import com.fruitnvegie.fruitnvegieapi.models.User;
import com.fruitnvegie.fruitnvegieapi.models.api.ApiResponse;
import com.fruitnvegie.fruitnvegieapi.services.CustomerService;
import com.fruitnvegie.fruitnvegieapi.services.ShippingDetailsService;
import com.fruitnvegie.fruitnvegieapi.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/customer", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/api/v1/customer", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {
    private final UserService userService;
    private  final CustomerService customerService;
    private final ShippingDetailsService shippingDetailsService;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerController(UserService userService, CustomerService customerService, ShippingDetailsService shippingDetailsService, ModelMapper modelMapper){
        this.customerService = customerService;
        this.userService = userService;
        this.shippingDetailsService = shippingDetailsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get all customer details.", response = ApiResponse.class)
    public ApiResponse getAllCustomers(){
        return new ApiResponse(200, "SUCCESS", customerService.getAll());
    }

    @GetMapping("/{customer-id}")
    @ApiOperation(value = "Get a single customer details entity by their ID", response = ApiResponse.class)
    public ApiResponse getOneCustomer(@PathVariable("customer-id") Long customerId) {
        return new ApiResponse(200, "SUCCESS", customerService.getOne(customerId));
    }

    @DeleteMapping(value = "/delete/{customer-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete a customer details entity by their ID", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse deleteCustomer(@PathVariable("customer-id") Long customerId){
        return new ApiResponse(200, "SUCCESS", customerService.delete(customerId));
    }
    @PostMapping(value = "/add-details/{user-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add customer details to the fruit'n'vegie platform taking user-id as path variable.", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse addCustomer(@RequestBody AddCustomerDto addCustomerDto, @PathVariable("user-id") Integer userId, HttpServletRequest request) {
        Customer customer = modelMapper.map(addCustomerDto, Customer.class);
        customer.setUser(userService.getOne(userId));
        User user = userService.getOne(userId);
        //set active to true by default
        customer.setActive(true);

        //fields to be collected from current user
        customer.setPassword(user.getPassword());
        customer.setContactEmail(user.getEmailAddress());

        //set customerId to a new user
        user.setCustomerId(customer.getId());

        ShoppingCart newCart = new ShoppingCart();
        newCart.setCustomer(customer);

        return new ApiResponse(200, "SUCCESS", customerService.add(customer));
    }
    @PutMapping(value = "/edit-customer/{customer-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Updates a current customer's details", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse updateCustomer(@RequestBody UpdateCustomerDto updateCustomerDto, @PathVariable("customer-id") Long customerId){
        customerService.getOne(customerId);
        Customer customer = modelMapper.map(updateCustomerDto, Customer.class);
        return new ApiResponse(200, "SUCCESS", customerService.update(customer));
    }

}
