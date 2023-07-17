package com.team.controllers;

import com.team.entities.Player;
import com.team.entities.dto.TeamDto;
import com.team.entities.dto.TeamKAdroDto;
import com.team.entities.dto.TeamYedekKAdroDto;
import com.team.services.TeamService;
import com.team.utils.Rest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/team")
public class TeamRestController {

    final TeamService teamService;

    @PostMapping("/teamRegister")
    public ResponseEntity teamRegister(@Valid @RequestBody TeamDto teamDto) {
        if (teamDto.getTeam().equals("A")) {
            return teamService.teamAInsert();
        } else
            return teamService.teamBInsert();
    }
    @GetMapping("/deleteAllTeamPlayer")
    public ResponseEntity deleteAllPlayer() {
         teamService.deleteAllTeamAPlayer();
         teamService.deleteAllTeamBPlayer();
         return ResponseEntity.ok("Delete Başarılı");
    }
    @GetMapping("/teamKadroCreate")
    public ResponseEntity teamKadroCreate() {
        TeamKAdroDto teamKAdroDto= new TeamKAdroDto();
        teamKAdroDto.setTeamA(teamService.teamACreate());
        teamKAdroDto.setTeamB(teamService.teamBCreate());

        return Rest.success(teamKAdroDto,"başarılı");
    }
    @GetMapping("/teamYedekCreate")
    public ResponseEntity teamYedekCreate() {
        TeamYedekKAdroDto teamYedekKAdroDto= new TeamYedekKAdroDto();
        teamYedekKAdroDto.setTeamAYedek(teamService.teamAYedekCreate());
        teamYedekKAdroDto.setTeamBYedek(teamService.teamBYedekCreate());

        return Rest.success(teamYedekKAdroDto,"başarılı");
    }


}
