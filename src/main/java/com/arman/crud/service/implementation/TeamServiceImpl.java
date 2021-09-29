package com.arman.crud.service.implementation;

import com.arman.crud.model.Team;
import com.arman.crud.repo.implementation.TeamRepoImplementation;
import com.arman.crud.service.GenericService;

import java.util.List;

public class TeamServiceImpl implements GenericService<Team, Integer> {
    private static final TeamServiceImpl INSTANCE = new TeamServiceImpl();
    private static final TeamRepoImplementation teamRepo = TeamRepoImplementation.getInstance();
    public static TeamServiceImpl getInstance() {
        return INSTANCE;
    }

    private TeamServiceImpl() {

    }
    @Override
    public List<Team> findAll() {
        return teamRepo.findAll();
    }

    @Override
    public Team findById(Integer id) {
        return teamRepo.findById(id).orElse(null);
    }

    @Override
    public Team save(Team team) {
        return teamRepo.save(team);
    }

    @Override
    public boolean update(Team team) {
        return teamRepo.update(team);
    }

    @Override
    public boolean deleteById(Integer id) {
        return teamRepo.deleteById(id);
    }
}
