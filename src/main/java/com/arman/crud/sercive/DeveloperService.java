package com.arman.crud.sercive;

import com.arman.crud.model.Developer;
import com.arman.crud.model.Skill;
import com.arman.crud.repo.implementation.DeveloperRepoImplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeveloperService implements GenericService<Developer> {
    private static final DeveloperService INSTANCE = new DeveloperService();
    private static final DeveloperRepoImplementation developerRepo = DeveloperRepoImplementation.getInstance();

    private DeveloperService() {

    }

    @Override
    public Developer save( Developer developer) {
        return developerRepo.save(developer);
    }

    @Override
    public Developer update(Developer developer) {
        return developerRepo.update(developer);
    }

    @Override
    public Optional<Developer> findById(Integer id) {
        return developerRepo.findById(id);
    }

    @Override
    public List<Developer> findAll() {
        return developerRepo.findAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        return developerRepo.deleteById(id);
    }

    public static DeveloperService getInstance() {
        return INSTANCE;
    }

}
