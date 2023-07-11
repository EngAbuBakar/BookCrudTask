package com.task.crudapplication.DTOs;

import com.task.crudapplication.Entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    @NotNull(message = "title should not be null")
    private String title;
    private String tier;
    private User user;
}
