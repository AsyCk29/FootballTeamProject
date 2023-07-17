package com.team.entities.dto;

import com.team.entities.Player;
import lombok.Data;

import java.util.List;

@Data
public class TeamYedekKAdroDto {
    List<Player> teamAYedek;
    List<Player> teamBYedek;
}
