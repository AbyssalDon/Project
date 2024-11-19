package com.example.demo.dto;

import lombok.Data;

//TODO use better names for dtos like userdto
@Data
public class UserDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    private Integer age;
    private String email;
}
