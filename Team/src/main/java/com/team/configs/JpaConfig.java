package com.team.configs;


import com.team.entities.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class JpaConfig implements AuditorAware<String> {
    final HttpServletRequest req;

    @Override
    public Optional getCurrentAuditor() {
        Object playerObj = req.getSession().getAttribute("player");
        if (playerObj != null) {
            System.out.println("JpaConfig call");
            // adminObj içinde admin nesnesi olup olmadığı kontrol edilir.
            if (playerObj instanceof Player) {
                Player player = (Player) playerObj;
                Optional<String> optional = Optional.of(player.getEmail());
                return optional;
            }
        }
        return Optional.empty();
    }
}