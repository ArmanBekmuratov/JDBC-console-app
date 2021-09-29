package com.arman.crud.controller;

import com.arman.crud.model.Developer;
import com.arman.crud.model.Skill;
import com.arman.crud.service.implementation.DeveloperServiceImpl;
import com.arman.crud.service.implementation.SkillServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class DeveloperController {
    private static final DeveloperServiceImpl developerService = DeveloperServiceImpl.getInstance();
    private static final DeveloperController INSTANCE = new DeveloperController();

    public List<Developer> findAll() {
        return developerService.findAll();
    }

    public boolean deleteById(String id) {
        return developerService.deleteById(stringToInt(id));
    }

    public Developer findById(String id) {
        return developerService.findById(stringToInt(id));
    }

    public Developer save( String firstName, String lastName, String[] skills) {
        return developerService.save(new Developer(null, firstName, lastName, countSkill(skills)));
    }

    public boolean update(String id, String firstName, String lastName, String[] skills) {
        return developerService.update(new Developer(stringToInt(id), firstName, lastName, countSkill(skills)));
    }


    private Integer stringToInt(String string) {
        return Integer.parseInt(string);
    }


    private List<Skill> countSkill(String[] skills) {
        List<Skill> skillList = new ArrayList<>();
        for (String skill : skills) {
            skillList.add(new Skill(stringToInt(skill), null));
        }
        return skillList;
    }

    public static DeveloperController getInstance() {
        return INSTANCE;
    }

    private DeveloperController() {
    }
}
