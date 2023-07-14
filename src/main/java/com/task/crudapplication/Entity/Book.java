package com.task.crudapplication.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String tier;
    @Column(name = "user_id")
    private Long userId;



}
