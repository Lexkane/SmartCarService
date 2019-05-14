package ua.ita.smartcarservice.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ua.ita.smartcarservice.dto.LoginForm;
import ua.ita.smartcarservice.dto.SignUpForm;
import ua.ita.smartcarservice.entity.RoleEntity;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.repository.RoleRepository;
import ua.ita.smartcarservice.repository.UserRepository;
import ua.ita.smartcarservice.security.JwtResponse;
import ua.ita.smartcarservice.security.ResponseMessage;
import ua.ita.smartcarservice.security.TokenProvider;
import ua.ita.smartcarservice.service.mail.MailService;

import javax.validation.Valid;
import java.lang.invoke.MethodHandles;
import java.util.HashSet;
import java.util.Set;



@RestController
@RequestMapping("api/auth")
public class AuthController {
    private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private MailService mailService;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        UserDetails userDetail = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt, userDetail.getUsername(), userDetail.getAuthorities()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail: Username is already exists!"), HttpStatus.BAD_REQUEST);
        }
        mailService.sendEmailRegistration(registerRequest.getUsername(), registerRequest.getPassword(), registerRequest.getEmail(), registerRequest.getFullName());
        UserEntity userEntity = new UserEntity(registerRequest.getUsername(), passwordEncoder.encode(registerRequest.getPassword()),
                registerRequest.getEmail(), registerRequest.getFullName(), registerRequest.getNumberPhone());
        Set<RoleEntity> roles = new HashSet<RoleEntity>();
        Set<String> strRole = registerRequest.getRole();
        strRole.forEach(role -> {
            RoleEntity roleEntity = roleRepository.findByName(role);
            roles.add(roleEntity);
        });
        userEntity.setRoles(roles);
        userRepository.save(userEntity);

        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
    }

}
