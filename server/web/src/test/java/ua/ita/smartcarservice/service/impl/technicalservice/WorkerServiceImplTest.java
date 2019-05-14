package ua.ita.smartcarservice.service.impl.technicalservice;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import ua.ita.smartcarservice.entity.Roles;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(MockitoJUnitRunner.class)
public class WorkerServiceImplTest extends AbstractTestNGSpringContextTests {

    @InjectMocks
    WorkerServiceImpl workerService;

    @Mock
    UserRepository userRepository;

    @Test
    public void getWorkerById() {
        UserEntity expectedEntity;
        UserEntity actualEntity;

        expectedEntity = new UserEntity();
        expectedEntity.setId(1L);
        expectedEntity.setEmail("mail");
        expectedEntity.setFullName("Full name");
        expectedEntity.setNumberPhone("09999999");
        expectedEntity.setPassword("password");
        expectedEntity.setUsername("Username");

        Mockito.when(userRepository.getUserById(anyLong())).thenReturn(expectedEntity);

        actualEntity = userRepository.getUserById(anyLong());

        Assert.assertEquals(expectedEntity.getId(), actualEntity.getId());
        Assert.assertEquals(expectedEntity.getEmail(), actualEntity.getEmail());
        Assert.assertEquals(expectedEntity.getFullName(), actualEntity.getFullName());
        Assert.assertEquals(expectedEntity.getNumberPhone(), actualEntity.getNumberPhone());
        Assert.assertEquals(expectedEntity.getUsername(), actualEntity.getUsername());
        Assert.assertEquals(expectedEntity.getPassword(), expectedEntity.getPassword());
    }

    @Test
    public void getAllWorkers() {
        List<UserEntity> expectedList;
        List<UserEntity> actualList;
        UserEntity entity;

        entity = new UserEntity();
        entity.setId(1L);
        entity.setEmail("mail");
        entity.setFullName("Full name");
        entity.setNumberPhone("09999999");
        entity.setPassword("password");
        entity.setUsername("Username");

        expectedList = new ArrayList<>();
        expectedList.add(entity);
        expectedList.add(entity);
        expectedList.add(entity);

        Mockito.when(userRepository.getUserEntitiesByRoleName(Roles.ROLE_WORKER.toString())).thenReturn(expectedList);

        actualList = workerService.getAllWorkers();

        Assert.assertEquals(expectedList.size(), actualList.size());
        Assert.assertEquals(expectedList.get(0), entity);
    }

    @Test
    public void findByCarIdAndWorkersSkill() {
    }

    @Test
    public void getWorkerSkillDtoById() {
    }
}