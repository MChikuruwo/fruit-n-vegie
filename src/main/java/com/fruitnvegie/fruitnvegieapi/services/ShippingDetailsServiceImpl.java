package com.fruitnvegie.fruitnvegieapi.services;

import com.fruitnvegie.fruitnvegieapi.dao.ShippingDetailsRepository;
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

    @Autowired
    public ShippingDetailsServiceImpl(ShippingDetailsRepository shippingDetailsRepository) {
        this.shippingDetailsRepository = shippingDetailsRepository;
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
    public ShippingDetails findByUserId(Integer userId) {
        ShippingDetails shippingDetails = shippingDetailsRepository.findByUserId(userId);
        if (shippingDetails == null){
            throw new EntityNotFoundException("Shipping Details of user id " + userId + " not found.");
        }
        return shippingDetails;
    }

    @Override
    public List<ShippingDetails> findAllByCity(String city) {
        List<ShippingDetails> shippingDetails = shippingDetailsRepository.findAllByCity(city);
        if (shippingDetails.isEmpty()){
            throw new EntityNotFoundException("Shipping Details with city " + city + "not found.");
        }
        return shippingDetails;
    }

    @Override
   public  List<ShippingDetails> findAllByCountry(String country) {
        List<ShippingDetails> shippingDetails = shippingDetailsRepository.findAllByCountry(country);
        if (shippingDetails.isEmpty()){
            throw new EntityNotFoundException("Shipping Details from country " + country + "not found.");
        }
        return shippingDetails;
    }

    @Override
    public List<ShippingDetails> findAllByDeliveryMethods(String deliveryMethods) {
        List<ShippingDetails> shippingDetails = shippingDetailsRepository.findAllByDeliveryMethods(deliveryMethods);
        if (shippingDetails.isEmpty()){
            throw new EntityNotFoundException("Shipping Details with Delivery Method".concat(deliveryMethods).concat(" not found."));
        }
        return shippingDetails;
    }

    @Override
    public List<ShippingDetails> findAllByPaymentMethods(String paymentMethods) {
        List<ShippingDetails> shippingDetails = shippingDetailsRepository.findAllByPaymentMethods(paymentMethods);
        if (shippingDetails.isEmpty()){
            throw new EntityNotFoundException("Shipping Details with Payment Method ".concat(paymentMethods).concat(" not found."));
        }
        return shippingDetails;
    }

}
