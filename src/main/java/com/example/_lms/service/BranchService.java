package com.example._lms.service;

import com.example._lms.domain.Branch;
import com.example._lms.repository.BranchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BranchService {

    private final Logger log = LoggerFactory.getLogger(BranchService.class);

    private final BranchRepository branchRepository;

    @Autowired
    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    /**
     * Save a branch.
     *
     * @param branch the entity to save.
     * @return the persisted entity.
     */
    public Branch save(Branch branch) {
        log.debug("Request to save Branch : {}", branch);
        return branchRepository.save(branch);
    }


    /**
     * Get all the branches.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Branch> findAll() {
        log.debug("Request to get all Branches");
        return branchRepository.findAll();
    }


    /**
     * Get one branch by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */

    @Transactional(readOnly = true)
    public Branch findOne(Long id) {
        log.debug("Request to get Branch : {}", id);
        return branchRepository.findById(id).orElseThrow(() -> new  IllegalStateException ("branch does not exist"));
    }

    /**
     * Delete the branch by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public void delete(Long id) {
        log.debug("Request to delete Branch : {}", id);
        branchRepository.deleteById(id);
    }

    @Transactional
    public Branch createOrUpdateBranch(Branch branch)
    {
        if (branch.getId() == null){
            branch = branchRepository.save(branch);
            return branch;
        }

        else {
            Optional<Branch> branch1 = branchRepository.findById(branch.getId());
            if (branch1.isPresent()){
                Branch branch2 = branch1.get();
                branch2.setName(branch.getName());
                branch2.setCode(branch.getCode());
                branch2.setStreet(branch.getStreet());
                branch2.setZip_code(branch.getZip_code());
                branch2.setContact(branch.getContact());
                branch2.setCountry(branch.getCountry());
                branch2.setState(branch.getState());
//                branch2.setCity(branch.getCity());
                branch2 = branchRepository.save(branch2);
                return branch2;
            }else {
                branch =branchRepository.save(branch);
                return branch;

            }
        }
    }
}
