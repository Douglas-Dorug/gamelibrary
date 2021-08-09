package com.project.gamelibrary.service;

import com.project.gamelibrary.dto.GameDTO;
import com.project.gamelibrary.entity.Game;
import com.project.gamelibrary.exception.GameAlreadyRegisteredException;
import com.project.gamelibrary.exception.GameNotFoundException;
import com.project.gamelibrary.mapper.GameMapper;
import com.project.gamelibrary.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GameService {

    private final GameRepository gameRepository;
    private final GameMapper gameMapper = GameMapper.INSTANCE;

    public GameDTO createGame(GameDTO gameDTO) throws GameAlreadyRegisteredException{
        verifyIfIsAlreadyRegistered(gameDTO.getName());
        Game game = gameMapper.toModel(gameDTO);
        Game savedGame = gameRepository.save(game);
        return gameMapper.toDTO(savedGame);
    }
    public GameDTO findByName(String name) throws GameNotFoundException{
        Game foundGame = gameRepository.findByName(name)
                .orElseThrow(() -> new GameNotFoundException(name));
        return gameMapper.toDTO(foundGame);
    }
    public List<GameDTO> listAll(){
        return gameRepository.findAll()
                .stream()
                .map(gameMapper::toDTO)
                .collect(Collectors.toList());
    }

    private void verifyIfIsAlreadyRegistered(String name) throws GameAlreadyRegisteredException{
        Optional<Game> optSaverGame = gameRepository.findByName(name);
        if (optSaverGame.isPresent()){
            throw new GameAlreadyRegisteredException(name);
        }
    }

    private Game verifyIfExists(Long id) throws GameNotFoundException{
        return gameRepository.findById(id).orElseThrow(() -> new GameNotFoundException(id));
    }

}
