package com.task.crudapplication.DTOs;

import com.task.crudapplication.Entity.Membership;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotNull
    private String name;
    @Email(message = "invalid email address")
    private String email;
    private Membership membership;
}
