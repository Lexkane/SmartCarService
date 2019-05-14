package ua.ita.smartcarservice.controller.technicalservice;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.ita.smartcarservice.dto.feedback.ServicesFeedbackInputDto;
import ua.ita.smartcarservice.dto.feedback.ServicesFeedbackOutputDto;
import ua.ita.smartcarservice.dto.technicalservice.TechnicalServiceDto;
import ua.ita.smartcarservice.dto.technicalservice.WorkerSkillDto;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.technicalservice.TechnicalServiceEntity;
import ua.ita.smartcarservice.service.feedback.ServicesFeedbackService;
import ua.ita.smartcarservice.service.technicalservice.TechnicalServiceService;
import ua.ita.smartcarservice.service.technicalservice.WorkerService;

import java.lang.invoke.MethodHandles;
import java.util.List;

/**
 * Controller (REST) works with TechnicalService.
 */
@RequestMapping("/api")
@RestController
public class TechnicalServiceController {

    @Autowired
    private ServicesFeedbackService servicesFeedbackService;

    @Autowired
    private TechnicalServiceService technicalServiceService;

    @Autowired
    private WorkerService workerService;

    private static final Logger LOGGER =
            Logger.getLogger(MethodHandles.
                    lookup().lookupClass().getSimpleName());

    /**
     * Method for getting all the technical services.
     */
    @GetMapping("/techservices")
    ResponseEntity<List<TechnicalServiceDto>> getAllTechnicalServices() {
        ResponseEntity<List<TechnicalServiceDto>> responseEntity;

        try {
            responseEntity = new ResponseEntity<>(
                    technicalServiceService.getAllTechnicalServicesDto(),
                    HttpStatus.OK);
            LOGGER.info("Successfully get all the technical services.");
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            LOGGER.error("Error while getting all the technical services."
                    + " Details: {}", e);
        }

        return responseEntity;
    }

    /**
     * Method for creating TechnicalService, it gets parameters from the URL.
     */
    @PostMapping("/users/{id}/techservices")
    @ResponseBody
    void createTechnicalService(
            @PathVariable final Long id,
            @RequestParam(value = "name") final String name,
            @RequestParam(value = "address") final String address) {

        try {
            technicalServiceService.createTechnicalService(name, address, id);
            LOGGER.info("Successfully created a technical service.");
        } catch (Exception e) {
            LOGGER.error("Error while creating a technical service."
                    + " Details: " + e.getMessage());
        }
    }

    /**
     * Method for getting whole information
     * about about current TechnicalService by its id.
     */
    @GetMapping("/techservices/{id}")
    ResponseEntity<TechnicalServiceDto> getTechnicalService(
            @PathVariable final Long id) {
        ResponseEntity<TechnicalServiceDto> responseEntity;

        try {
            responseEntity = new ResponseEntity<>(
                    technicalServiceService.getTechnicalServiceDtoById(id),
                    HttpStatus.OK);
            LOGGER.info("Successfully get a technical service by id: " + id);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            LOGGER.error("Error while getting a technical service by id: " + id
                    + " Details: " + e.getMessage());
        }

        return responseEntity;
    }

    /**
     * Method fot creation a connection between Worker and TechnicalService
     * also it adds a Skill to Current Worker.
     */
    @PostMapping("/techservices/{id}/workers/{username}/skills/{skillId}")
    ResponseEntity addWorkerToTechnicalService(
            @PathVariable final Long id,
            @PathVariable final String username,
            @PathVariable final Long skillId) {
        ResponseEntity responseEntity;

        try {
            /*  Adding Worker to Technical Service    */
            technicalServiceService.addUserToTechnicalService(username, id);

            /* Adding Skill to Worker */
            workerService.addSkillToWorker(username, skillId);

            responseEntity = new ResponseEntity(HttpStatus.OK);
            LOGGER.info("Successfully get a technical service`s workers"
                    + " by id: " + id);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            LOGGER.error("Error while connection a technical service"
                    + " and worker id: " + id
                    + " Worker`s username^ " + username
                    + " Details: " + e.getMessage());
        }

        return responseEntity;
    }

    /**
     * Method fot creation a connection between User and TechnicalService.
     */
    @PostMapping("/techservices/{id}/users/{username}")
    ResponseEntity applyUserToTechnicalService(
            @PathVariable final Long id,
            @PathVariable final String username) {
        ResponseEntity responseEntity;

        try {
            /*  Adding Worker to Technical Service    */
            technicalServiceService.addUserToTechnicalService(username, id);

            responseEntity = new ResponseEntity(HttpStatus.OK);
            LOGGER.info("Successfully applied a user username: "
                    + username + " a technical service with id: " + id);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            LOGGER.error("Error while aworkerspplying a technical service id:"
                    + id
                    + " and user username: " + username
                    + " Details: " + e.getMessage());
        }

        return responseEntity;
    }

