package com.arman.crud.controller;

import com.arman.crud.model.Skill;
import com.arman.crud.service.implementation.SkillServiceImpl;

import java.util.List;

public class SkillController {
    private static final SkillServiceImpl skillService = SkillServiceImpl.getInstance();
    private static final SkillController INSTANCE = new SkillController();

    public List<Skill> findAll() {
       return skillService.findAll();
    }

    public boolean deleteById(String id) {
        return skillService.deleteById(stringToInt(id));
    }

    public Skill findById(String id) {
        return skillService.findById(stringToInt(id));
    }

    public Skill save(String name) {
        return skillService.save(new Skill(null, name));
    }

    public boolean update(String id, String name) {
        return skillService.update(new Skill(stringToInt(id), name));
    }


    private Integer stringToInt(String string) {
        return Integer.parseInt(string);
    }

    public static SkillController getInstance() {
        return INSTANCE;
    }

    private SkillController() {

    }

}
