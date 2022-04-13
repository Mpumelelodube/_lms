package com.example._lms.service;



import com.example._lms.domain.Receiver;
import com.example._lms.repository.ReceiverRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReceiverService {

    private final Logger log = LoggerFactory.getLogger(ReceiverService.class);

    private final ReceiverRepository receiverRepository;

    @Autowired
    public ReceiverService(ReceiverRepository receiverRepository) {
        this.receiverRepository = receiverRepository;
    }

    /**
     * Save a receiver.
     *
     * @param receiver the entity to save.
     * @return the persisted entity.
     */
    public Receiver save(Receiver receiver) {
        log.debug("Request to save Receiver : {}", receiver);
        return receiverRepository.save(receiver);
    }

/*    *//**
     * Partially update a receiver.
     *
     * @param receiver the entity to update partially.
     * @return the persisted entity.
     *//*
    public Mono<Receiver> partialUpdate(Receiver receiver) {
        log.debug("Request to partially update Receiver : {}", receiver);

        return receiverRepository
                .findById(receiver.getId())
                .map(existingReceiver -> {
                    if (receiver.getReceiver_number() != null) {
                        existingReceiver.setReceiver_number(receiver.getReceiver_number());
                    }
                    if (receiver.getName() != null) {
                        existingReceiver.setName(receiver.getName());
                    }
                    if (receiver.getAddress() != null) {
                        existingReceiver.setAddress(receiver.getAddress());
                    }
                    if (receiver.getPhone_number() != null) {
                        existingReceiver.setPhone_number(receiver.getPhone_number());
                    }
                    if (receiver.getEmail() != null) {
                        existingReceiver.setEmail(receiver.getEmail());
                    }

                    return existingReceiver;
                })
                .flatMap(receiverRepository::save);
    }*/

    /**
     * Get all the receivers.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Receiver> findAll() {
        log.debug("Request to get all Receivers");
        return receiverRepository.findAll();
    }

/*    *//**
     * Returns the number of receivers available.
     * @return the number of entities in the database.
     *
     *//*
    public Mono<Long> countAll() {
        return receiverRepository.count();
    }*/

    /**
     * Get one receiver by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Receiver> findOne(Long id) {
        log.debug("Request to get Receiver : {}", id);
        return receiverRepository.findById(id);
    }

    /**
     * Delete the receiver by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public void delete(Long id) {
        log.debug("Request to delete Receiver : {}", id);
        receiverRepository.deleteById(id);
    }

    @Transactional
    public Receiver createOrUpdateReceiver(Receiver receiver)
    {
        if (receiver.getId() == null){
            receiver = receiverRepository.save(receiver);
            return receiver;
        }

        else {
            Optional<Receiver> receiver1 = receiverRepository.findById(receiver.getId());
            if (receiver1.isPresent()){
                Receiver receiver2 = receiver1.get();
                receiver2.setReceiver_number(receiver.getReceiver_number());
                receiver2.setName(receiver.getName());
                receiver2.setAddress(receiver.getAddress());
                receiver2.setPhone_number(receiver.getPhone_number());
                receiver2.setEmail(receiver.getEmail());
                receiver2 = receiverRepository.save(receiver2);
                return receiver2;
            }else {
                receiver =receiverRepository.save(receiver);
                return receiver;

            }
        }
    }
}
