package com.example._lms.rest;



import com.example._lms.domain.Parcel;
import com.example._lms.service.ParcelService;
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
public class ParcelResource {

    private final Logger log = LoggerFactory.getLogger(ParcelResource.class);

    private static final String ENTITY_NAME = "parcel";


    private final ParcelService parcelService;

    @Autowired
    public ParcelResource(ParcelService parcelService) {
        this.parcelService = parcelService;
    }


    /**
     * {@code POST  /parcels} : Create a new parcel.
     *
     * @param parcel the parcel to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new parcel, or with status {@code 400 (Bad Request)} if the parcel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/parcels")
    public Parcel createParcel(@RequestBody Parcel parcel) throws URISyntaxException {
        log.debug("REST request to save Parcel : {}", parcel);
        if (parcel.getId() != null) {
           // throw ne("A new parcel cannot already have an ID");
        }
        return parcelService.createOrUpdateParcel(parcel);
    }


    /**
     * {@code GET  /parcels} : get all the parcels.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of parcels in body.
     */
    @GetMapping("/parcels")
    public List<Parcel> getAllParcels() {
        log.debug("REST request to get all Parcels");
        List<Parcel> parcels = parcelService.findAll();

        for (int i = 0; i < parcels.size(); i++){
            parcels.se
        }

        return parcels;
    }


    /**
     * {@code GET  /parcels/:id} : get the "id" parcel.
     *
     * @param id the id of the parcel to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the parcel, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/parcels/{id}")
    public Optional<Parcel> getParcel(@PathVariable Long id) {
        log.debug("REST request to get Parcel : {}", id);
        Optional<Parcel> parcel = parcelService.findOne(id);
        return parcel;
    }

    /**
     * {@code DELETE  /parcels/:id} : delete the "id" parcel.
     *
     * @param id the id of the parcel to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/parcels/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteParcel(@PathVariable Long id) {
        log.debug("REST request to delete parcel : {}", id);
         parcelService.delete(id);
    }
}
