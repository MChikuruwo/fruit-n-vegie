package com.fruitnvegie.fruitnvegieapi.dao;

import com.fruitnvegie.fruitnvegieapi.models.AvailableCountries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AvailableCountriesRepository extends JpaRepository<AvailableCountries,Long> {
    AvailableCountries findByCountry(String countryName);
}
