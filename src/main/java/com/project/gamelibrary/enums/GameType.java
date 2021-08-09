package com.project.gamelibrary.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GameType {

    RPG("Rpg"),
    ACTION("Action"),
    TERROR("Terror"),
    SIMULATION("Simulation"),
    FPS("FPS"),
    PUZZLE("Puzzle"),
    SPORT("Sport");

    private final String description;
}
