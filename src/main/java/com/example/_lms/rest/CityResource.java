package com.example._lms.rest;


import com.example._lms.domain.Branch;
import com.example._lms.domain.City;
import com.example._lms.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

/**
 * REST controller for managing {@link Branch}.
 */
@RestController
@RequestMapping("/api")
public class CityResource {

    private final Logger log = LoggerFactory.getLogger(CityResource.class);

    private static final String ENTITY_NAME = "city";


    private final CityService cityService;
    private final CountryResource countryResource;

    @Autowired
    public CityResource(CityService cityService, CountryResource cityResource) {
        this.cityService = cityService;
        this.countryResource = cityResource;
    }

    /**
     * {@code POST  /cities} : Create a new city.
     *
     * @param city the city to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new city, or with status {@code 400 (Bad Request)} if the city has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cities")
    public City createCity(@RequestBody City city) throws URISyntaxException {
        log.debug("REST request to save Branch : {}", city);
        return cityService.createOrUpdateCity(city);
    }



    /**
     * {@code GET  /cities} : get all the cities.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cities in body.
     */
    @GetMapping("/cities")
    public List<City> getAllCities() {
        log.debug("REST request to get all Cities");
        List<City> cities = cityService.findAll();

        for (int i = 0; i < cities.size(); i++){
            cities.get(i).setCountryObject(countryResource.getSpecificCountry(cities.get(i).getCountry()));
        }

        return cities;
    }


    /**
     * {@code GET  /cities/:id} : get the "id" city.
     *
     * @param id the id of the city to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the city, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cities/{id}")
    public City getCity(@PathVariable Long id) {
        log.debug("REST request to get City : {}", id);
        City city = cityService.findOne(id);
        city.setCountryObject(countryResource.getSpecificCountry(city.getCountry()));

        return city;
    }

    public City getSpecificCity(Long id){
        City city = cityService.findOne(id);
        city.setCountryObject(countryResource.getSpecificCountry(city.getCountry()));

        return city;
    }

    /**
     * {@code DELETE  /cities/:id} : delete the "id" cities.
     *
     * @param id the id of the city to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cities/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteCity(@PathVariable Long id) {
        log.debug("REST request to delete City : {}", id);
         cityService.delete(id);
    }
}
