package com.team.repositories;

import com.team.entities.Player;
import com.team.entities.TeamA;
import com.team.projections.ITeamAcat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeamRepositoryA extends JpaRepository<TeamA, Long> {
    @Query(value = "select TP.PLAYER_FID ,P2.NAME,P2.LASTNAME,P2.EMAIL,P2.AGE   from TEAMA as TA inner join TEAMA_PLAYER TP on TA.ID = TP.TEAMA_ID inner join PLAYER P2 on P2.FID = TP.PLAYER_FID ",nativeQuery = true)
    List<ITeamAcat> allTeamAcat(Pageable pageable);
}
