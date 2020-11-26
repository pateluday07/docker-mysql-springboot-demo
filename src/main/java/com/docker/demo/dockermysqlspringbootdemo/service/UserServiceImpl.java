package com.docker.demo.dockermysqlspringbootdemo.service;

import com.docker.demo.dockermysqlspringbootdemo.dto.UserDto;
import com.docker.demo.dockermysqlspringbootdemo.mapper.UserMapper;
import com.docker.demo.dockermysqlspringbootdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDto save(UserDto userDto) {
        log.info("Saving user {}", userDto);
        if (userDto.getId() != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A new user should not have an id");
        userDto = userMapper.toDto(userRepository.save(userMapper.toEntity(userDto)));
        log.info("Saved user {}", userDto);
        return userDto;
    }

    @Override
    @Transactional
    public UserDto update(UserDto userDto) {
        log.info("Updating user {}", userDto);
        if (userDto.getId() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "To update user please enter id");
        userDto = userMapper.toDto(userRepository.save(userMapper.toEntity(userDto)));
        log.info("Update user {}", userDto);
        return userDto;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getById(Long id) {
        log.info("Get user by id {}", id);
        return userMapper.toDto(userRepository
                .findById(id)
                .orElseThrow(() -> userNotFoundException(id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAll() {
        log.info("Get all users");
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("Delete user by id {}", id);
        if (!userRepository.existsById(id)) throw userNotFoundException(id);
        userRepository.deleteById(id);
        log.info("User successfully deleted fo the id {}", id);
    }

    private ResponseStatusException userNotFoundException(Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found for the id " + id);
    }
}
