package com.fruitnvegie.fruitnvegieapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fruitnvegie.fruitnvegieapi.models.enums.ProductSize;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cart_items", schema = "fruit_n_vegie_shopping_cart")
public class CartItem implements Serializable {
    private Long id;
    private Integer quantity;
    private Double productPrice;
    private ProductSize productSize;
    private Double subTotal;
    private ShoppingCart shoppingCart;
    private Products products;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "product_price")
    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    @Basic
    @Column(name = "product_size")
    public ProductSize getProductSize() {
        return productSize;
    }

    public void setProductSize(ProductSize productSize) {
        this.productSize = productSize;
    }

    @Basic
    @Column(name = "sub_total")
    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartItem that = (CartItem) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (productPrice != null ? !productPrice.equals(that.productPrice) : that.productPrice != null) return false;
        if (productSize != null ? !productSize.equals(that.productSize) : that.productSize != null) return false;
        if (subTotal != null ? !subTotal.equals(that.subTotal) : that.subTotal != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (productPrice != null ? productPrice.hashCode() : 0);
        result = 31 * result + (productSize != null ? productSize.hashCode() : 0);
        result = 31 * result + (subTotal != null ? subTotal.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCartByCartId) {
        this.shoppingCart = shoppingCartByCartId;
    }

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    public Products getProducts() {
        return products;
    }

    public void setProducts(Products productsByProductId) {
        this.products = productsByProductId;
    }
}
