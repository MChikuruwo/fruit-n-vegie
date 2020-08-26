package com.fruitnvegie.fruitnvegieapi.api;

import com.fruitnvegie.fruitnvegieapi.dao.CustomerRepository;
import com.fruitnvegie.fruitnvegieapi.dto.AddCartItemsDto;
import com.fruitnvegie.fruitnvegieapi.models.CartItem;
import com.fruitnvegie.fruitnvegieapi.models.Products;
import com.fruitnvegie.fruitnvegieapi.models.ShoppingCart;
import com.fruitnvegie.fruitnvegieapi.models.api.ApiResponse;
import com.fruitnvegie.fruitnvegieapi.services.CartItemService;
import com.fruitnvegie.fruitnvegieapi.services.OrderService;
import com.fruitnvegie.fruitnvegieapi.services.ProductsService;
import com.fruitnvegie.fruitnvegieapi.services.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/cart-items",produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/api/v1/cart-items",produces = MediaType.APPLICATION_JSON_VALUE)
    public class CartResourcesController {

    private final ShoppingCartService cartService;
    private final CustomerRepository customerDao;
    private final ProductsService productService;
    private final CartItemService cartItemService;
    private final OrderService orderService;
    private final ModelMapper modelMapper;

    @Autowired
    public CartResourcesController(ShoppingCartService cartService, CustomerRepository customerDao, ProductsService productsService, CartItemService cartItemService,OrderService orderService, ModelMapper modelMapper) {
        this.cartService = cartService;
        this.customerDao = customerDao;
        this.productService = productsService;
        this.cartItemService = cartItemService;
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }


    @RequestMapping(value = "/{cartId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Shopping cart by taking cartId as path variable", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ShoppingCart getCartById(@PathVariable(value = "cartId") long cartId) {

        return cartService.getCartById(cartId);
    }

    @PutMapping(value = "/addItems/{cart-id}/{product-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add items to a selected cart, taking cartId & productId as path variable.", produces = MediaType.APPLICATION_JSON_VALUE)
    //@ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ApiResponse addItem(@PathVariable(value = "cart-id") long cartId,
                               @PathVariable(value = "product-id") long productId,
                               @RequestBody AddCartItemsDto addCartItemsDto) {

        CartItem item = modelMapper.map(addCartItemsDto, CartItem.class);
        item.setShoppingCart(cartService.getCartById(cartId));
        ShoppingCart cart = cartService.getCartById(cartId);
        Products product = productService.getOne(productId);


        List<CartItem> cartItems = cart.getCartItems();
        for (int i = 0; i < cartItems.size(); i++) {
            if (product.getId() == cartItems.get(i).getProducts().getId()) {
                CartItem cartItem = cartItems.get(i);
                cartItem.setQuantity(item.getQuantity());
                cartItem.setProductPrice(product.getProductPrice());
                cartItem.setSubTotal(product.getProductPrice() * cartItem.getQuantity());
                //cartItem.setProductSize(item.getProductSize());

                //set shopping cart sub total
                cart.setSubTotal(orderService.getCustomerOrderGrandTotal(cartId));

                return new ApiResponse(200, "SUCCESS", cartItemService.add(cartItem));
            }
        }

            CartItem cartItem = new CartItem();
            cartItem.setProducts(product);
            cartItem.setQuantity(item.getQuantity());
            cartItem.setProductPrice(product.getProductPrice());
            cartItem.setSubTotal(product.getProductPrice() * cartItem.getQuantity());
            //cartItem.setProductSize(item.getProductSize());

           //set shopping cart sub total
           cart.setSubTotal(orderService.getCustomerOrderGrandTotal(cartId));

        return new ApiResponse(200, "SUCCESS", cartItemService.add(cartItem));
    }





        @DeleteMapping(value= "/remove/{cart-id}/{product-id}", produces = MediaType.APPLICATION_JSON_VALUE)
        @ApiOperation(value = "Remove a product from cart by taking the productId as path variable.",produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseStatus(value = HttpStatus.NO_CONTENT)
        public void removeItem(@PathVariable(value="cart-id") long cartId,
                                @PathVariable(value="product-id") long productId){
            CartItem cartItem = cartItemService.findByProductId(productId);
            cartItem.setShoppingCart(cartService.getCartById(cartId));
           // cartItem.setProducts(cartItemService.findByProductId(productService.getOne(productId)));
            cartItemService.removeCartItem(productId);
        }


        @DeleteMapping(value = "/{cart-Id}",produces = MediaType.APPLICATION_JSON_VALUE)
        @ApiOperation(value = "Erase all entries of a shopping cart and delete the shopping cart as well.", produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseStatus(value = HttpStatus.NO_CONTENT)
        public void clearCart(@PathVariable(value = "cart-Id") long cartId){
            cartItemService.removeAllCartItems(cartService.getCartById(cartId));
        }


        @ExceptionHandler(IllegalArgumentException.class)
        @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Illegal request, Please check your data")
        public void handleClientErrors(Exception e){}

        @ExceptionHandler(Exception.class)
        @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server Error")
        public void handleServerErrors(Exception e){}
    }

