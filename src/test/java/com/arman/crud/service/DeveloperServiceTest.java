package com.arman.crud.service;

import com.arman.crud.repo.DeveloperRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DeveloperServiceTest {
    DeveloperRepo developerRepo = Mockito.mock(DeveloperRepo.class);

    @Test
    public void findByIdTest() {
        developerRepo.findById(1);
        verify(developerRepo, times(1)).findById(1);
    }

    @Test
    void findAllSkillsTest() {
        developerRepo.findAll();
        verify(developerRepo, times(1)).findAll();
    }
}
