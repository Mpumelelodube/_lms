package com.example._lms.rest;



import com.example._lms.domain.Branch;
import com.example._lms.repository.BranchRepository;
import com.example._lms.service.BranchService;
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
public class BranchResource {

    private final Logger log = LoggerFactory.getLogger(BranchResource.class);

    private static final String ENTITY_NAME = "branch";


    private final BranchService branchService;

    private final BranchRepository branchRepository;
    private CityResource cityResource;
    private StateResource stateResource;
    private CountryResource countryResource;

    @Autowired
    public BranchResource(BranchService branchService, BranchRepository branchRepository, CityResource cityResource, StateResource stateResource, CountryResource countryResource) {
        this.branchService = branchService;
        this.branchRepository = branchRepository;
        this.cityResource = cityResource;
        this.stateResource = stateResource;
        this.countryResource = countryResource;
    }

    /**
     * {@code POST  /branches} : Create a new branch.
     *
     * @param branch the branch to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new branch, or with status {@code 400 (Bad Request)} if the branch has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/branches")
    public Branch createBranch(@RequestBody Branch branch) throws URISyntaxException {
//        cityResource.getSpecificCity(branch.getCity());
        return branchService.save(branch);
    }




    /**
     * {@code PUT  /branches/:id} : Updates an existing branch.
     *
     * @param id the id of the branch to save.
     * @param branch the branch to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated branch,
     * or with status {@code 400 (Bad Request)} if the branch is not valid,
     * or with status {@code 500 (Internal Server Error)} if the branch couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */


    /**
     * {@code PATCH  /branches/:id} : Partial updates given fields of an existing branch, field will ignore if it is null
     *
     * @param id the id of the branch to save.
     * @param branch the branch to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated branch,
     * or with status {@code 400 (Bad Request)} if the branch is not valid,
     * or with status {@code 404 (Not Found)} if the branch is not found,
     * or with status {@code 500 (Internal Server Error)} if the branch couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
/*    @PatchMapping(value = "/branches/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<Branch>> partialUpdateBranch(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Branch branch
    ) throws URISyntaxException {
        log.debug("REST request to partial update Branch partially : {}, {}", id, branch);
        if (branch.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, branch.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return branchRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<Branch> result = branchService.partialUpdate(branch);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, res.getId().toString()))
                            .body(res)
                    );
            });
    }*/

    /**
     * {@code GET  /branches} : get all the branches.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of branches in body.
     */
    @GetMapping("/branches")
    public List<Branch> getAllBranches() {
        log.debug("REST request to get all Branches");
        List<Branch> branches = branchService.findAll();

        for (int i = 0; i < branches.size(); i++){
            branches.get(i).setCityObject(cityResource.getSpecificCity(branches.get(i).getCountry()));
            branches.get(i).setCountryObject(countryResource.getSpecificCountry(branches.get(i).getCountry()));
            branches.get(i).setStateObject(stateResource.getSpecificState(branches.get(i).getState()));
        }


        return branches;
    }


    /**
     * {@code GET  /branches/:id} : get the "id" branch.
     *
     * @param id the id of the branch to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the branch, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/branches/{id}")
    public Branch getBranch(@PathVariable Long id) {
        log.debug("REST request to get Branch : {}", id);
        Branch branch = branchService.findOne(id);
        branch.setCountryObject(countryResource.getSpecificCountry(branch.getCountry()));
        branch.setStateObject(stateResource.getSpecificState(branch.getState()));
        branch.setCityObject(cityResource.getSpecificCity(branch.getCity()));

        return branch;
    }

    /**
     * {@code DELETE  /branches/:id} : delete the "id" branch.
     *
     * @param id the id of the branch to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/branches/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteBranch(@PathVariable Long id) {
        log.debug("REST request to delete Branch : {}", id);
         branchService.delete(id);
    }
}
