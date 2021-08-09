package com.project.gamelibrary.controller;

import com.project.gamelibrary.dto.GameDTO;
import com.project.gamelibrary.exception.GameAlreadyRegisteredException;
import com.project.gamelibrary.exception.GameNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Api("Manages game library")
public interface GameControllerDoc {

    @ApiOperation(value = "Game creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success Game creation"),
            @ApiResponse(code = 400, message = "Missing required fields or wrong field range value.")
    })
    GameDTO createGame(GameDTO gameDTO) throws GameAlreadyRegisteredException;

    @ApiOperation(value = "Returns Game found by a given name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success Game found in the system"),
            @ApiResponse(code = 404, message = "Game with given name not found.")
    })
    GameDTO findByName(@PathVariable String name) throws GameNotFoundException;

    @ApiOperation(value = "Returns a list of all Games registered in the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all Games registered in the system"),
    })
    List<GameDTO> listGames();

    @ApiOperation(value = "Delete a Game found by a given valid Id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success Game deleted in the system"),
            @ApiResponse(code = 404, message = "Game with given id not found.")
    })
    void deleteById(@PathVariable Long id) throws GameNotFoundException;

}
