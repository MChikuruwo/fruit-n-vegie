package com.fruitnvegie.fruitnvegieapi.services;

import com.fruitnvegie.fruitnvegieapi.dao.CartItemRepository;
import com.fruitnvegie.fruitnvegieapi.models.CartItem;
import com.fruitnvegie.fruitnvegieapi.models.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartItemServiceImpl implements CartItemService {

    private CartItemRepository cartItemDao;
    private  ProductsService productsService;

    @Autowired
    public CartItemServiceImpl(CartItemRepository cartItemDao, ProductsService productsService) {
        this.cartItemDao = cartItemDao;
        this.productsService = productsService;
    }

    @Override
    public String add(CartItem cartItem) {

        cartItemDao.save(cartItem);
        return "Item has been successfully added.";
    }

    @Override
    public String update(CartItem cartItem) {
        Optional<CartItem> cartItemsFromDatabase = cartItemDao.findById(cartItem.getId());
        if (!cartItemsFromDatabase.isPresent()) throw new EntityNotFoundException("Cart Item does not exist.");
        // Carry date created timestamp
        //shippingDetails.setDateCreated(shippingDetailsFromDatabase.get().getDateCreated());
        cartItemDao.save(cartItem);
        return "Cart Items with ID " + cartItem.getId() + " have been updated.";    }

    @Override
    public String removeCartItem(long productId) {
        Optional<CartItem> productToDelete = Optional.ofNullable(cartItemDao.findByProductsId(productId));
        if (!productToDelete.isPresent()){
            throw new EntityNotFoundException("Product with Id :" + productId + " does not exist in cart.");
        }
        cartItemDao.deleteByProductsId(productId);
        return "Product has been successfully deleted.";    }

    @Override
    public List<CartItem> getAll() {
        List<CartItem> cartItems = cartItemDao.findAll();
        if (cartItems.isEmpty()){
            throw new EntityNotFoundException("Items not found.");
        }
        return cartItems;    }

    @Override
    public CartItem findByProductId(Long productId) {
        CartItem cartItem = cartItemDao.findByProductsId(productId);
        if (cartItem == null){
            throw new EntityNotFoundException("Product: " + productId.toString() + " not found.");
        }
        return cartItem;    }

    @Override
    public String removeAllCartItems(ShoppingCart shoppingCart) {
        List<CartItem> cartItems = shoppingCart.getCartItems();

        for(CartItem cartItem : cartItems){
            cartItemDao.deleteAllById(cartItem);
        }
        return "Cart Items have been successfully deleted";
    }

    @Override
    public CartItem getAllItemsInCart(long cartId) {
        Optional<CartItem> productsInCart = Optional.ofNullable(cartItemDao.findByShoppingCart(cartId));
        if (!productsInCart.isPresent()){
            throw new EntityNotFoundException("Items in cart with Id :" + cartId + " not found!");
        }
        return  cartItemDao.findAllByShoppingCart(cartId);}

}
