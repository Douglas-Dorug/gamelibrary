package com.project.gamelibrary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String launchDate;

    @Column(nullable = false)
    private String companyName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private String type;
}
