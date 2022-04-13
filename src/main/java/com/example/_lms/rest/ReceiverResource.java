package com.example._lms.rest;

import com.example._lms.domain.Receiver;
import com.example._lms.service.ReceiverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link Receiver}.
 */
@RestController
@RequestMapping("/api")
public class ReceiverResource {

    private final Logger log = LoggerFactory.getLogger(ReceiverResource.class);

    private static final String ENTITY_NAME = "receiver";


    private final ReceiverService receiverService;

    @Autowired
    public ReceiverResource(ReceiverService receiverService) {
        this.receiverService = receiverService;
    }

    /**
     * {@code POST  /receivers} : Create a new receiver.
     *
     * @param receiver the receiver to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new receiver, or with status {@code 400 (Bad Request)} if the receiver has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/receivers")
    public Receiver createReceiver(@RequestBody Receiver receiver) throws URISyntaxException {
        log.debug("REST request to save receiver : {}", receiver);
        if (receiver.getId() != null) {
           // throw ne("A new receiver cannot already have an ID");
        }
        return receiverService.createOrUpdateReceiver(receiver);
    }

    /**
     * {@code GET  /receivers} : get all the receivers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of receivers in body.
     */
    @GetMapping("/receivers")
    public List<Receiver> getAllReceivers() {
        log.debug("REST request to get all Receivers");
        return receiverService.findAll();
    }


    /**
     * {@code GET  /receivers/:id} : get the "id" receiver.
     *
     * @param id the id of the receiver to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the receiver, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/receivers/{id}")
    public Optional<Receiver> getReceiver(@PathVariable Long id) {
        log.debug("REST request to get Receiver : {}", id);
        Optional<Receiver> receiver = receiverService.findOne(id);
        return receiver;
    }

    /**
     * {@code DELETE  /receivers/:id} : delete the "id" receiver.
     *
     * @param id the id of the receiver to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/receivers/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteReceiver(@PathVariable Long id) {
        log.debug("REST request to delete Receiver : {}", id);
         receiverService.delete(id);
    }
}
