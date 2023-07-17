package com.team.utils;

import com.team.entities.Player;
import com.team.entities.TeamA;
import com.team.repositories.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RequiredArgsConstructor
public class Genel {
    public static Long getFidFormSession(HttpServletRequest req){
        Object playerObj = req.getSession().getAttribute("player");
        if (playerObj == null) {
            Long fid = null;
            if (playerObj instanceof Player) {
                Player player = (Player) playerObj;
                fid = player.getFid();
            }
            return fid;
        }

        return null;
    }
}
