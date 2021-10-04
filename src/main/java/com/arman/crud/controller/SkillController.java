package com.arman.crud.controller;

import com.arman.crud.model.Skill;
import com.arman.crud.sercive.implementation.SkillService;

import java.util.List;
import java.util.Optional;

public class SkillController {
    private static final SkillService skillService = SkillService.getInstance();
    private static final SkillController INSTANCE = new SkillController();

    private SkillController(){

    }

    public static SkillController getInstance() {
        return INSTANCE;
    }

    public Optional<Skill> findById(Integer id) {
        return skillService.findById(id);
    }

    public List<Skill> findAll() {
        return skillService.findAll();
    }

    public Skill save(String name) {
        return skillService.save(new Skill(name));
    }

    public Skill update(Integer id, String  name) {
        return skillService.update(new Skill(id, name));
    }

    public boolean deleteById(Integer id) {
        return skillService.deleteById(id);
    }
}
