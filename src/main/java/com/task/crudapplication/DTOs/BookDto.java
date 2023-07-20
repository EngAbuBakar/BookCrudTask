package com.task.crudapplication.DTOs;

import com.task.crudapplication.Entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private Long id;
    @NotNull(message = "title should not be null")
    private String title;
    private String tier;
    private UserDto userDto;
}
