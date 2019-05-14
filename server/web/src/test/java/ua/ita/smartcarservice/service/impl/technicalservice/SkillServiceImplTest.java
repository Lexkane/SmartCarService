package ua.ita.smartcarservice.service.impl.technicalservice;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import ua.ita.smartcarservice.dto.technicalservice.SkillDto;
import ua.ita.smartcarservice.entity.technicalservice.SkillEntity;
import ua.ita.smartcarservice.repository.technicalservice.SkillRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(MockitoJUnitRunner.class)
public class SkillServiceImplTest extends AbstractTestNGSpringContextTests {

    @InjectMocks
    SkillServiceImpl skillService;

    @Mock
    SkillRepository skillRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllSkills() {
        List<SkillDto> actualList;
        List<SkillEntity> entityList;
        SkillDto skillDto;
        String skillName = "Skill Name";

        skillDto = new SkillDto();
        skillDto.setName(skillName);

        entityList = new ArrayList<>();
        entityList.add(new SkillEntity(skillName));
        entityList.add(new SkillEntity(skillName));
        entityList.add(new SkillEntity(skillName));

        Mockito.when(skillRepository.findAll()).thenReturn(entityList);

        actualList = skillService.getAllSkills();

        Assert.assertEquals(entityList.size(), actualList.size());
        Assert.assertEquals(skillDto, actualList.get(0));
    }

    @Test
    public void getSkillDto() {
        SkillEntity entity;
        SkillDto expectedDto;
        SkillDto actualDto;
        String skillName = "SkillName";

        entity = new SkillEntity();
        entity.setName(skillName);
        entity.setSkillId(1L);

        expectedDto = new SkillDto();
        expectedDto.setName(skillName);
        expectedDto.setId(entity.getSkillId());

        actualDto = skillService.getSkillDto(entity);

        Assert.assertEquals(expectedDto.getId(), actualDto.getId());
        Assert.assertEquals(expectedDto.getName(), actualDto.getName());
    }

    @Test
    public void findSkillNameBySto() {
        List<SkillDto> actualList;
        List<SkillEntity> entityList;
        SkillDto skillDto;
        String skillName = "Skill Name";

        skillDto = new SkillDto();
        skillDto.setName(skillName);

        entityList = new ArrayList<>();
        entityList.add(new SkillEntity(skillName));
        entityList.add(new SkillEntity(skillName));
        entityList.add(new SkillEntity(skillName));

        Mockito.when(skillRepository.findSkillNameBySto(anyLong())).thenReturn(entityList);

        actualList = skillService.findSkillNameBySto(anyLong());

        Assert.assertEquals(entityList.size(), actualList.size());
        Assert.assertEquals(skillDto, actualList.get(0));
    }

    @Test
    public void findSkillNameByCarId() {
        List<SkillDto> actualList;
        List<SkillEntity> entityList;
        SkillDto skillDto;
        String skillName = "Skill Name";

        skillDto = new SkillDto();
        skillDto.setName(skillName);

        entityList = new ArrayList<>();
        entityList.add(new SkillEntity(skillName));
        entityList.add(new SkillEntity(skillName));
        entityList.add(new SkillEntity(skillName));

        Mockito.when(skillRepository.findSkillNameByCarId(anyLong())).thenReturn(entityList);

        actualList = skillService.findSkillNameByCarId(anyLong());

        Assert.assertEquals(entityList.size(), actualList.size());
        Assert.assertEquals(skillDto, actualList.get(0));
    }
/*
    @Test
    public void getSkillById() {
        SkillEntity expectedEntity;
        SkillEntity actualEntity;

        expectedEntity = new SkillEntity();
        expectedEntity.setSkillId(1L);
        expectedEntity.setName("Skill Name");

        Mockito.when(skillRepository.findById(anyLong()).get()).thenReturn(expectedEntity);

        actualEntity = skillService.getSkillById(anyLong());

        Assert.assertEquals(expectedEntity.getSkillId(), actualEntity.getSkillId());
        Assert.assertEquals(expectedEntity.getName(), actualEntity.getName());
    }
*/
}