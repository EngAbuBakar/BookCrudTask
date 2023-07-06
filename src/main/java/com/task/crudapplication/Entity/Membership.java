package com.task.crudapplication.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Membership {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    private String tierName;
}
