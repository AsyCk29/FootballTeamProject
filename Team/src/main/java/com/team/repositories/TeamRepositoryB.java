package com.team.repositories;

import com.team.entities.Player;
import com.team.entities.TeamA;
import com.team.entities.TeamB;
import com.team.projections.ITeamAcat;
import com.team.projections.ITeamBcat;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeamRepositoryB extends JpaRepository<TeamB, Long> {
    @Query(value = "select TP.PLAYER_FID ,P2.NAME,P2.LASTNAME,P2.EMAIL,P2.AGE     from TEAMB as TB inner join TEAMB_PLAYER TP on TB.ID = TP.TEAMB_ID inner join PLAYER P2 on P2.FID = TP.PLAYER_FID ",nativeQuery = true)
    List<ITeamBcat> allTeamBcat(Pageable pageable);

}
