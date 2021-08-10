package com.project.gamelibrary.controller;

import com.project.gamelibrary.builder.GameDTOBuilder;
import com.project.gamelibrary.dto.GameDTO;
import com.project.gamelibrary.exception.GameAlreadyRegisteredException;
import com.project.gamelibrary.exception.GameNotFoundException;
import com.project.gamelibrary.mapper.GameMapper;
import com.project.gamelibrary.repository.GameRepository;
import com.project.gamelibrary.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collections;

import static com.project.gamelibrary.utils.JsonConvertionUtils.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class GameControllerTest {

    private static final String GAME_API_URL_PATH = "/api/v1/games";
    private static final long VALID_GAME_ID = 1L;
    private static final long INVALID_GAME_ID = 2L;

    private MockMvc mockMvc;

    @Mock
    private GameService gameService;


    @InjectMocks
    private GameController gameController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(gameController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPOSTIsCalledThenAGameIsCreated() throws Exception {
        //given
        GameDTO gameDTO = GameDTOBuilder.builder().build().toGameDTO();

        //when
        Mockito.when(gameService.createGame(gameDTO)).thenReturn(gameDTO);

        //then
        mockMvc.perform(post(GAME_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(gameDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(gameDTO.getName())))
                .andExpect(jsonPath("$.companyName", is(gameDTO.getCompanyName())))
                .andExpect(jsonPath("$.type", is(gameDTO.getType().toString())));
    }
    @Test
    void whenPOSTIsCalledWithoutRequiredFieldThenAnErrorIsReturned() throws Exception {
        //given
        GameDTO gameDTO = GameDTOBuilder.builder().build().toGameDTO();
        gameDTO.setCompanyName(null);

        //then
        mockMvc.perform(post(GAME_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(gameDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenGETIsCalledWithValidNameThenOkStatusIsReturned() throws Exception {
        //given
        GameDTO gameDTO = GameDTOBuilder.builder().build().toGameDTO();

        //when
        Mockito.when(gameService.findByName(gameDTO.getName())).thenReturn(gameDTO);

        //then
        mockMvc.perform(MockMvcRequestBuilders.get(GAME_API_URL_PATH + "/" + gameDTO.getName())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(gameDTO.getName())))
                .andExpect(jsonPath("$.companyName", is(gameDTO.getCompanyName())))
                .andExpect(jsonPath("$.type", is(gameDTO.getType().toString())));
    }

    @Test
    void whenGETIsCalledWithoutRegisteredNameThenNotFoundStatusIsReturned() throws Exception {
        //given
        GameDTO gameDTO = GameDTOBuilder.builder().build().toGameDTO();

        //when
        Mockito.when(gameService.findByName(gameDTO.getName())).thenThrow(GameNotFoundException.class);

        //then
        mockMvc.perform(MockMvcRequestBuilders.get(GAME_API_URL_PATH + "/" + gameDTO.getName())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenGETListWithGamesIsCalledThenOkStatusIsReturned() throws Exception {
        //given
        GameDTO gameDTO = GameDTOBuilder.builder().build().toGameDTO();

        //when
        Mockito.when(gameService.listAll()).thenReturn(Collections.singletonList(gameDTO));

        //then
        mockMvc.perform(MockMvcRequestBuilders.get(GAME_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(gameDTO.getName())))
                .andExpect(jsonPath("$[0].companyName", is(gameDTO.getCompanyName())))
                .andExpect(jsonPath("$[0].type", is(gameDTO.getType().toString())));
    }
}
