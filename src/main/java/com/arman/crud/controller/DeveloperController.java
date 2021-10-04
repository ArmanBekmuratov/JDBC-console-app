package com.arman.crud.controller;

import com.arman.crud.model.Developer;
import com.arman.crud.model.Skill;
import com.arman.crud.sercive.implementation.DeveloperService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeveloperController {
    private static final DeveloperController INSTANCE = new DeveloperController();
    private static final DeveloperService developerService = DeveloperService.getInstance();

    public Developer save(String firstName, String lastName, String[] skills) {
        return developerService.save(new Developer(null, firstName, lastName, countSkillIds(skills)));
    }

    public Developer update(Integer id, String firstName, String lastName, String[] skills) {
        return developerService.update(new Developer(id, firstName, lastName, countSkillIds(skills)));
    }

    public Optional<Developer> findById(Integer id) {
        return developerService.findById(id);
    }

    public List<Developer> findAll() {
        return developerService.findAll();
    }

    public boolean deleteById(Integer id) {
        return developerService.deleteById(id);
    }

    private DeveloperController() {

    }

    public static DeveloperController getInstance() {
        return INSTANCE;
    }

    private List<Skill> countSkillIds(String[] skills) {
        List<Skill> skillList = new ArrayList<>();
        for (String skill : skills) {
            skillList.add(new Skill(Integer.parseInt(skill), null));
        }
        return skillList;
    }
}
