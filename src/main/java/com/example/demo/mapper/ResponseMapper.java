package com.example.demo.mapper;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.model.Address;
import com.example.demo.model.User;
import org.mapstruct.*;
import org.openapitools.model.Response;

import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ResponseMapper {
    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "middleName", source = "user.middleName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "age", source = "user.age")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "country", source = "address.country", qualifiedByName = "defaultCountry")
    @Mapping(target = "building", source = "address.building")
    @Mapping(target = "road", source = "address.road")
    @Mapping(target = "block", source = "address.block")
    @Mapping(target = "apt", source = "address.apt")
    ResponseDTO mapToResponse(Address address, User user);

    org.openapitools.model.ResponseDTO responseToResponse(ResponseDTO responseDTO);


    @Named("defaultCountry")
    default String defaultCountry(String country) {
        return Optional.ofNullable(country).orElse("Bahrain");
    }
}
