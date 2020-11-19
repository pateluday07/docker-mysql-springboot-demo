package com.docker.demo.dockermysqlspringbootdemo.controller;

import com.docker.demo.dockermysqlspringbootdemo.dto.UserDto;
import com.docker.demo.dockermysqlspringbootdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> save(@Valid @RequestBody UserDto userDto) {
        log.info("Rest API To Save User {}", userDto);
        return ResponseEntity.ok(userService.save(userDto));
    }

    @PutMapping
    public ResponseEntity<UserDto> update(@Valid @RequestBody UserDto userDto) {
        log.info("Rest API To Update User {}", userDto);
        return ResponseEntity.ok(userService.update(userDto));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        log.info("Rest API To Get User By Id {}", id);
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping()
    public List<UserDto> getAll() {
        log.info("Rest API To Get All Users");
        return userService.getAll();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        log.info("Rest API To Delete User By Id {}", id);
        userService.deleteById(id);
        return ResponseEntity.ok("User Deleted Successfully");
    }

}
