package com.fruitnvegie.fruitnvegieapi.api;

import com.fruitnvegie.fruitnvegieapi.models.*;
import com.fruitnvegie.fruitnvegieapi.models.api.ApiResponse;
import com.fruitnvegie.fruitnvegieapi.services.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/order",produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "api/v1/order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    private final ShoppingCartService cartService;
    private final OrderService customerOrderService;
    private final ShippingDetailsService shippingDetailsService;
    private final CustomerService customerService;
    private  final DeliveryMethodsService deliveryMethodsService;

    @Autowired
    public OrderController(ShoppingCartService cartService, OrderService customerOrderService, ShippingDetailsService shippingDetailsService, CustomerService customerService,DeliveryMethodsService deliveryMethodsService) {
        this.cartService = cartService;
        this.customerOrderService = customerOrderService;
        this.shippingDetailsService = shippingDetailsService;
        this.customerService = customerService;
        this.deliveryMethodsService = deliveryMethodsService;

    }


    @PostMapping(value = "/{cartId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a customer order request,sending the cartId as path variable.", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse createOrder(@RequestBody CustomerOrder order,
                                   //@PathVariable("customer-id") long customerId,
                                   @PathVariable("cartId") long cartId
                                   /*@PathVariable("shipping-details-id") long shippingDetailsId*/) {

        //order.setCustomer(customerService.getOne(customerId));
        order.setShoppingCart(cartService.getCartById(cartId));
        ShoppingCart cart = cartService.getCartById(cartId);
        //order.setShippingDetails(shippingDetailsService.getOne(shippingDetailsId));
        DeliveryMethods deliveryMethod = deliveryMethodsService.findByDeliveryMethod("Normal Delivery");

        Customer customer = cart.getCustomer();
        order.setCustomer(customer);
        order.setShippingDetails(customer.getShippingDetails());
        // Generate the order number for a customer
        String orderNum = generateOrderNum(order.getOrderNum());

        // Set the order to the generated order number
        order.setOrderNum(orderNum);

        order.setTotal(cart.getSubTotal()+deliveryMethod.getShippingCost());

        //TODO: Add email logic to send the checkout to the customer
        return new ApiResponse(200,"SUCCESS", customerOrderService.addCustomerOrder(order));
    }

    //generated order number to be sent to the customer
    private String generateOrderNum(String orderNum) {
        String generateOrderNum;
        // Generate random number to be used as the order number
        Random randomNumber = new Random();
        int n = 1000 + randomNumber.nextInt(9999);
        generateOrderNum = (String.valueOf(n));
        return generateOrderNum;

    }
}