package com.example._lms.rest;



import com.example._lms.domain.State;
import com.example._lms.service.StateService;
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
 * REST controller for managing {@link State}.
 */
@RestController
@RequestMapping("/api")
public class StateResource {

    private final Logger log = LoggerFactory.getLogger(StateResource.class);

    private static final String ENTITY_NAME = "state";


    private final StateService stateService;

    @Autowired
    public StateResource(StateService stateService) {
        this.stateService = stateService;
    }

    /**
     * {@code POST  /states} : Create a new state.
     *
     * @param state the state to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new state, or with status {@code 400 (Bad Request)} if the state has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/states")
    public State createState(@RequestBody State state) throws URISyntaxException {
        log.debug("REST request to save State : {}", state);

        return stateService.createOrUpdateState(state);
    }

    /**
     * {@code GET  /states} : get all the states.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of states in body.
     */
    @GetMapping("/states")
    public List<State> getAllStates() {
        log.debug("REST request to get all States");
        return stateService.findAll();
    }


    /**
     * {@code GET  /states/:id} : get the "id" state.
     *
     * @param id the id of the state to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the state, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/states/{id}")
    public State getState(@PathVariable Long id) {
        log.debug("REST request to get State : {}", id);
        State state = stateService.findOne(id);
        return state;
    }

    /**
     * {@code DELETE  /states/:id} : delete the "id" state.
     *
     * @param id the id of the state to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/states/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteState(@PathVariable Long id) {
        log.debug("REST request to delete State : {}", id);
         stateService.delete(id);
    }

    public State getSpecificState(Long id) {
        return stateService.findOne(id);
    }
}