    /**
     * Method for getting Workers from current TechnicalService,
     * it gets parameters from the URL.
     */
    @GetMapping("/techservices/{id}/workers")
    ResponseEntity<List<WorkerSkillDto>> getTechnicalServiceWorkers(
            @PathVariable final Long id) {

        ResponseEntity<List<WorkerSkillDto>> responseEntity;

        try {
            List<UserEntity> workersList =
                    technicalServiceService.getUsersByRoleAndTechnicalSevice(
                            "ROLE_WORKER", id);
            List<WorkerSkillDto> workersWithSkillList =
                    workerService.addSkillToWorkersList(workersList);

            responseEntity = new ResponseEntity<List<WorkerSkillDto>>(
                    workersWithSkillList,
                    HttpStatus.OK);
            LOGGER.info("Successfully get a technical service`s workers by"
                    + " id: " + id);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            LOGGER.error("Error while getting a technical service`s workers by"
                    + " id: " + id
                    + " Details: " + e.getMessage());
        }

        return responseEntity;
    }

    /**
     * Method for updating information about current TechnicalService,
     * it gets parameters from the URL.
     */
    @PutMapping("/techservices/{id}")
    void updateTechnicalService(
            @PathVariable final Long id,
            @RequestParam(value = "name", required = false) final String name,
            @RequestParam(value = "address", required = false) final String address) {
        try {
            TechnicalServiceEntity technicalServiceEntity =
                    technicalServiceService.getTechnicalServiceById(id);

            if (name != null) {
                technicalServiceEntity.setName(name);
            }

            if (address != null) {
                technicalServiceEntity.setAddress(address);
            }

            technicalServiceService.
                    updateTechnicalService(technicalServiceEntity);

            LOGGER.info("Successfully updated a technical service by id: "
                    + id);
        } catch (Exception e) {
            LOGGER.error("Error while updating a technical service by id: "
                    + id + " Details: " + e.getMessage());
        }
    }

    /**
     * Method for getting Technical Service bu User ID.
     */
    @GetMapping("/users/{userId}/techservice")
    public ResponseEntity<TechnicalServiceDto> findTechnicalServiceByUserId(
            @PathVariable("userId") final Long userId) {

        ResponseEntity<TechnicalServiceDto> responseEntity;

        try {
            responseEntity = new ResponseEntity<TechnicalServiceDto>(
                technicalServiceService.getTechnicalServiceDtoByUser(userId),
                HttpStatus.OK);
            LOGGER.info("Successfully get a Technical Service by User`s id: "
                    + userId);
        } catch (Exception e) {
            responseEntity =
                    new ResponseEntity<TechnicalServiceDto>(
                            HttpStatus.NO_CONTENT);
            LOGGER.error("Error while getting a Technical Service by"
                    + " User`s id: " + userId
                    + " Details: " + e.getMessage());
        }

        return responseEntity;
    }

    /**
     * Method for getting Technical Service bu User ID.
     */
    @GetMapping("/users/username/{username}/techservice")
    public ResponseEntity<TechnicalServiceDto> findTechnicalServiceByUsername(
            @PathVariable("username") final String username) {

        ResponseEntity<TechnicalServiceDto> responseEntity;

        try {
            responseEntity = new ResponseEntity<TechnicalServiceDto>(
                technicalServiceService.getTechnicalServiceDtoByUser(username),
                HttpStatus.OK);
            LOGGER.info("Successfully get a Technical Service by username: "
                    + username);
        } catch (Exception e) {
            responseEntity =
                    new ResponseEntity<TechnicalServiceDto>(
                            HttpStatus.NO_CONTENT);
            LOGGER.
                    error("Error while getting a Technical Service by username"
                            + " id: " + username
                            + " Details: " + e.getMessage());
        }

        return responseEntity;
    }

    /*
    Method for deleting Technical Service by ID
     */
    @DeleteMapping("/techservices/{techServiceId}")
    void deleteTechnicalService(@PathVariable final Long techServiceId) {
        try {
            technicalServiceService.deleteTechnicalService(techServiceId);
            LOGGER.info("Successfully deleted a Technical Service by"
                    + " id: " + techServiceId);
        } catch (Exception e) {
            LOGGER.error("Error while deleting a Technical Service by id: "
                    + techServiceId + " Details: " + e.getMessage());
        }
    }

    @GetMapping("/techservices/{techServiceId}/rating")
    Double getServicesRating(@PathVariable final Long techServiceId) {
        return servicesFeedbackService.getServicesRating(techServiceId);
    }

    @PostMapping("/techservices/{techServiceId}/feedback")
    void addFeedbackAboutTechnicalService(
            @PathVariable final Long techServiceId,
            @RequestBody final ServicesFeedbackInputDto
                    servicesFeedbackInputDto) {

        servicesFeedbackService.addFeedbackToService(servicesFeedbackInputDto);
        System.out.println(servicesFeedbackInputDto);
        LOGGER.info(servicesFeedbackInputDto);
    }

    @GetMapping("/techservices/{techServiceId}/feedback")
    ResponseEntity<List<ServicesFeedbackOutputDto>> getAllFeedbackAboutService(
            @PathVariable final Long techServiceId) {
        ResponseEntity<List<ServicesFeedbackOutputDto>> responseEntity;

        try {
            responseEntity = new ResponseEntity<>(
                    servicesFeedbackService.getServicesFeedback(techServiceId),
                    HttpStatus.OK);
            LOGGER.info("Successfully got feedback about Technical Service by"
                    + " id: " + techServiceId);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            LOGGER.warn("Can not get feedback about Technical Service by id: "
                    + techServiceId
                    + " Details: " + e.getMessage());
        }

        return responseEntity;
    }
}
