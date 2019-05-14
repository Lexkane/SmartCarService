package ua.ita.smartcarservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.UserDto;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.repository.UserRepository;
import ua.ita.smartcarservice.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    public List<UserDto> findAll() {
        List<UserDto> userDtos = new ArrayList<>();
        for (UserEntity user : userRepository.findAll()) {
            userDtos.add(getUserDto(user));
        }
        return userDtos;
    }

    public UserDto getUserById(Long id) {
        UserEntity user = userRepository.getUserById(id);
        UserDto carOwnerDto = getUserDto(user);
        return carOwnerDto;
    }

    public UserDto findByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username).get();
        UserDto userDto = getUserDto(user);
        return userDto;
    }

    public UserEntity findUser(String username) {
        UserEntity user = userRepository.findByUsername(username).get();
        return user;
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);

    }

    @Override
    public void updateUserById(Long id, UserEntity userEntity) {
        userEntity.setId(id);
        userRepository.save(userEntity);

    }

    public UserDto getUserDto(UserEntity user) {
        UserDto userDto = new UserDto(user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getFullName(),
                user.getNumberPhone());
        return userDto;
    }

    @Override
    public UserEntity findByUserName(String username) {
        return userRepository.findByUsername(username).get();
    }

    @Override
    public UserEntity findById(Long id) {
        return userRepository.getUserById(id);
    }

}
