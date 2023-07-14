package com.task.crudapplication.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MembershipDto {
    private Long id;
    @NotNull(message = "tierName should not be null")
    private String tierName;
}
