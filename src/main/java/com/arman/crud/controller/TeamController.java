package com.arman.crud.controller;

import com.arman.crud.model.Developer;
import com.arman.crud.model.Team;
import com.arman.crud.model.TeamStatus;
import com.arman.crud.sercive.implementation.DeveloperService;
import com.arman.crud.sercive.implementation.TeamService;

import java.util.ArrayList;
import java.util.List;

public class TeamController {
    private static final TeamController INSTANCE = new TeamController();
    private static final TeamService teamService = TeamService.getInstance();
    private static final DeveloperService developerService = DeveloperService.getInstance();

    public List<Team> findAll() {
        return teamService.findAll();
    }

    public Team findById(Integer id) {
        return teamService.findById(id).orElse(null);
    }

    public Team update(Integer id, String[] developers) {
        return teamService.update(new Team(id, findById(id).getName(), developerCount(developers),TeamStatus.active));

    }

    public void deleteById(Integer id) {
        teamService.deleteById(id);
    }

    public Team save(String name, String[] developers) {
        return teamService.save(new Team(null, name, developerCount(developers), TeamStatus.active));
    }

    private List<Developer> developerCount(String[] developers) {
        List<Developer> listDeveloper = new ArrayList<>();
        for (String developer : developers) {
            listDeveloper.add(developerService.findById(Integer.parseInt(developer)).orElse(null));
        }
        return listDeveloper;
    }


    private TeamController() {

    }

    public static TeamController getInstance() {
        return INSTANCE;
    }
}
