package ua.ita.smartcarservice.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ita.smartcarservice.dto.UserDto;
import ua.ita.smartcarservice.dto.feedback.ServicesFeedbackOutputDto;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.service.UserService;
import ua.ita.smartcarservice.service.feedback.FeedbackToLeaveService;
import ua.ita.smartcarservice.service.feedback.ServicesFeedbackService;


@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ServicesFeedbackService servicesFeedbackService;

    @Autowired
    private FeedbackToLeaveService feedbackToLeaveService;

    private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    /* Get user by id */
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    /* Get all users */
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> findAll() {
        List<UserDto> users = userService.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /*Get user by username */
    @GetMapping("/user/{username}/username")
    public ResponseEntity<UserDto> findByUsername(@PathVariable String username) {
        UserDto userDto = userService.findByUsername(username);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    /* Delete user by id */
    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody UserEntity userEntity) {
        userService.createUser(userEntity);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, UserEntity userEntity) {
        userService.updateUserById(id, userEntity);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    /**
     * Method returns all the feedback this user ever left
     *
     * @param userName
     * @return DTO with feedback
     */
    @GetMapping("/users/{userName}/feedback")
    public ResponseEntity<List<ServicesFeedbackOutputDto>> getAllUsersFeedback(@PathVariable String userName) {
        ResponseEntity<List<ServicesFeedbackOutputDto>> responseEntity;

        try {
            responseEntity = new ResponseEntity<List<ServicesFeedbackOutputDto>>(
                    servicesFeedbackService.getUsersFeedback(userName), HttpStatus.OK);
            logger.info("Successfully got feedback from User by username: " + userName);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            logger.warn("Can not get feedback from User by username: " + userName + " Details: " + e.getMessage());
        }

        return responseEntity;
    }

    /**
     * This method checks if this user have to leave
     * any feedback about workers, and returns list of workers ids
     * @param username
     * @return List with Workers id
     */
    @GetMapping("/users/{username}/leavefeedback")
    public ResponseEntity<List<Long>> findWorkersForFeedbackByUsername(@PathVariable String username) {
        ResponseEntity<List<Long>> responseEntity;

        responseEntity = new ResponseEntity<>(
                feedbackToLeaveService.findWorkersIdToLeaveFeedbackByUsername(username),
                HttpStatus.OK);

        return responseEntity;
    }

    /**
     * Method saves feedback left by this user
     * @param username
     * @param workersList
     * @return
     */
    @PostMapping("/users/{username}/leavefeedback")
    public ResponseEntity addFeedbackToLeaveByUsername(@PathVariable String username,
                                                        @RequestBody List<Long> workersList) {
        ResponseEntity responseEntity;

        try {
            this.feedbackToLeaveService.saveFeedbackToLeave(username, workersList);
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity(HttpStatus.BAD_REQUEST);
            logger.error("Can not add feedback to leave by username: " + username
                    + " Details: " + e.getMessage());
        }

        return responseEntity;
    }

    /**
     * Method deletes all the feedback suggestions
     * @param username
     * @return
     */
    @DeleteMapping("/users/{username}/leavefeedback")
    public ResponseEntity deleteFeedbackToLeaveByUsername(@PathVariable String username) {
        ResponseEntity responseEntity;

        try {
            this.feedbackToLeaveService.deleteByUsername(username);
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
            logger.error("Can not delete feedback to leave by username: " + username
                    + " Details: " + e.getMessage());
        }

        return responseEntity;
    }
}