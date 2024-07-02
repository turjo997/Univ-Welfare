package com.suffixit.kieb.repository;

import com.suffixit.kieb.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CountryRepository extends JpaRepository<Country , String> {

    Country findByCountryCode(String countryCode);

    List<Country> findAllByCountryCodeIn(Set<String> countryCodes);

}
