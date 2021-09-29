package com.arman.crud.controller;

import com.arman.crud.model.Developer;
import com.arman.crud.model.Team;
import com.arman.crud.model.TeamStatus;
import com.arman.crud.service.implementation.DeveloperServiceImpl;
import com.arman.crud.service.implementation.TeamServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class TeamController {
    private static final TeamController INSTANCE = new TeamController();
    private static final TeamServiceImpl teamService = TeamServiceImpl.getInstance();
    private static final DeveloperServiceImpl developerService = DeveloperServiceImpl.getInstance();

    public List<Team> findAll() {
        return teamService.findAll();
    }

    public Team findById(String id) {
        return teamService.findById(stringToInt(id));
    }

    public boolean update(String id, String[] developers) {
        return teamService.update(new Team(stringToInt(id), teamService.findById(stringToInt(id)).getName(), developerCount(developers),TeamStatus.active));

    }

    public void deleteById(String id) {
        teamService.deleteById(stringToInt(id));
    }

    public Team save(String name, String[] developers) {
        return teamService.save(new Team(null, name, developerCount(developers), TeamStatus.active));
    }

    private List<Developer> developerCount(String[] developers) {
        List<Developer> listDeveloper = new ArrayList<>();
        for (String developer : developers) {
            listDeveloper.add(developerService.findById(stringToInt(developer)));
        }
        return listDeveloper;
    }

    private Integer stringToInt(String string) {
        return Integer.parseInt(string);
    }

    private TeamController() {

    }

    public static TeamController getInstance() {
        return INSTANCE;
    }
}
