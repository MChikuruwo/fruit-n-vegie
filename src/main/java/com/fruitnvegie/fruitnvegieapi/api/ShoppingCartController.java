package com.fruitnvegie.fruitnvegieapi.api;

import com.fruitnvegie.fruitnvegieapi.dto.AddShoppingCartDto;
import com.fruitnvegie.fruitnvegieapi.models.Customer;
import com.fruitnvegie.fruitnvegieapi.models.ShoppingCart;
import com.fruitnvegie.fruitnvegieapi.models.api.ApiResponse;
import com.fruitnvegie.fruitnvegieapi.services.CartItemService;
import com.fruitnvegie.fruitnvegieapi.services.CustomerService;
import com.fruitnvegie.fruitnvegieapi.services.OrderService;
import com.fruitnvegie.fruitnvegieapi.services.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/shopping",produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/api/v1/shopping", produces = MediaType.APPLICATION_JSON_VALUE)
public class ShoppingCartController {

    private final CustomerService customerService;
    private final ShoppingCartService shoppingCartService;
    private final CartItemService cartItemService;
    private final OrderService customerOrderService;
    private final ModelMapper modelMapper;

    @Autowired
    public ShoppingCartController(CustomerService customerService, ShoppingCartService shoppingCartService, CartItemService cartItemService,OrderService customerOrderService,ModelMapper modelMapper){
        this.customerService = customerService;
        this.shoppingCartService = shoppingCartService;
        this.cartItemService = cartItemService;
        this.customerOrderService = customerOrderService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "/cart/{customer-id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get cart as per user session, ready to be added items. Takes CustomerId as path variable.", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse getCart(@RequestBody AddShoppingCartDto addShoppingCartDto,
                               @PathVariable("customer-id") Long customerId){
        ShoppingCart cart = modelMapper.map(addShoppingCartDto,ShoppingCart.class);
        Customer customer = customerService.getOne(customerId);
        cart.setCustomer(customerService.getOne(customerId));

        cart.setConfirmed(true);
        //cart.setCustomer(customer);

        //long cartId = customer.getShoppingCart().getId();

         customer.setShoppingCart(cart);

         //double grandTotal = customerOrderService.getCustomerOrderGrandTotal(customer.getShoppingCart().getId());

       //cart.setSubTotal(grandTotal);


        return new ApiResponse(200,"SUCCESS",shoppingCartService.addCart(cart));
    }

    @RequestMapping(value = "/{cartId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Redirect cart.", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCartRedirect(@PathVariable(value="cartId") long cartId, Model model){
        model.addAttribute("cartId", cartId);

        return "cart";

    }
}
