package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "quote")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Quote extends org.openapitools.model.Quote {
    @Id
    private String _id;
    private String content;
    private List<String> tags;
    private String authorSlug;
    private Integer length;
    private LocalDate dateAdded;
    private LocalDate dateModified;
}
