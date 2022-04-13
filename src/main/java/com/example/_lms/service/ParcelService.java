package com.example._lms.service;


import com.example._lms.domain.Parcel;
import com.example._lms.repository.ParcelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ParcelService {
    private final Logger log = LoggerFactory.getLogger(ParcelService.class);

    private final ParcelRepository parcelRepository;

    @Autowired
    public ParcelService(ParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    /**
     * Save a parcel.
     *
     * @param parcel the entity to save.
     * @return the persisted entity.
     */
    public Parcel save(Parcel parcel) {
        log.debug("Request to save Parcel : {}", parcel);
        return parcelRepository.save(parcel);
    }

/*    *//**
     * Partially update a parcel.
     *
     * @param parcel the entity to update partially.
     * @return the persisted entity.
     *//*
    public Mono<Parcel> partialUpdate(Parcel parcel) {
        log.debug("Request to partially update Parcel : {}", parcel);

        return parcelRepository
                .findById(parcel.getId())
                .map(existingParcel -> {
                    if (parcel.getReference_number() != null) {
                        existingParcel.setReference_number(parcel.getReference_number());
                    }
                    if (parcel.getWeight() != null) {
                        existingParcel.setWeight(parcel.getWeight());
                    }
                    if (parcel.getHeight() != null) {
                        existingParcel.setHeight(parcel.getHeight());
                    }
                    if (parcel.getLength() != null) {
                        existingParcel.setLength(parcel.getLength());
                    }
                    if (parcel.getWidth() != null) {
                        existingParcel.setWidth(parcel.getWidth());
                    }
                    if (parcel.getPrice() != null) {
                        existingParcel.setPrice(parcel.getPrice());
                    }
                    if (parcel.getStatus() != null) {
                        existingParcel.setStatus(parcel.getStatus());
                    }

                    return existingParcel;
                })
                .flatMap(parcelRepository::save);
    }*/

    /**
     * Get all the parcels.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Parcel> findAll() {
        log.debug("Request to get all Parcels");
        return parcelRepository.findAll();
    }

/*    *//**
     * Returns the number of parcels available.
     * @return the number of entities in the database.
     *
     *//*
    public Mono<Long> countAll() {
        return parcelRepository.count();
    }*/

    /**
     * Get one parcel by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Parcel> findOne(Long id) {
        log.debug("Request to get Parcel : {}", id);
        return parcelRepository.findById(id);
    }

    /**
     * Delete the parcel by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public void delete(Long id) {
        log.debug("Request to delete Parcel : {}", id);
        parcelRepository.deleteById(id);
    }

    @Transactional
    public Parcel createOrUpdateParcel(Parcel parcel)
    {
        if (parcel.getId() == null){
            parcel = parcelRepository.save(parcel);
            return parcel;
        }

        else {
            Optional<Parcel> parcel1 = parcelRepository.findById(parcel.getId());
            if (parcel1.isPresent()){
                Parcel parcel2 = parcel1.get();
                parcel2.setReference_number(parcel.getReference_number());
                parcel2.setFromBranch(parcel.getFromBranch());
                parcel2.setToBranch(parcel.getToBranch());
                parcel2.setHeight(parcel.getHeight());
                parcel2.setWidth(parcel.getWidth());
                parcel2.setLength(parcel.getLength());
                parcel2.setWeight(parcel.getWeight());
                parcel2.setPrice(parcel.getPrice());
                parcel2.setStatus(parcel.getStatus());
                parcel2.setSender(parcel.getSender());
                parcel2.setReceiver(parcel.getReceiver());
                parcel2 = parcelRepository.save(parcel2);
                return parcel2;
            }else {
                parcel =parcelRepository.save(parcel);
                return parcel;

            }
        }
    }
}
