package com.example.demo.mapper;

import com.example.demo.dto.FullnameDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {
    FullnameDTO userToFullname(User user);
    UserDTO userToUserNoId(User user);


}
