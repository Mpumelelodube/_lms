package com.example._lms.rest;

import com.example._lms.domain.Country;
import com.example._lms.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class CountryResource {

    private final Logger log = LoggerFactory.getLogger(CountryResource.class);

    private static final String ENTITY_NAME = "country";

    private final CountryService countryService;

    @Autowired
    public CountryResource(CountryService countryService) {
        this.countryService = countryService;
    }

    /**
     * {@code POST  /countries} : Create a new country.
     *
     * @param country the country to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new country, or with status {@code 400 (Bad Request)} if the country has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/countries")
    public Country createCountry(@RequestBody Country country) throws URISyntaxException {
        log.debug("REST request to save Country : {}", country);

        return countryService.createOrUpdateCountry(country);
    }


    /**
     * {@code GET  /countries} : get all the countries.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of countries in body.
     */
    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        log.debug("REST request to get all Countries");
        return countryService.findAll();
    }


    /**
     * {@code GET  /countries/:id} : get the "id" country.
     *
     * @param id the id of the country to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the country, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/countries/{id}")
    public Country getCountry(@PathVariable Long id) {
        log.debug("REST request to get Country : {}", id);
        Country country = countryService.findOne(id);
        return country;
    }

    /**
     * {@code DELETE  /countries/:id} : delete the "id" country.
     *
     * @param id the id of the country to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/countries/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteCountry(@PathVariable Long id) {
        log.debug("REST request to delete Country : {}", id);
         countryService.delete(id);
    }

    public Country getSpecificCountry(Long id) {
        return countryService.findOne(id);
    }
}
