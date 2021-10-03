package com.arman.crud.sercive;

import com.arman.crud.model.Team;
import com.arman.crud.repo.implementation.TeamRepoImplementation;
import liquibase.pro.packaged.T;

import java.util.List;
import java.util.Optional;

public class TeamService implements GenericService<Team>{
    private static final TeamService INSTANCE = new TeamService();
    private static final TeamRepoImplementation teamRepo = TeamRepoImplementation.getInstance();

    @Override
    public Optional<Team> findById(Integer id) {
        return teamRepo.findById(id);
    }

    @Override
    public List<Team> findAll() {
        return teamRepo.findAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        return teamRepo.deleteById(id);
    }

    @Override
    public Team save(Team team) {
        return teamRepo.save(team);
    }

    @Override
    public Team update(Team team) {
        return teamRepo.update(team);
    }

    public static TeamService getInstance() {
        return INSTANCE;
    }

    private TeamService() {

    }
}
