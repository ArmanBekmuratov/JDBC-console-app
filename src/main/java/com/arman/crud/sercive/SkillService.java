package com.arman.crud.sercive;

import com.arman.crud.model.Skill;
import com.arman.crud.repo.implementation.SkillRepoImplementation;

import java.util.List;
import java.util.Optional;

public class SkillService implements GenericService<Skill>{
    private static final SkillRepoImplementation skillRepo = SkillRepoImplementation.getInstance();
    private static final SkillService INSTANCE = new SkillService();

    private SkillService() {

    }
    public static SkillService getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<Skill> findById(Integer id) {
        return skillRepo.findById(id);
    }

    @Override
    public List<Skill> findAll() {
        return skillRepo.findAll();
    }

    @Override
    public Skill save(Skill skill) {
        return skillRepo.save(skill);
    }

    @Override
    public Skill update(Skill skill) {
        return skillRepo.update(skill);
    }

    @Override
    public boolean deleteById(Integer id) {
        return skillRepo.deleteById(id);
    }
}
