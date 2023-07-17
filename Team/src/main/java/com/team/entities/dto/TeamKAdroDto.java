package com.team.entities.dto;

import com.team.entities.Player;
import lombok.Data;

import java.util.List;

@Data
public class TeamKAdroDto {
    List<Player> teamA;
    List<Player> teamB;
}
