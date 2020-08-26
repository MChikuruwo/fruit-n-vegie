package com.fruitnvegie.fruitnvegieapi.services;


import com.fruitnvegie.fruitnvegieapi.dao.AvailableCountriesRepository;
import com.fruitnvegie.fruitnvegieapi.models.AvailableCountries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AvailableCountriesServiceImpl implements AvailableCountriesService {

    private final AvailableCountriesRepository availableCountriesRepository;

    @Autowired
    public AvailableCountriesServiceImpl(AvailableCountriesRepository availableCountriesRepository) {
        this.availableCountriesRepository = availableCountriesRepository;
    }


    @Override
    public String add(AvailableCountries availableCountries) {
        Optional<AvailableCountries> countiesFromDatabase = availableCountriesRepository.findById(availableCountries.getId());
        if (countiesFromDatabase.isPresent()) throw new EntityNotFoundException("Country already exists!");
        availableCountriesRepository.save(availableCountries);
        return "Country has been successfully added";
    }

    @Override
    public String update(AvailableCountries availableCountries) {
        Optional<AvailableCountries> countiesFromDatabase = availableCountriesRepository.findById(availableCountries.getId());
        if (!countiesFromDatabase.isPresent()) throw new EntityNotFoundException("Sorry, selected country does not exist.");
        availableCountriesRepository.save(availableCountries);
        return "Country with ID " + availableCountries.getId() + " has been successfully updated";
    }

    @Override
    public String delete(Long id) {
        Optional<AvailableCountries> countryToDelete = availableCountriesRepository.findById(id);
        if (!countryToDelete.isPresent()){
            throw new EntityNotFoundException("Country with ID " + id + " does not exist");
        }
        availableCountriesRepository.deleteById(id);
        return "Country has been successfully deleted";
    }

    @Override
    public List<AvailableCountries> getAll() {
        List<AvailableCountries> availableCountries = availableCountriesRepository.findAll();
        if (availableCountries.isEmpty()){
            throw new EntityNotFoundException("No Countries Available");
        }
        return availableCountries;
    }

    @Override
    public AvailableCountries  getOne(Long id) {
        Optional<AvailableCountries> availableCountries = availableCountriesRepository.findById(id);
        if (!availableCountries.isPresent()){
            throw new EntityNotFoundException("Country with id " + id + " is not available.");
        }
        return availableCountries.get();
    }

    @Override
    public  AvailableCountries findByCountryName(String countryName){
        AvailableCountries availableCountries = availableCountriesRepository.findByCountry(countryName);
        if (availableCountries == null){
            throw new EntityNotFoundException("Country" + countryName + " is not yet available.");
        }
        return availableCountries;
    }


}

