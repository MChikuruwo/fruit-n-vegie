package com.fruitnvegie.fruitnvegieapi.dao;

import com.fruitnvegie.fruitnvegieapi.models.AvailableCountries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableCountriesRepository extends JpaRepository<AvailableCountries,Long> {
    AvailableCountries findByCountry(String country);
}
