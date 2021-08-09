package com.project.gamelibrary.mapper;

import com.project.gamelibrary.dto.GameDTO;
import com.project.gamelibrary.entity.Game;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GameMapper {

    GameMapper INSTANCE = Mappers.getMapper(GameMapper.class);

    Game toModel(GameDTO gameDTO);
    GameDTO toDTO(Game game);
}