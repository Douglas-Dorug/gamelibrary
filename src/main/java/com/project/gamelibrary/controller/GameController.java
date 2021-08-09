package com.project.gamelibrary.controller;

import com.project.gamelibrary.dto.GameDTO;
import com.project.gamelibrary.entity.Game;
import com.project.gamelibrary.exception.GameAlreadyRegisteredException;
import com.project.gamelibrary.exception.GameNotFoundException;
import com.project.gamelibrary.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/games")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GameController {

    private final GameService gameService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GameDTO createGame(@RequestBody @Valid GameDTO gameDTO) throws GameAlreadyRegisteredException{
        return gameService.createGame(gameDTO);
    }

    @GetMapping("/{name}")
    public GameDTO findByName(@PathVariable String name) throws GameNotFoundException{
        return gameService.findByName(name);
    }

    @GetMapping
    public List<GameDTO> listGames(){
        return gameService.listAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws GameNotFoundException{
        gameService.deleteById(id);
    }


}