package com.task.crudapplication.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String tier;
//    @Column(name = "user_id")
//    private Long userId;
    @JsonIgnore
    @ManyToMany(mappedBy = "assignedBooks",fetch = FetchType.LAZY)
    private List<User>userList=new ArrayList<>();



}
