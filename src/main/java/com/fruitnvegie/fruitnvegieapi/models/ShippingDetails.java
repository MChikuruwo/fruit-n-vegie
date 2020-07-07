package com.fruitnvegie.fruitnvegieapi.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "shipping_details", schema = "fruit_n_veg_shopping_cart")
public class ShippingDetails {
    private Long id;
    private String address;
    private String city;
    private String zipPostalCode;
    private Timestamp dateCreated;
    private Timestamp dateUpdated;
    private User user;
    private AvailableCountries availableCountries;
    private DeliveryMethods deliveryMethods;
    private PaymentMethods paymentMethods;

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
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "postal_code")
    public String getZipPostalCode() {
        return zipPostalCode;
    }

    public void setZipPostalCode(String zipPostalCode) {
        this.zipPostalCode = zipPostalCode;
    }

    @Basic
    @CreationTimestamp
    @Column(name = "date_created")
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Basic
    @UpdateTimestamp
    @Column(name = "date_updated")
    public Timestamp getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Timestamp dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShippingDetails that = (ShippingDetails) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (zipPostalCode != null ? !zipPostalCode.equals(that.zipPostalCode) : that.zipPostalCode != null)
            return false;
        if (dateCreated != null ? !dateCreated.equals(that.dateCreated) : that.dateCreated != null) return false;
        if (dateUpdated != null ? !dateUpdated.equals(that.dateUpdated) : that.dateUpdated != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (zipPostalCode != null ? zipPostalCode.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        result = 31 * result + (dateUpdated != null ? dateUpdated.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id", nullable = false)
    public AvailableCountries getAvailableCountries() {
        return availableCountries;
    }

    public void setAvailableCountries(AvailableCountries availableCountries) {
        this.availableCountries = availableCountries;
    }
    @ManyToOne
    @JoinColumn(name = "delivery_method_id", referencedColumnName = "id", nullable = false)
    public DeliveryMethods getDeliveryMethods() {
        return deliveryMethods;
    }

    public void setDeliveryMethods(DeliveryMethods deliveryMethods) {
        this.deliveryMethods = deliveryMethods;
    }

    @ManyToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id", nullable = false)
    public PaymentMethods getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(PaymentMethods paymentMethods) {
        this.paymentMethods = paymentMethods;
    }
}
