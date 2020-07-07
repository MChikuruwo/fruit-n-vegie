package com.fruitnvegie.fruitnvegieapi.dto;

import com.fruitnvegie.fruitnvegieapi.models.DeliveryMethods;
import com.fruitnvegie.fruitnvegieapi.models.PaymentMethods;

public class AddShippingDetailsDto {
    private String address;
    private String city;
    private Long zipPostalCode;



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

    public Long getZipPostalCode() {
        return zipPostalCode;
    }

    public void setZipPostalCode(Long zipPostalCode) {
        this.zipPostalCode = zipPostalCode;
    }


}
