package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address extends org.openapitools.model.Address {
    @Id
    @GeneratedValue(
            strategy = GenerationType.UUID
    )
    private UUID addressId;
    private UUID userId;
    private Integer building;
    private Integer road;
    private Integer block;
    private Integer apt;
    private String country = "Bahrain";
}
