package com.arman.crud.service;

import com.arman.crud.repo.TeamRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TeamServiceTest {
    TeamRepo teamRepo = Mockito.mock(TeamRepo.class);

    @Test
    public void findByIdTest() {
        teamRepo.findById(1);
        verify(teamRepo, times(1)).findById(1);
    }

    @Test
    void findAllSkillsTest() {
        teamRepo.findAll();
        verify(teamRepo, times(1)).findAll();
    }
}
