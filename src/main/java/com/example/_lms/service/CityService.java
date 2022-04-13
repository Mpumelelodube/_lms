package com.example._lms.service;


import com.example._lms.domain.City;
import com.example._lms.repository.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CityService {
    private final Logger log = LoggerFactory.getLogger(CityService.class);

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    /**
     * Save a city.
     *
     * @param city the entity to save.
     * @return the persisted entity.
     */
    public City save(City city) {
        log.debug("Request to save City : {}", city);
        return cityRepository.save(city);
    }

/*    *//**
     * Partially update a city.
     *
     * @param city the entity to update partially.
     * @return the persisted entity.
     *//*
    public Optional<City> partialUpdate(City city) {
        log.debug("Request to partially update City : {}", city);

        return cityRepository
                .findById(city.getId())
                .map(existingCity -> {
                    if (city.getName() != null) {
                        existingCity.setName(city.getName());
                    }

                    return existingCity;
                })
                .flatMap(cityRepository::save);
    }*/

    /**
     * Get all the cities.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<City> findAll() {
        log.debug("Request to get all Cities");
        return cityRepository.findAll();
    }
/*
    *//**
     * Returns the number of cities available.
     * @return the number of entities in the database.
     *
     *//*
    public Mono<Long> countAll() {
        return cityRepository.count();
    }*/

    /**
     * Get one city by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public City findOne(Long id) {
        log.debug("Request to get City : {}", id);
        return cityRepository.findById(id).orElseThrow(() -> new  IllegalStateException ("city does not exist"));

    }

    /**
     * Delete the city by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public void delete(Long id) {
        log.debug("Request to delete City : {}", id);
        cityRepository.deleteById(id);
    }

    @Transactional
    public City createOrUpdateCity(City city)
    {
        if (city.getId() == null){
            city = cityRepository.save(city);
            return city;
        }

        else {
            Optional<City> city1 = cityRepository.findById(city.getId());
            if (city1.isPresent()){
                City city2 = city1.get();
                city2.setName(city.getName());
                city2.setCountry(city.getCountry());
                city2 = cityRepository.save(city2);
                return city2;
            }else {
                city =cityRepository.save(city);
                return city;

            }
        }
    }
}
