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


public class Main {
    static SkillRepoImplementation skillRepo = SkillRepoImplementation.getInstance();
    static DeveloperRepoImplementation developerRepo = DeveloperRepoImplementation.getInstance();
    static TeamRepoImplementation teamRepo = TeamRepoImplementation.getInstance();

    public static void main(String[] args) {
        ConsoleRunner consoleRunner = new ConsoleRunner();
        consoleRunner.run();
    }

}
