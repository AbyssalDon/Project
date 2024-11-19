package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "quote")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Quote {
    @Id
    private String _id;
    private String content;
    private String[] tags;
    private String authorSlug;
    private Integer length;
    private Date dateAdded;
    private Date dateModified;
}
