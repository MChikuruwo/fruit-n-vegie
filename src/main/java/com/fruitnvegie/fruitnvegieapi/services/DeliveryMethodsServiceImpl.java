package com.fruitnvegie.fruitnvegieapi.services;

import com.fruitnvegie.fruitnvegieapi.dao.DeliveryMethodsRepository;
import com.fruitnvegie.fruitnvegieapi.models.DeliveryMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DeliveryMethodsServiceImpl implements  DeliveryMethodsService{

    private final DeliveryMethodsRepository deliveryMethodsRepository;

    @Autowired
    public DeliveryMethodsServiceImpl(DeliveryMethodsRepository deliveryMethodsRepository) {
        this.deliveryMethodsRepository = deliveryMethodsRepository;
    }


    @Override
    public String add(DeliveryMethods deliveryMethods) {
        deliveryMethodsRepository.save(deliveryMethods);
        return "Delivery Method has been added";
    }

    @Override
    public String update(DeliveryMethods deliveryMethods) {
        Optional<DeliveryMethods> deliveryMethodFromDatabase = deliveryMethodsRepository.findById(deliveryMethods.getId());
        if (!deliveryMethodFromDatabase.isPresent()) throw new EntityNotFoundException("Delivery Method does not exist");
        deliveryMethodsRepository.save(deliveryMethods);
        return "Delivery Method with ID " + deliveryMethods.getId() + " has been updated";
    }

    @Override
    public String delete(Long id) {
        Optional<DeliveryMethods> departmentToDelete = deliveryMethodsRepository.findById(id);
        if (!departmentToDelete.isPresent()){
            throw new EntityNotFoundException("Delivery Method with ID " + id + " does not exist");
        }
        deliveryMethodsRepository.deleteById(id);
        return "Department has been deleted";
    }

    @Override
    public List<DeliveryMethods> getAll() {
        List<DeliveryMethods> deliveryMethods = deliveryMethodsRepository.findAll();
        if (deliveryMethods.isEmpty()){
            throw new EntityNotFoundException("No Delivery Methods found");
        }
        return deliveryMethods;
    }

    @Override
    public DeliveryMethods  getOne(Long id) {
        Optional<DeliveryMethods> deliveryMethods = deliveryMethodsRepository.findById(id);
        if (!deliveryMethods.isPresent()){
            throw new EntityNotFoundException("Delivery Method with id " + id + " not found");
        }
        return deliveryMethods.get();
    }

    @Override
    public DeliveryMethods findByDeliveryMethod(String deliveryMethod){
        DeliveryMethods deliveryMethods = deliveryMethodsRepository.findByDeliveryMethod(deliveryMethod);
        if (deliveryMethods == null){
            throw new EntityNotFoundException("Delivery Method " + deliveryMethod + " not found");
        }
        return deliveryMethods;
    }


}
