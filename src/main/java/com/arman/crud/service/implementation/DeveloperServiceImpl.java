package com.arman.crud.service.implementation;

import com.arman.crud.model.Developer;
import com.arman.crud.repo.implementation.DeveloperRepoImplementation;
import com.arman.crud.service.GenericService;

import java.util.List;

public class DeveloperServiceImpl implements GenericService<Developer, Integer> {
    private static final DeveloperRepoImplementation developerRepo = DeveloperRepoImplementation.getInstance();
    private static final DeveloperServiceImpl INSTANCE = new DeveloperServiceImpl();

    public static DeveloperServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Developer> findAll() {
        return developerRepo.findAll();
    }

    @Override
    public Developer findById(Integer id) {
        return developerRepo.findById(id).orElse(null);
    }

    @Override
    public Developer save(Developer developer) {
        return developerRepo.save(developer);
    }

    @Override
    public boolean update(Developer developer) {
        return developerRepo.update(developer);
    }

    @Override
    public boolean deleteById(Integer id) {
        return developerRepo.deleteById(id);
    }
}
