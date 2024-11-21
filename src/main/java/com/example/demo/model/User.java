package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends org.openapitools.model.User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.UUID
    )
    private UUID userId;

    @Size(min = 2, max = 20)
    @NotBlank
    private String firstName;

    @Size(min = 2, max = 20)
    private String middleName;

    @Size(min = 2, max = 20)
    @NotBlank
    private String lastName;

    @Max(120)
    @Min(12)
    private Integer age;

    @Email
    private String email;
}
