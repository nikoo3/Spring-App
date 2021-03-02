package pl.sda.todoapp.mapper;

import pl.sda.todoapp.entity.UserEntity;
import pl.sda.todoapp.model.RegisterUserDto;

public class UserMapper {

    public static UserEntity mapRegisterUserDtoToUserEntity(RegisterUserDto dto) {

        UserEntity entity = new UserEntity();
        entity.setUsername(dto.getUsername());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPassword(dto.getPassword());

        return entity;
    }
}