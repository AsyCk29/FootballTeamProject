package com.team.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class TeamB extends  Base {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToMany
    List<Player> player;
}
