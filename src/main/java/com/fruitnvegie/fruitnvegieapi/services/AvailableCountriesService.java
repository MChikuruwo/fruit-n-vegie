package com.fruitnvegie.fruitnvegieapi.services;

import com.fruitnvegie.fruitnvegieapi.models.AvailableCountries;

import java.util.List;

public interface AvailableCountriesService {

    String add(AvailableCountries availableCountries);
    String update(AvailableCountries availableCountries);
    String delete(Long id);
    List<AvailableCountries> getAll();
    AvailableCountries getOne(Long id);
    AvailableCountries findByCountry(String country);
}
