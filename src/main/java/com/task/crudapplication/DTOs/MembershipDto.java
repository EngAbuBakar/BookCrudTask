package com.task.crudapplication.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MembershipDto {
    private Long id;
    @NotNull(message = "tierName should not be null")
    private String tierName;
}
