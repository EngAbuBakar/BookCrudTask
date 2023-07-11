package com.task.crudapplication.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class Membership {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String tierName;

}
