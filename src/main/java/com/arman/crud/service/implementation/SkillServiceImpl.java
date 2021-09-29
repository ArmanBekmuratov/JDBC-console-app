package com.arman.crud.service.implementation;

import com.arman.crud.model.Skill;
import com.arman.crud.repo.implementation.SkillRepoImplementation;
import com.arman.crud.service.GenericService;

import java.util.List;

public class SkillServiceImpl implements GenericService<Skill, Integer> {
    private static final SkillRepoImplementation skillRepo = SkillRepoImplementation.getInstance();
    private static final SkillServiceImpl INSTANCE = new SkillServiceImpl();

    private SkillServiceImpl(){

    }

    public static SkillServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Skill> findAll() {
        return skillRepo.findAll();
    }

    @Override
    public Skill findById(Integer id) {
        return skillRepo.findById(id).orElse(null);
    }

    @Override
    public Skill save(Skill skill) {
        return skillRepo.save(skill);
    }

    @Override
    public boolean update(Skill skill) {
        return skillRepo.update(skill);
    }

    @Override
    public boolean deleteById(Integer id) {
        return skillRepo.deleteById(id);
    }
}
