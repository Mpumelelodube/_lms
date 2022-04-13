package com.example._lms.service;


import com.example._lms.domain.State;
import com.example._lms.repository.StateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StateService {
    private final Logger log = LoggerFactory.getLogger(StateService.class);

    private final StateRepository stateRepository;

    private final CountryService countryService;

    @Autowired
    public StateService(StateRepository stateRepository,CountryService countryService) {
        this.stateRepository = stateRepository;
        this.countryService = countryService;
    }

    /**
     * Save a state.
     *
     * @param state the entity to save.
     * @return the persisted entity.
     */
    @Transactional
    public State save(State state) {
        log.debug("Request to save State : {}", state);
        return stateRepository.save(state);
    }

/*    *//**
     * Partially update a state.
     *
     * @param state the entity to update partially.
     * @return the persisted entity.
     *//*
    public State partialUpdate(State state) {
        log.debug("Request to partially update State : {}", state);

        return stateRepository
                .findById(state.getId())
                .map(existingState -> {
                    if (state.getName() != null) {
                        existingState.setName(state.getName());
                    }

                    return existingState;
                })
                .flatMap(stateRepository::save);
    }*/

    /**
     * Get all the states.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<State> findAll() {
        log.debug("Request to get all States");
        return stateRepository.findAll();
    }

/*    *//**
     * Returns the number of states available.
     * @return the number of entities in the database.
     *
     *//*
    public Mono<Long> countAll() {
        return stateRepository.count();
    }*/

    /**
     * Get one state by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public State findOne(Long id) {
        log.debug("Request to get State : {}", id);
        return stateRepository.findById(id).orElseThrow(() -> new  IllegalStateException ("state does not exist"));
    }

    /**
     * Delete the state by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public void delete(Long id) {
        log.debug("Request to delete State : {}", id);
        stateRepository.deleteById(id);
    }

    @Transactional
    public State createOrUpdateState(State state)
    {
        if (state.getId() == null){
            state = stateRepository.save(state);
            return state;
        }

        else {
            Optional<State> state1 = stateRepository.findById(state.getId());
            if (state1.isPresent()){
                State state2 = state1.get();
                state2.setName(state.getName());
                state2.setCountry(state.getCountry());
                state2 = stateRepository.save(state2);
                return state2;
            }else {
                state =stateRepository.save(state);
                return state;

            }
        }
    }
}
