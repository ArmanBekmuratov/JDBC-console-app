package com.arman.crud.service;
import com.arman.crud.repo.SkillRepo;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;


class SkillServiceTest {

    SkillRepo skillRepository = mock(SkillRepo.class);
    @Test
    public void findByIdTest() {
        skillRepository.findById(1);
        verify(skillRepository, times(1)).findById(1);
    }

    @Test
    void findAllSkillsTest() {
        skillRepository.findAll();
        verify(skillRepository, times(1)).findAll();
    }
}
