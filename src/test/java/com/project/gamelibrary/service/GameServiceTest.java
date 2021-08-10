package com.project.gamelibrary.service;

import com.project.gamelibrary.builder.GameDTOBuilder;
import com.project.gamelibrary.dto.GameDTO;
import com.project.gamelibrary.entity.Game;
import com.project.gamelibrary.exception.GameAlreadyRegisteredException;
import com.project.gamelibrary.exception.GameNotFoundException;
import com.project.gamelibrary.mapper.GameMapper;
import com.project.gamelibrary.repository.GameRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Assertions;


import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    private GameMapper gameMapper = GameMapper.INSTANCE;

    @InjectMocks
    private GameService gameService;

    @Test
    void whenGameInformedThenItShouldBeCreated() throws GameAlreadyRegisteredException {
        //given
        GameDTO expectedGameDTO = GameDTOBuilder.builder().build().toGameDTO();
        Game expectedSavedGame = gameMapper.toModel(expectedGameDTO);

        //when
        Mockito.when(gameRepository.findByName(expectedGameDTO.getName())).thenReturn(Optional.empty());
        Mockito.when(gameRepository.save(expectedSavedGame)).thenReturn(expectedSavedGame);

        //then
        GameDTO createdGameDTO = gameService.createGame(expectedGameDTO);

        //Hamcrest
        assertThat(createdGameDTO.getId(), is(equalTo(expectedGameDTO.getId())));
        assertThat(createdGameDTO.getName(), is(equalTo(expectedGameDTO.getName())));

//        //Junit
//        Assertions.assertEquals(expectedGameDTO.getId(), createdGameDTO.getId());
//        Assertions.assertEquals(expectedGameDTO.getName(), createdGameDTO.getName());



    }

    @Test
    void whenAlreadyRegisteredGameInformedThenAnExceptionShouldBeThrown() {
        //given
        GameDTO expectedGameDTO = GameDTOBuilder.builder().build().toGameDTO();
        Game duplicatedGame = gameMapper.toModel(expectedGameDTO);

        //when
        Mockito.when(gameRepository.findByName(expectedGameDTO.getName())).thenReturn(Optional.of(duplicatedGame));

        //then
        Assertions.assertThrows(GameAlreadyRegisteredException.class, () -> gameService.createGame(expectedGameDTO));


    }

    @Test
    void whenValidGameNameIsGivenThenReturnAGame() throws GameNotFoundException {
        //given
        GameDTO expectedFoundGameDTO = GameDTOBuilder.builder().build().toGameDTO();
        Game expectedFoundGame = gameMapper.toModel(expectedFoundGameDTO);

        //when
        Mockito.when(gameRepository.findByName(expectedFoundGame.getName())).thenReturn(Optional.of(expectedFoundGame));

        //then
        GameDTO foundGameDTO = gameService.findByName(expectedFoundGameDTO.getName());

        assertThat(foundGameDTO,is(equalTo(expectedFoundGameDTO)));
    }

    @Test
    void whenNotRegisteredGameNameIsGivenThenThrowAnException() throws GameNotFoundException {
        //given
        GameDTO expectedFoundGameDTO = GameDTOBuilder.builder().build().toGameDTO();

        //when
        Mockito.when(gameRepository.findByName(expectedFoundGameDTO.getName())).thenReturn(Optional.empty());

        //then
        Assertions.assertThrows(GameNotFoundException.class,() -> gameService.findByName(expectedFoundGameDTO.getName()));

    }

    @Test
    void whenListGameIsCalledThenReturnAListOfGames() {
        //given
        GameDTO expectedFoundGameDTO = GameDTOBuilder.builder().build().toGameDTO();
        Game expectedFoundGame = gameMapper.toModel(expectedFoundGameDTO);

        //when
        Mockito.when(gameRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundGame));

        //then
        List<GameDTO> foundGameDTO = gameService.listAll();

        assertThat(foundGameDTO, is(not(empty())));
        assertThat(foundGameDTO.get(0), is(equalTo(expectedFoundGameDTO)));
    }

    @Test
    void whenListGameIsCalledThenReturnAnEmptyListOfGames() {

        //when
        Mockito.when(gameRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        //then
        List<GameDTO> foundListGameDTO = gameService.listAll();

        assertThat(foundListGameDTO, is(empty()));
    }
}
