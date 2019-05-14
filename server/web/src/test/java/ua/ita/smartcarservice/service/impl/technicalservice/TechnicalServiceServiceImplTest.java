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
import ua.ita.smartcarservice.dto.technicalservice.TechnicalServiceDto;
import ua.ita.smartcarservice.entity.Roles;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.technicalservice.TechnicalServiceEntity;
import ua.ita.smartcarservice.entity.technicalservice.UserTechnicalService;
import ua.ita.smartcarservice.repository.UserRepository;
import ua.ita.smartcarservice.repository.technicalservice.TechnicalServiceRepository;
import ua.ita.smartcarservice.repository.technicalservice.UserTechnicalServiceRepository;
import ua.ita.smartcarservice.service.feedback.ServicesFeedbackService;

import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;

//@TestExecutionListeners(MockitoTestExecutionListener.class)
//@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TechnicalServiceServiceImplTest extends AbstractTestNGSpringContextTests {

    @Mock//Bean
            TechnicalServiceRepository technicalServiceRepository;

    @Mock
    ServicesFeedbackService servicesFeedbackService;

    @Mock
    UserRepository userRepository;

    @Mock
    UserTechnicalServiceRepository userTechnicalServiceRepository;

    @InjectMocks
    TechnicalServiceServiceImpl technicalServiceServiceImpl = new TechnicalServiceServiceImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getTechnicalServiceEntityByUser() {
        TechnicalServiceEntity expectedEntity;
        TechnicalServiceEntity actualEntity;

        Long userId = 1L;

        expectedEntity = new TechnicalServiceEntity("ServiceName", "Address", null);

        Mockito.when(technicalServiceRepository.getTechnicalServiceEntityByUser(anyLong())).thenReturn(expectedEntity);

        actualEntity = technicalServiceRepository.getTechnicalServiceEntityByUser(userId);

        Assert.assertEquals(expectedEntity, actualEntity);
        Assert.assertEquals(expectedEntity.getTechnicalServiceId(), actualEntity.getTechnicalServiceId());
        Assert.assertEquals(expectedEntity.getName(), actualEntity.getName());
        Assert.assertEquals(expectedEntity.getAddress(), actualEntity.getAddress());
    }


    @Test
    public void getTechnicalServiceDtoByUser() {
        TechnicalServiceDto expectedDto;
        TechnicalServiceDto actualDto;
        TechnicalServiceEntity entity;

        entity = new TechnicalServiceEntity();
        entity.setTechnicalServiceId(1L);
        entity.setName("ServiceName");
        entity.setAddress("Address");

        expectedDto = new TechnicalServiceDto();
        expectedDto.setStoId(1L);
        expectedDto.setName("ServiceName");
        expectedDto.setAddress("Address");
        expectedDto.setRating(4.6D);
        expectedDto.setWorkers(new ArrayList<UserEntity>() {});
        expectedDto.setTechnicalManager(null);

        Mockito.when(userRepository.getUserEntitiesByRoleNameAndTechnicalService(
                eq(Roles.ROLE_WORKER.toString()),
                anyLong())).thenReturn(new ArrayList<>());
        Mockito.when(servicesFeedbackService.getServicesRating(anyLong())).thenReturn(expectedDto.getRating());
        Mockito.when(technicalServiceRepository.getTechnicalServiceEntityByUser(anyLong())).thenReturn(entity);

        actualDto = technicalServiceServiceImpl.getTechnicalServiceDtoByUser(anyLong());

        System.out.println(actualDto);

        Assert.assertEquals(expectedDto.getStoId(), actualDto.getStoId());
        Assert.assertEquals(expectedDto.getName(), actualDto.getName());
        Assert.assertEquals(expectedDto.getAddress(), actualDto.getAddress());
        Assert.assertEquals(expectedDto.getRating(), actualDto.getRating());
        Assert.assertEquals(expectedDto.getTechnicalManager(), actualDto.getTechnicalManager());
        Assert.assertEquals(0, actualDto.getWorkers().size());
    }


    @Test
    public void convertToDto() {
        TechnicalServiceDto expectedDto;
        TechnicalServiceDto actualDto;
        TechnicalServiceEntity entity;

        entity = new TechnicalServiceEntity();
        entity.setTechnicalServiceId(1L);
        entity.setName("ServiceName");
        entity.setAddress("Address");

        expectedDto = new TechnicalServiceDto();
        expectedDto.setStoId(1L);
        expectedDto.setName("ServiceName");
        expectedDto.setAddress("Address");
        expectedDto.setRating(4.6D);
        expectedDto.setWorkers(new ArrayList<UserEntity>() {});
        expectedDto.setTechnicalManager(null);

        Mockito.when(userRepository.getUserEntitiesByRoleNameAndTechnicalService(
                eq(Roles.ROLE_WORKER.toString()),
                anyLong())).thenReturn(new ArrayList<>());
        Mockito.when(servicesFeedbackService.getServicesRating(anyLong())).thenReturn(expectedDto.getRating());

        actualDto = technicalServiceServiceImpl.convertToDto(entity);

        Assert.assertEquals(expectedDto.getStoId(), actualDto.getStoId());
        Assert.assertEquals(expectedDto.getName(), actualDto.getName());
        Assert.assertEquals(expectedDto.getAddress(), actualDto.getAddress());
        Assert.assertEquals(expectedDto.getRating(), actualDto.getRating());
        Assert.assertEquals(expectedDto.getTechnicalManager(), actualDto.getTechnicalManager());
        Assert.assertEquals(0, actualDto.getWorkers().size());
    }

    @Test
    public void convertToEntity() {
        TechnicalServiceDto dto;
        TechnicalServiceEntity expectedEntity;
        TechnicalServiceEntity actualEntity;

        expectedEntity = new TechnicalServiceEntity();
        expectedEntity.setTechnicalServiceId(1L);
        expectedEntity.setName("ServiceName");
        expectedEntity.setAddress("Address");

        dto = new TechnicalServiceDto();
        dto.setStoId(1L);
        dto.setName("ServiceName");
        dto.setAddress("Address");
        dto.setRating(4.6D);
        dto.setWorkers(new ArrayList<UserEntity>() {});
        dto.setTechnicalManager(null);

        actualEntity = technicalServiceServiceImpl.convertToEntity(dto);

        Assert.assertEquals(expectedEntity.getTechnicalServiceId(), actualEntity.getTechnicalServiceId());
        Assert.assertEquals(expectedEntity.getName(), actualEntity.getName());
        Assert.assertEquals(expectedEntity.getAddress(), actualEntity.getAddress());
    }


    @Test
    public void getAllTechnicalServices() {
        List<TechnicalServiceEntity> expectedList = new ArrayList<>();
        List<TechnicalServiceEntity> actualList;

        expectedList.add(new TechnicalServiceEntity("Name1", "Address1", null));
        expectedList.add(new TechnicalServiceEntity("Name2", "Address2", null));
        expectedList.add(new TechnicalServiceEntity("Name3", "Address3", null));

        Mockito.when(technicalServiceRepository.findAll()).thenReturn(new ArrayList<>(expectedList));

        actualList = technicalServiceServiceImpl.getAllTechnicalServices();

        Assert.assertEquals(expectedList.size(), actualList.size());
        Assert.assertEquals(expectedList.get(0), actualList.get(0));
        Assert.assertEquals(expectedList.get(1), actualList.get(1));
        Assert.assertEquals(expectedList.get(2), actualList.get(2));
    }

    @Test
    public void getAllTechnicalServicesDto() {
        List<TechnicalServiceDto> expectedList = new ArrayList<>();
        List<TechnicalServiceDto> actualList;
        List<TechnicalServiceEntity> entityList = new ArrayList<>();
        TechnicalServiceDto dto;
        TechnicalServiceEntity entity;

        entity = new TechnicalServiceEntity("Name1", "Address1", null);
        entity.setTechnicalServiceId(1L);
        entityList.add(entity);
        entityList.add(entity);
        entityList.add(entity);

        dto = new TechnicalServiceDto();
        dto.setStoId(1L);
        dto.setName("Name1");
        dto.setAddress("Address1");
        dto.setRating(4.6D);
        dto.setWorkers(new ArrayList<UserEntity>() {});
        dto.setTechnicalManager(null);

        expectedList.add(dto);
        expectedList.add(dto);
        expectedList.add(dto);

        Mockito.when(userRepository.getUserEntitiesByRoleNameAndTechnicalService(
                eq(Roles.ROLE_WORKER.toString()),
                anyLong())).thenReturn(new ArrayList<>());
        Mockito.when(servicesFeedbackService.getServicesRating(anyLong())).thenReturn(dto.getRating());


        Mockito.when(technicalServiceRepository.findAll()).thenReturn(new ArrayList<>(entityList));

        actualList = technicalServiceServiceImpl.getAllTechnicalServicesDto();

        Assert.assertEquals(expectedList.size(), actualList.size());
        Assert.assertEquals(expectedList.get(0), actualList.get(0));
        Assert.assertEquals(expectedList.get(1), actualList.get(1));
        Assert.assertEquals(expectedList.get(2), actualList.get(2));
    }

    @Test
    public void getTechnicalServiceById() {
        TechnicalServiceEntity expectedEntity;
        TechnicalServiceEntity actualEntity;

        expectedEntity = new TechnicalServiceEntity();
        expectedEntity.setTechnicalServiceId(1L);
        expectedEntity.setName("ServiceName");
        expectedEntity.setAddress("Address");

        Mockito.when(technicalServiceRepository.getOne(anyLong())).thenReturn(expectedEntity);

        actualEntity = technicalServiceServiceImpl.getTechnicalServiceById(anyLong());

        Assert.assertEquals(expectedEntity.getTechnicalServiceId(), actualEntity.getTechnicalServiceId());
        Assert.assertEquals(expectedEntity.getName(), actualEntity.getName());
        Assert.assertEquals(expectedEntity.getAddress(), actualEntity.getAddress());
    }

    @Test
    public void findTechnicalServiceByCarId() {
        TechnicalServiceEntity expectedEntity;
        UserTechnicalService userTechnicalService;

        String actualName;

        expectedEntity = new TechnicalServiceEntity();
        expectedEntity.setTechnicalServiceId(1L);
        expectedEntity.setName("ServiceName");
        expectedEntity.setAddress("Address");

        userTechnicalService = new UserTechnicalService();
        userTechnicalService.setTechnicalServiceId(expectedEntity);

        Mockito.when(userTechnicalServiceRepository.findTechnicalServiceByCarId(anyLong())).thenReturn(userTechnicalService);

        actualName = technicalServiceServiceImpl.findTechnicalServiceByCarId(anyLong());

        Assert.assertEquals(expectedEntity.getName(), actualName);
    }
}