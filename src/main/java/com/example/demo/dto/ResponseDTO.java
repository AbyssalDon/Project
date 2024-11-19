package com.example.demo.dto;

import lombok.Data;

@Data
public class ResponseDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    private Integer age;
    private String email;
    private String country;
    private Integer building;
    private Integer road;
    private Integer block;
    private Integer apt;
}
