package com.team.repositories;

import com.team.entities.Player;
import com.team.projections.ITeamAcat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByNameEqualsIgnoreCaseAndLastnameEqualsIgnoreCase(String name, String lastname);
    Optional<Player> findByEmailEqualsIgnoreCase(String email);





}