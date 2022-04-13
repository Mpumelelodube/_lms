package com.example._lms.service;


import com.example._lms.domain.Sender;
import com.example._lms.repository.SenderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SenderService {
    private final Logger log = LoggerFactory.getLogger(SenderService.class);

    private final SenderRepository senderRepository;

    @Autowired
    public SenderService(SenderRepository senderRepository) {
        this.senderRepository = senderRepository;
    }

    /**
     * Save a sender.
     *
     * @param sender the entity to save.
     * @return the persisted entity.
     */
    public Sender save(Sender sender) {
        log.debug("Request to save Sender : {}", sender);
        return senderRepository.save(sender);
    }

/*    *//**
     * Partially update a sender.
     *
     * @param sender the entity to update partially.
     * @return the persisted entity.
     *//*
    public Mono<Sender> partialUpdate(Sender sender) {
        log.debug("Request to partially update Sender : {}", sender);

        return senderRepository
                .findById(sender.getId())
                .map(existingSender -> {
                    if (sender.getSender_number() != null) {
                        existingSender.setSender_number(sender.getSender_number());
                    }
                    if (sender.getName() != null) {
                        existingSender.setName(sender.getName());
                    }
                    if (sender.getAddress() != null) {
                        existingSender.setAddress(sender.getAddress());
                    }
                    if (sender.getPhone_number() != null) {
                        existingSender.setPhone_number(sender.getPhone_number());
                    }
                    if (sender.getEmail() != null) {
                        existingSender.setEmail(sender.getEmail());
                    }

                    return existingSender;
                })
                .flatMap(senderRepository::save);
    }*/

    /**
     * Get all the senders.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Sender> findAll() {
        log.debug("Request to get all Senders");
        return senderRepository.findAll();
    }

 /*   *//**
     * Returns the number of senders available.
     * @return the number of entities in the database.
     *
     *//*
    public Mono<Long> countAll() {
        return senderRepository.count();
    }*/

    /**
     * Get one sender by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Sender> findOne(Long id) {
        log.debug("Request to get Sender : {}", id);
        return senderRepository.findById(id);
    }

    /**
     * Delete the sender by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public void delete(Long id) {
        log.debug("Request to delete Sender : {}", id);
        senderRepository.deleteById(id);
    }

    @Transactional
    public Sender createOrUpdateSender(Sender sender)
    {
        if (sender.getId() == null){
            sender = senderRepository.save(sender);
            return sender;
        }

        else {
            Optional<Sender> receiver1 = senderRepository.findById(sender.getId());
            if (receiver1.isPresent()){
                Sender sender2 = receiver1.get();
                sender2.setSender_number(sender.getSender_number());
                sender2.setName(sender.getName());
                sender2.setAddress(sender.getAddress());
                sender2.setPhone_number(sender.getPhone_number());
                sender2.setEmail(sender.getEmail());
                sender2 = senderRepository.save(sender2);
                return sender2;
            }else {
                sender =senderRepository.save(sender);
                return sender;

            }
        }
    }
}
