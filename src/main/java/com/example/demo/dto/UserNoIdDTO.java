package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//TODO use better names for dtos like userdto
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNoIdDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    private Integer age;
    private String email;
}
