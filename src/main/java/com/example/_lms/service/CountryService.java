package com.example._lms.service;

import com.example._lms.domain.Country;
import com.example._lms.repository.CountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CountryService {
    private final Logger log = LoggerFactory.getLogger(CountryService.class);

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    /**
     * Save a country.
     *
     * @param country the entity to save.
     * @return the persisted entity.
     */
    public Country save(Country country) {
        log.debug("Request to save Country : {}", country);
        return countryRepository.save(country);
    }

/*    *//**
     * Partially update a country.
     *
     * @param country the entity to update partially.
     * @return the persisted entity.
     *//*
    public Country partialUpdate(Country country) {
        log.debug("Request to partially update Country : {}", country);

        return countryRepository
                .findById(country.getId())
                .map(existingCountry -> {
                    if (country.getName() != null) {
                        existingCountry.setName(country.getName());
                    }

                    return existingCountry;
                })
                .flatMap(countryRepository::save);
    }*/

    /**
     * Get all the countries.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Country> findAll() {
        log.debug("Request to get all Countries");
        return countryRepository.findAll();
    }

/*    *//**
     * Returns the number of countries available.
     * @return the number of entities in the database.
     *
     *//*
    public Mono<Long> countAll() {
        return countryRepository.count();
    }*/

    /**
     * Get one country by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Country findOne(Long id) {
        log.debug("Request to get Country : {}", id);
        return countryRepository.findById(id).orElseThrow(() -> new  IllegalStateException ("country does not exist"));
    }

    /**
     * Delete the country by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public void delete(Long id) {
        log.debug("Request to delete Country : {}", id);
        countryRepository.deleteById(id);
    }

    @Transactional
    public Country createOrUpdateCountry(Country country)
    {
        if (country.getId() == null){
            country = countryRepository.save(country);
            return country;
        }

        else {
            Optional<Country> country1 = countryRepository.findById(country.getId());
            if (country1.isPresent()){
                Country country2 = country1.get();
                country2.setName(country.getName());
                country2 = countryRepository.save(country2);
                return country2;
            }else {
                country =countryRepository.save(country);
                return country;

            }
        }
    }
}
