package com.fruitnvegie.fruitnvegieapi.dto;

public class AddDeliveryMethodsDto {
    private String deliveryMethod;
    private Long shippingCost;

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public Long getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(Long shippingCost) {
        this.shippingCost = shippingCost;
    }
}
