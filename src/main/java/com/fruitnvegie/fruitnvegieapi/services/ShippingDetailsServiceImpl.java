package com.fruitnvegie.fruitnvegieapi.services;

import com.fruitnvegie.fruitnvegieapi.dao.AvailableCountriesRepository;
import com.fruitnvegie.fruitnvegieapi.dao.ShippingDetailsRepository;
import com.fruitnvegie.fruitnvegieapi.models.DeliveryMethods;
import com.fruitnvegie.fruitnvegieapi.models.PaymentMethods;
import com.fruitnvegie.fruitnvegieapi.models.ShippingDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ShippingDetailsServiceImpl implements ShippingDetailsService{

    private final ShippingDetailsRepository shippingDetailsRepository;
    private final AvailableCountriesRepository availableCountriesRepository;

    @Autowired
    public ShippingDetailsServiceImpl(ShippingDetailsRepository shippingDetailsRepository, AvailableCountriesRepository availableCountriesRepository) {
        this.shippingDetailsRepository = shippingDetailsRepository;
        this.availableCountriesRepository = availableCountriesRepository;
    }

    @Override
    public String add(ShippingDetails shippingDetails) {
        shippingDetailsRepository.save(shippingDetails);
        return "Shipping Details have  been successfully added.";
    }

    @org.springframework.transaction.annotation.Transactional
    @Override
    public String update(ShippingDetails shippingDetails) {
        Optional<ShippingDetails> shippingDetailsFromDatabase = shippingDetailsRepository.findById(shippingDetails.getId());
        if (!shippingDetailsFromDatabase.isPresent()) throw new EntityNotFoundException("Shipping Details do not exist.");
        // Carry date created timestamp
        shippingDetails.setDateCreated(shippingDetailsFromDatabase.get().getDateCreated());
        shippingDetailsRepository.save(shippingDetails);
        return "Shipping Details with ID " + shippingDetails.getId() + " have been updated.";
    }

    @org.springframework.transaction.annotation.Transactional
    @Override
    public String delete(Long id) {
        Optional<ShippingDetails> shippingDetailsToDelete = shippingDetailsRepository.findById(id);
        if (!shippingDetailsToDelete.isPresent()){
            throw new EntityNotFoundException("Shipping Details with ID " + id + " do not exist.");
        }
        shippingDetailsRepository.deleteById(id);
        return "Shipping Details have been successfully deleted.";

    }

    @Override
    public List<ShippingDetails> getAll() {
        List<ShippingDetails> shippingDetails = shippingDetailsRepository.findAll();
        if (shippingDetails.isEmpty()){
            throw new EntityNotFoundException("Shipping Details  not found.");
        }
        return shippingDetails;
    }

    @Override
    public ShippingDetails getOne(Long id) {
        Optional<ShippingDetails> shippingDetails = shippingDetailsRepository.findById(id);
        if (!shippingDetails.isPresent()){
            throw new EntityNotFoundException("Shipping Details with the ID " + id + " do not exist.");
        }
        return shippingDetails.get();
    }

    @Override
    public ShippingDetails findByCustomerId(Long customerId) {
        ShippingDetails shippingDetails = shippingDetailsRepository.findByCustomerId(customerId);
        if (shippingDetails == null){
            throw new EntityNotFoundException("Shipping Details of user with id: " + customerId + " not found.");
        }
        return shippingDetails;
    }

    @Override
    public ShippingDetails findByAvailableCountriesId(Long countryId){
        ShippingDetails shippingDetails = shippingDetailsRepository.findByAvailableCountriesId(countryId);
        if (shippingDetails == null){
            throw new EntityNotFoundException("Shipping Details of country: " + countryId.toString() + " not found.");
        }
        return shippingDetails;
    }

    @Override
    public List<ShippingDetails> findAllByCity(String city) {
        List<ShippingDetails> shippingDetails = shippingDetailsRepository.findAllByCity(city);
        if (shippingDetails.isEmpty()){
            throw new EntityNotFoundException("Shipping is not available in  " + city + "yet.");
        }
        return shippingDetails;
    }

   /* @Override
   public  List<ShippingDetails> findAllByCountry(String country) {
        List<ShippingDetails> shippingDetails = (List<ShippingDetails>) availableCountriesRepository.findByCountry(country);
        if (shippingDetails.isEmpty()){
            throw new EntityNotFoundException("Shipping is not available in " + country + "yet.");
        }
        return shippingDetails;
    }

    */

    @Override
    public List<ShippingDetails> findAllByDeliveryMethods(DeliveryMethods deliveryMethods) {
        List<ShippingDetails> shippingDetails = shippingDetailsRepository.findAllByDeliveryMethods(deliveryMethods);
        if (shippingDetails.isEmpty()){
            throw new EntityNotFoundException("Shipping Details with Delivery Method:".concat(deliveryMethods.getDeliveryMethod()).concat(" not found."));
        }
        return shippingDetails;
    }

    @Override
    public List<ShippingDetails> findAllByPaymentMethods(PaymentMethods paymentMethods) {
        List<ShippingDetails> shippingDetails = shippingDetailsRepository.findAllByPaymentMethods(paymentMethods);
        if (shippingDetails.isEmpty()){
            throw new EntityNotFoundException("Shipping Details with Payment Method ".concat(paymentMethods.getPaymentMethod()).concat(" not found."));
        }
        return shippingDetails;
    }

}
