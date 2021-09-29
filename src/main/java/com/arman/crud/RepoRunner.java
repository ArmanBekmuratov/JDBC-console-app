package com.arman.crud;

import com.arman.crud.model.Developer;
import com.arman.crud.model.Skill;
import com.arman.crud.model.Team;
import com.arman.crud.model.TeamStatus;
import com.arman.crud.repo.implementation.DeveloperRepoImplementation;
import com.arman.crud.repo.implementation.SkillRepoImplementation;
import com.arman.crud.repo.implementation.TeamRepoImplementation;
import com.arman.crud.view.ConsoleRunner;
import com.arman.crud.view.SkillView;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;


public class RepoRunner {
    static SkillRepoImplementation skillRepo = SkillRepoImplementation.getInstance();
    static DeveloperRepoImplementation developerRepo = DeveloperRepoImplementation.getInstance();
    static TeamRepoImplementation teamRepo = TeamRepoImplementation.getInstance();

    public static void main(String[] args) {
        ConsoleRunner consoleRunner = new ConsoleRunner();
        consoleRunner.run();

    }

    private static void testSkill(Skill skill1, Skill skill2) {
        System.out.println("SAVING FIRST SKILL");
        System.out.println(skillRepo.save(skill1));
        System.out.println("DELETING FIRST SKILL");
        System.out.println(skillRepo.deleteById(1));
        System.out.println("SAVING FIRST SKILL AGAIN");
        System.out.println(skillRepo.save(skill1));
        System.out.println("SAVING SECOND SKILL");
        System.out.println(skillRepo.save(skill2));
        System.out.println("FINDING FIRST SKILL");
        System.out.println(skillRepo.findById(1));
        System.out.println("FINDING SECOND SKILL");
        System.out.println(skillRepo.findById(2));
        System.out.println("FINDING ALL SKILLS");
        System.out.println(skillRepo.findAll());
        System.out.println("UPDATING NAME OF FIRST SKILL FROM 'java' TO 'JAVA'");
        var optionalSkill = skillRepo.findById(2);
        optionalSkill.ifPresent(skill -> {
            skill.setName("JAVA");
            skillRepo.update(skill);
        });
    }


}
