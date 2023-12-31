package com.team.services;

import com.team.entities.Player;
import com.team.entities.TeamA;
import com.team.entities.TeamB;
import com.team.entities.dto.TeamKAdroDto;
import com.team.projections.ITeamAcat;
import com.team.projections.ITeamBcat;
import com.team.repositories.TeamRepositoryA;
import com.team.repositories.TeamRepositoryB;
import com.team.utils.Genel;
import com.team.utils.Rest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamService {
    final TeamRepositoryA teamRepositoryA;
    final TeamRepositoryB teamRepositoryB;
    final PlayerService playerService;
    final HttpServletRequest req;


    public ResponseEntity teamAInsert() {

        Long fid = Genel.getFidFormSession(req);
        if (fid == null) {
            return Rest.fail(null, "Oturum Bilgisi Alınamadı !!!.", HttpStatus.BAD_REQUEST);
        }

        List <TeamB> teamPlayers = teamRepositoryB.findAll().stream().toList() ;

        if(!teamPlayers.isEmpty())

            for (int i = 0; i < teamPlayers.size(); i++) {
                System.out.println("PLAYER fid :: "+teamPlayers.get(i).getPlayer().get(i).getFid());
                if (teamPlayers.get(i).getPlayer().get(i).getFid().equals(fid))
                    return Rest.fail(teamPlayers.get(i), "Bu Oyuncu B Takımı Kadrosunda !!!.", HttpStatus.BAD_REQUEST);
            }


        List <TeamA> teamPlayers2 = teamRepositoryA.findAll().stream().toList() ;

        if(!teamPlayers2.isEmpty())

            for (int i = 0; i < teamPlayers2.size(); i++) {
                System.out.println("PLAYER fid :: "+teamPlayers2.get(i).getPlayer().get(i).getFid());
                if (teamPlayers2.get(i).getPlayer().get(i).getFid().equals(fid))
                    return Rest.fail(teamPlayers2.get(i), "Bu Oyuncu A Takımı Kadrosunda !!!.", HttpStatus.BAD_REQUEST);
            }

        TeamA teamA = new TeamA();
        try {
            Optional<Player> player = playerService.playerRepository.findById(fid);
            teamA.setPlayer(player.stream().toList());
            teamRepositoryA.save(teamA);

            return Rest.success(teamA, "Başarılı");
        } catch (Exception ex) {
            return Rest.fail(teamA, ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity deleteAllTeamAPlayer() {
        teamRepositoryA.deleteAll();
        return ResponseEntity.ok().body("Delete Başarılı");
    }

    public List<Player> teamACreate() {
        List<ITeamAcat> teamAcatList = getITeamAcatList();
        List<Player> teamAAsilList = new ArrayList<>();
        for (int i = 0; i < teamAcatList.size(); i++) {
            if (i > 5)
                break;
            playerService.playerRepository.findById(teamAcatList.get(i).getPLAYER_FID()).ifPresent(teamAAsilList::add);
        }

        return teamAAsilList;
    }
    public List<Player> teamAYedekCreate() {
        List<ITeamAcat> teamAcatList = getITeamAcatList();
        List<Player> teamAYedekList = new ArrayList<>();
        for (int i = 6; i < teamAcatList.size(); i++) {
            if (i > 8)
                break;
            playerService.playerRepository.findById(teamAcatList.get(i).getPLAYER_FID()).ifPresent(teamAYedekList::add);
        }

        return teamAYedekList;
    }

    public List<ITeamAcat> getITeamAcatList() {

        Sort sort = Sort.by("P2.AGE").descending();
        int pageCount = 0;
        Pageable pageable = PageRequest.of(pageCount, 1000, sort);

        return teamRepositoryA.allTeamAcat(pageable);

    }

    /**
     * TeamB Mothods
     */


    public ResponseEntity teamBInsert() {

        Long fid = Genel.getFidFormSession(req);
        if (fid == null) {
            return Rest.fail(null, "Oturum Bilgisi Alınamadı !!!.", HttpStatus.BAD_REQUEST);
        }

        List <TeamB> teamPlayers = teamRepositoryB.findAll().stream().toList() ;

        if(!teamPlayers.isEmpty())

        for (int i = 0; i < teamPlayers.size(); i++) {
            System.out.println("PLAYER fid :: "+teamPlayers.get(i).getPlayer().get(i).getFid());
            if (teamPlayers.get(i).getPlayer().get(i).getFid().equals(fid))
                return Rest.fail(teamPlayers.get(i), "Bu Oyuncu B Takımı Kadrosunda !!!.", HttpStatus.BAD_REQUEST);
        }

        List <TeamA> teamPlayers2 = teamRepositoryA.findAll().stream().toList() ;

        if(!teamPlayers2.isEmpty())

            for (int i = 0; i < teamPlayers2.size(); i++) {
                System.out.println("PLAYER fid :: "+teamPlayers2.get(i).getPlayer().get(i).getFid());
                if (teamPlayers2.get(i).getPlayer().get(i).getFid().equals(fid))
                    return Rest.fail(teamPlayers2.get(i), "Bu Oyuncu A Takımı Kadrosunda !!!.", HttpStatus.BAD_REQUEST);
            }


        TeamB teamB1 = new TeamB();
        Optional<Player> player = playerService.playerRepository.findById(fid);

        try {

            teamB1.setPlayer(player.stream().toList());
            teamRepositoryB.save(teamB1);
            return Rest.success(player, "Başarılı");

        } catch (Exception ex) {
            return Rest.fail(player, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }
    public List<ITeamBcat> getITeamBcatList() {

        Sort sort = Sort.by("P2.AGE").descending();
        int pageCount = 0;
        Pageable pageable = PageRequest.of(pageCount, 1000, sort);

        return teamRepositoryB.allTeamBcat(pageable);

    }

    public List<Player> teamBCreate() {
        List<ITeamBcat> teamBcatList = getITeamBcatList();
        List<Player> teamBAsilList = new ArrayList<>();
        for (int i = 0; i < teamBcatList.size(); i++) {
            if (i > 5)
                break;
            playerService.playerRepository.findById(teamBcatList.get(i).getPLAYER_FID()).ifPresent(teamBAsilList::add);
        }

        return teamBAsilList;
    }
    public List<Player> teamBYedekCreate() {
        List<ITeamBcat> teamBcatList = getITeamBcatList();
        List<Player> teamBYedekList = new ArrayList<>();
        for (int i = 6; i < teamBcatList.size(); i++) {
            if (i > 8)
                break;
            playerService.playerRepository.findById(teamBcatList.get(i).getPLAYER_FID()).ifPresent(teamBYedekList::add);
        }

        return teamBYedekList;
    }


    public ResponseEntity deleteAllTeamBPlayer() {
        teamRepositoryB.deleteAll();
        return ResponseEntity.ok().body("Delete Başarılı");
    }
}
