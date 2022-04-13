package com.example._lms.rest;



import com.example._lms.domain.Sender;
import com.example._lms.service.SenderService;
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
public class SenderResource {

    private final Logger log = LoggerFactory.getLogger(SenderResource.class);

    private static final String ENTITY_NAME = "sender";


    private final SenderService senderService;

    @Autowired
    public SenderResource(SenderService senderService) {
        this.senderService = senderService;
    }

    /**
     * {@code POST  /senders} : Create a new sender.
     *
     * @param sender the sender to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sender, or with status {@code 400 (Bad Request)} if the sender has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/senders")
    public Sender createSender(@RequestBody Sender sender) throws URISyntaxException {
        log.debug("REST request to save Sender : {}", sender);
        if (sender.getId() != null) {
           // throw ne("A new sender cannot already have an ID");
        }
        return senderService.createOrUpdateSender(sender);
    }

    /**
     * {@code GET  /senders} : get all the senders.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of senders in body.
     */
    @GetMapping("/senders")
    public List<Sender> getAllSenders() {
        log.debug("REST request to get all Senders");
        return senderService.findAll();
    }


    /**
     * {@code GET  /senders/:id} : get the "id" sender.
     *
     * @param id the id of the sender to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sender, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/senders/{id}")
    public Optional<Sender> getSender(@PathVariable Long id) {
        log.debug("REST request to get Sender : {}", id);
        Optional<Sender> sender = senderService.findOne(id);
        return sender;
    }

    /**
     * {@code DELETE  /senders/:id} : delete the "id" sender.
     *
     * @param id the id of the sender to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/senders/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteSender(@PathVariable Long id) {
        log.debug("REST request to delete Sender : {}", id);
         senderService.delete(id);
    }
}
