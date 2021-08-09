package com.project.gamelibrary.dto;

import com.project.gamelibrary.enums.GameType;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameDTO {

    private Long id;

    @NotEmpty
    @Size(min = 1, max = 50)
    private String name;

    @NotEmpty
    @Size(min = 4, max = 4)
    private String launchAge;

    @NotEmpty
    @Size(min = 1, max = 50)
    private String companyName;

    @Enumerated(EnumType.STRING)
    private GameType type;


}
