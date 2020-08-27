package com.fruitnvegie.fruitnvegieapi.api;

import com.fruitnvegie.fruitnvegieapi.models.Customer;
import com.fruitnvegie.fruitnvegieapi.models.CustomerOrder;
import com.fruitnvegie.fruitnvegieapi.models.DeliveryMethods;
import com.fruitnvegie.fruitnvegieapi.models.ShoppingCart;
import com.fruitnvegie.fruitnvegieapi.models.api.ApiResponse;
import com.fruitnvegie.fruitnvegieapi.services.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    private  final  CartItemService cartItemService;
    private final EmailService emailService;

    @Autowired
    public OrderController(ShoppingCartService cartService, OrderService customerOrderService, ShippingDetailsService shippingDetailsService, CustomerService customerService,DeliveryMethodsService deliveryMethodsService,CartItemService cartItemService, EmailService emailService) {
        this.cartService = cartService;
        this.customerOrderService = customerOrderService;
        this.shippingDetailsService = shippingDetailsService;
        this.customerService = customerService;
        this.deliveryMethodsService = deliveryMethodsService;
        this.cartItemService = cartItemService;
        this.emailService = emailService;

    }


    @PostMapping(value = "/{cartId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a customer order request,sending the cartId as path variable.", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse createOrder(@RequestBody CustomerOrder order,
                                   //@PathVariable("customer-id") long customerId,
                                   @PathVariable("cartId") long cartId, HttpServletRequest request) {

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

        // Send a confirmation email message
        String appUrl = request.getScheme() + "://" + request.getServerName() + request.getContextPath();

        SimpleMailMessage orderEmail = new SimpleMailMessage();
        orderEmail.setTo(customer.getContactEmail());
        orderEmail.setSubject("fruit'n'vegie.Delivery");
        orderEmail.setText("We've received your order.\n" +
                "We'll be in touch soon to arrange payment and delivery.\n" +
                "Order No." + orderNum + "\tPlaced on "+order.getDateOrdered()+"\n" +
                "Offline Payment\n" +
                "\n" +
                "Pay when your box is delivered\n" +
                "\t\n" +
                "Shipping Information\n" +
                "Standard Delivery (30 minutes to 3 hours)\n" +
                customer.getFirstName() +" "+ customer.getLastName() + "\n"+
                customer.getShippingDetails().getAddress() + "\n" +
                customer.getShippingDetails().getCity()+"," + customer.getShippingDetails().getZipPostalCode()+"\n" +
                customer.getShippingDetails().getAvailableCountries().getCountry() + "\n" +
                customer.getPhoneNumber() + "\n" +
                customer.getContactEmail() + "\n" +
                "Order Summary\n" +
                "\t\n" +
                cartItemService.getAll().iterator().toString() +"\n" +
                "Subtotal \tLE"+ cart.getSubTotal() + "\n" +
                "Shipping \tLE"+deliveryMethod.getShippingCost() + "\n" +
                "Tax \tLE0.00\n" +
                "Total \tLE"+ order.getTotal() +"\n" +
                "Need Assistance? Contact us.\n" +
                "We'll do everything we can to make sure you have a great experience with us.\n" +
                "Call us: +23234723965\n" +
                "This email was sent by fruit'n'vegie.delivery\n" +
                "https://www.fruitnvegie.com/ ");
        orderEmail.setFrom("mchikuruwo@hotmail.com");

        emailService.sendEmail(orderEmail);

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