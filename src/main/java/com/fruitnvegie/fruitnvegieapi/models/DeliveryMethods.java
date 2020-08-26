package com.fruitnvegie.fruitnvegieapi.models;

import javax.persistence.*;

@Entity
@Table(name = "delivery_methods", schema = "fruit_n_vegie_shopping_cart")
public class DeliveryMethods {
    private Long id;
    private String deliveryMethod;
    private Double shippingCost;

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
    @Column(name = "delivery_method")
    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    @Basic
    @Column(name = "shipping_cost")
    public Double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(Double shippingCost) {
        this.shippingCost = shippingCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeliveryMethods that = (DeliveryMethods) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (deliveryMethod != null ? !deliveryMethod.equals(that.deliveryMethod) : that.deliveryMethod != null)
            return false;
        if (shippingCost != null ? !shippingCost.equals(that.shippingCost) : that.shippingCost != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (deliveryMethod != null ? deliveryMethod.hashCode() : 0);
        result = 31 * result + (shippingCost != null ? shippingCost.hashCode() : 0);
        return result;
    }
}
