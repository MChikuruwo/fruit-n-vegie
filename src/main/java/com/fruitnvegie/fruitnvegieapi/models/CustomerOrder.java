package com.fruitnvegie.fruitnvegieapi.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "orders", schema = "fruit_n_vegie_shopping_cart")
public class CustomerOrder {
    private Long id;
    private String orderNum;
    private Double total;
    private Timestamp dateOrdered;
    private Customer customer;
    private ShoppingCart shoppingCart;
    private ShippingDetails shippingDetails;

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
    @Column(name = "order_num")
    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    @Basic
    @Column(name = "total")
    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Basic
    @CreationTimestamp
    @Column(name = "date_ordered")
    public Timestamp getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(Timestamp dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerOrder that = (CustomerOrder) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (orderNum != null ? !orderNum.equals(that.orderNum) : that.orderNum != null) return false;
        if (total != null ? !total.equals(that.total) : that.total != null) return false;
        if (dateOrdered != null ? !dateOrdered.equals(that.dateOrdered) : that.dateOrdered != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (orderNum != null ? orderNum.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (dateOrdered != null ? dateOrdered.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "id=" + id +
                ", orderNum='" + orderNum + '\'' +
                ", total=" + total +
                ", dateOrdered=" + dateOrdered +
                ", customer=" + customer +
                ", shoppingCart=" + shoppingCart +
                ", shippingDetails=" + shippingDetails +
                '}';
    }

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    @OneToOne
    @JoinColumn(name = "shopping_cart_id", referencedColumnName = "id", nullable = false)
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCartByShoppingCartId) {
        this.shoppingCart = shoppingCartByShoppingCartId;
    }

    @OneToOne
    @JoinColumn(name = "shipping_details_id", referencedColumnName = "id", nullable = false)
    public ShippingDetails getShippingDetails() {
        return shippingDetails;
    }

    public void setShippingDetails(ShippingDetails shippingDetailsByShippingDetailsId) {
        this.shippingDetails = shippingDetailsByShippingDetailsId;
    }
}
