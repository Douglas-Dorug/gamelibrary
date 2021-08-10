package com.project.gamelibrary.builder;

import com.project.gamelibrary.dto.GameDTO;
import com.project.gamelibrary.enums.GameType;
import lombok.Builder;

@Builder
public class GameDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String name = "Skyrim";

    @Builder.Default
    private String launchAge = "2011";

    @Builder.Default
    private String companyName = "Bethesda";

    @Builder.Default
    private GameType type = GameType.RPG;

    public GameDTO toGameDTO(){
        return new GameDTO(id,
                name,
                launchAge,
                companyName,
                type);
    }
}