package ua.ita.smartcarservice.controller.technicalservice;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.ita.smartcarservice.dto.booking.WorkerBySkillNameDto;
import ua.ita.smartcarservice.dto.booking.WorkerDto;
import ua.ita.smartcarservice.dto.booking.WorkerWithSkillDto;
import org.springframework.beans.factory.annotation.Autowired;
import ua.ita.smartcarservice.dto.technicalservice.WorkerSkillDto;
import ua.ita.smartcarservice.service.UserService;
import ua.ita.smartcarservice.service.booking.WorkDependencyService;
import ua.ita.smartcarservice.service.feedback.WorkersRatingsService;
import ua.ita.smartcarservice.service.impl.booking.WorkInfo;
import ua.ita.smartcarservice.service.technicalservice.WorkerService;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Controller for Worker.
 * Worker - type oof user, but it needs some
 * additional opportunities.
 */
@RequestMapping("/api")
@RestController
public class WorkerController {

    @Autowired
    private UserService userService;

    @Autowired
    private WorkerService workerService;

    @Autowired
    private WorkersRatingsService workersRatingsService;

    @Autowired
    private WorkDependencyService workDependencyService;

    private static final Logger LOGGER =
            Logger.getLogger(MethodHandles.
                    lookup().lookupClass().getSimpleName());

    /**
     * Method get data about chooses works and
     * find optimal required time and workers schedule.
     *
     * @param workerWithSkillDto - info about chooses works
     * @return dto object with contains info about workers
     */
    @PostMapping("/workers/skill")
    public ResponseEntity<WorkerBySkillNameDto> findAllByCarAndSto(
            @RequestBody final WorkerWithSkillDto workerWithSkillDto) {

        WorkInfo workInfo =
                workDependencyService.
                        findWorkInfo(workerWithSkillDto.getWorksName());

        Map<String, List<WorkerDto>> workersBySkillName = workerWithSkillDto.getSkillsName().stream().collect(
                Collectors.toMap(s -> s,
                        s -> workerService.findByCarIdAndWorkersSkill(s, workerWithSkillDto.getCarId()))
        );

        WorkerBySkillNameDto workerBySkillNameDto = new WorkerBySkillNameDto();
        workerBySkillNameDto.setWorkersList(workersBySkillName);
        workerBySkillNameDto.setRequiredTime(workInfo.getRequiredTime());
        workerBySkillNameDto.setWorksInfo(workInfo.getWorkInfo());

        return new ResponseEntity<>(workerBySkillNameDto, HttpStatus.OK);
    }

    /**
     * Method for deleting worker by id.
     *
     * @param id
     * @return ResponseEntity with status
     */
    @DeleteMapping("/workers/{id}")
    public ResponseEntity deleteWorkerById(@PathVariable final Long id) {
        ResponseEntity responseEntity;

        try {
            LOGGER.info("Trying to delete worker by id: " + id);
            this.workerService.deleteWorker(id);
            LOGGER.info("Successfully deleted worker by id: " + id);
            responseEntity = new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity(HttpStatus.BAD_REQUEST);
            LOGGER.error("Can not delete worker by id: " + id
                    + " Ex:" + e.getMessage());
        }

        return responseEntity;
    }

    /**
     * Method for giving a rating for Worker by if.
     *
     * @param id
     * @param rate
     */
    @PostMapping("/workers/{id}/rating/{rate}")
    public void addRatingToWorker(@PathVariable final Long id,
                                  @PathVariable final Integer rate) {
        try {
            workersRatingsService.addRatingToWorker(id, rate);
            LOGGER.info("Rating: " + rate + " added ro worker id:" + id);
        } catch (Exception e) {
            LOGGER.error("Error while adding a rating to worker: " + id
                    + " Details: " + e);
        }
    }

    /**
     * Method returns average rating of Worker by id.
     *
     * @param id
     * @return rating
     */
    @GetMapping("/workers/{id}/rating")
    public ResponseEntity<Double> getWorkersRating(
            @PathVariable final Long id) {
        ResponseEntity<Double> responseEntity;

        try {
            responseEntity = new ResponseEntity<>(
                    workersRatingsService.getAvgWorkersRating(id),
                    HttpStatus.OK);
            LOGGER.info("Successfully got rating of worker id:" + id);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            LOGGER.error("Error while getting a rating if worker id:" + id
                    + " Details: " + e);
        }

        return responseEntity;
    }

    /**
     * Method returns DTO of Worker with Skill by its id.
     *
     * @param id
     * @return WorkerWithSkillDto
     */
    @GetMapping("/workers/{id}")
    public ResponseEntity<WorkerSkillDto> getWorkerById(
            @PathVariable final Long id) {
        ResponseEntity<WorkerSkillDto> responseEntity;

        try {
            responseEntity = new ResponseEntity<>(
                    workerService.getWorkerSkillDtoById(id),
                    HttpStatus.OK);
            LOGGER.info("Successfully get a Worker with skill DTO by id:" + id);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            LOGGER.error("Error while getting a Worker with skill DTO by"
                    + " id:" + id
                    + " Details: " + e);
        }

        return responseEntity;
    }
}

