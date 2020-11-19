package com.docker.demo.dockermysqlspringbootdemo.mapper;

import com.docker.demo.dockermysqlspringbootdemo.dto.UserDto;
import com.docker.demo.dockermysqlspringbootdemo.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(UserDto userDto);

    UserDto toDto(UserEntity userEntity);
}
