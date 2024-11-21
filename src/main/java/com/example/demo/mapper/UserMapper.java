package com.example.demo.mapper;

import com.example.demo.dto.FullnameDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.openapitools.model.UsernoidDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {
    FullnameDTO userToFullname(User user);
    UserDTO userToUserNoId(User user);
    org.openapitools.model.User userToUser(User user);
    UsernoidDTO userNoIdToUserNoId(UserDTO userDTO);
    org.openapitools.model.FullnameDTO fullnameToFullname(FullnameDTO fullnameDTO);
    User userToUser(org.openapitools.model.User user);
}
