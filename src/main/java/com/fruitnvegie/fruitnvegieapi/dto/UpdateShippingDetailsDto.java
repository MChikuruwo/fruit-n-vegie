package com.fruitnvegie.fruitnvegieapi.dto;

import com.fruitnvegie.fruitnvegieapi.models.AvailableCountries;
import com.fruitnvegie.fruitnvegieapi.models.DeliveryMethods;
import com.fruitnvegie.fruitnvegieapi.models.PaymentMethods;
import com.fruitnvegie.fruitnvegieapi.models.User;

public class UpdateShippingDetailsDto {
    private Long id;
    private String address;
    private String city;
    private String country;
    private Long zipPostalCode;
    private User user;
    private DeliveryMethods deliveryMethods;
    private PaymentMethods paymentMethods;
    private AvailableCountries availableCountries;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public AvailableCountries getAvailableCountries() {
        return availableCountries;
    }

    public void setAvailableCountries(AvailableCountries availableCountries) {
        this.availableCountries = availableCountries;
    }
}
