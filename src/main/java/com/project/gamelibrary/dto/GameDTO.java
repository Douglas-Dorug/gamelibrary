package com.project.gamelibrary.dto;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameDTO {

    private Long id;

    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    @NotNull
    @Size(min = 4, max = 4)
    private String launchAge;

    @NotNull
    @Size(min = 1, max = 50)
    private String companyName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private String type;
}
