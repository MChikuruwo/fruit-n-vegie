package com.fruitnvegie.fruitnvegieapi.services;

import com.fruitnvegie.fruitnvegieapi.dao.PaymentMethodsRepository;
import com.fruitnvegie.fruitnvegieapi.models.PaymentMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PaymentMethodsServiceImpl implements PaymentMethodsService {


    private final PaymentMethodsRepository paymentMethodsRepository;

    @Autowired
    public PaymentMethodsServiceImpl(PaymentMethodsRepository paymentMethodsRepository) {
        this.paymentMethodsRepository = paymentMethodsRepository;
    }


    @Override
    public String add(PaymentMethods paymentMethods) {
        paymentMethodsRepository.save(paymentMethods);
        return "Payment Method has been added";
    }

    @Override
    public String update(PaymentMethods paymentMethods) {
        Optional<PaymentMethods> paymentMethodFromDatabase = paymentMethodsRepository.findById(paymentMethods.getId());
        if (!paymentMethodFromDatabase.isPresent()) throw new EntityNotFoundException("Payment Method does not exist");
        paymentMethodsRepository.save(paymentMethods);
        return "Payment Method with ID " + paymentMethods.getId() + " has been updated";
    }

    @Override
    public String delete(Long id) {
        Optional<PaymentMethods> paymentMethodToDelete = paymentMethodsRepository.findById(id);
        if (!paymentMethodToDelete.isPresent()){
            throw new EntityNotFoundException("Payment Method with ID " + id + " does not exist");
        }
        paymentMethodsRepository.deleteById(id);
        return "Payment Method has been deleted";
    }

    @Override
    public List<PaymentMethods> getAll() {
        List<PaymentMethods> paymentMethods = paymentMethodsRepository.findAll();
        if (paymentMethods.isEmpty()){
            throw new EntityNotFoundException("No Payment Methods found");
        }
        return paymentMethods;
    }

    @Override
    public PaymentMethods getOne(Long id) {
        Optional<PaymentMethods> paymentMethods = paymentMethodsRepository.findById(id);
        if (!paymentMethods.isPresent()){
            throw new EntityNotFoundException("Payment Method with id " + id + " not found");
        }
        return paymentMethods.get();
    }

    @Override
    public PaymentMethods findByPaymentMethod(String paymentMethod) {
        PaymentMethods paymentMethods = paymentMethodsRepository.findByPaymentMethod(paymentMethod);
        if (paymentMethods == null){
            throw new EntityNotFoundException("Payment Method " + paymentMethod + " not found");
        }
        return paymentMethods;
    }

}
