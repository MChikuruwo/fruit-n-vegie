package com.fruitnvegie.fruitnvegieapi.dto;

import com.fruitnvegie.fruitnvegieapi.models.DeliveryMethods;
import com.fruitnvegie.fruitnvegieapi.models.PaymentMethods;

public class AddShippingDetailsDto {
    private String address;
    private String city;
    private String country;
    private Long zipPostalCode;
    private DeliveryMethods deliveryMethods;
    private PaymentMethods paymentMethods;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getZipPostalCode() {
        return zipPostalCode;
    }

    public void setZipPostalCode(Long zipPostalCode) {
        this.zipPostalCode = zipPostalCode;
    }

    public DeliveryMethods getDeliveryMethods() {
        return deliveryMethods;
    }

    public void setDeliveryMethods(DeliveryMethods deliveryMethods) {
        this.deliveryMethods = deliveryMethods;
    }

    public PaymentMethods getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(PaymentMethods paymentMethods) {
        this.paymentMethods = paymentMethods;
    }
}
