package com.team.services;

import com.team.entities.Player;
import com.team.entities.TeamA;
import com.team.entities.dto.TeamDto;
import com.team.repositories.PlayerRepository;
import com.team.utils.Rest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerService {
    final PlayerRepository playerRepository;
    final TinkEncDec tinkEncDec;
    final HttpServletRequest req;

    public ResponseEntity playerRegister(Player player) {
        /**
         * Player için mail adresi ve ad soyadın sistemde var olup olmadığı kontrol edilir.
         */

        Optional<Player> optional = playerRepository.findByEmailEqualsIgnoreCase(player.getEmail());
        if (optional.isPresent()) {
            return Rest.fail(player, "Bu Mail Adresi Sistemde Tanımlı Bulunmaktadır", HttpStatus.BAD_REQUEST);
        }
        optional = playerRepository.findByNameEqualsIgnoreCaseAndLastnameEqualsIgnoreCase(player.getName(), player.getLastname());
        if (optional.isPresent()) {
            return Rest.fail(player, "Bu Ad ve Soyad Sistemde Tanımlı Bulunmaktadır.", HttpStatus.BAD_REQUEST);
        }

        try {
            player.setPassword(tinkEncDec.encrypt(player.getPassword()));
            /**
             * Player player1 = playerRepository.save(player);  kullanımı aşağıdaki kullanım yerine tersih edilebilir.
             */
            playerRepository.save(player);
            return Rest.success(player, "Başarılı");
        } catch (Exception ex) {
            return Rest.fail(player, ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity login(Player player){
        Optional<Player> optional = playerRepository.findByEmailEqualsIgnoreCase(player.getEmail());
        if (optional.isPresent()) {
            Player player1 = optional.get();
            String dbPass = tinkEncDec.decrypt(player1.getPassword());
            if(dbPass.equals(player.getPassword())) {
                req.getSession().setAttribute("player",player1);
                return Rest.success(player1,"Başarılı");

            }

        }
        return Rest.fail(player, "Bu Kullanıcı Sistemde Tanımlı Değil", HttpStatus.BAD_REQUEST);

    }
    public ResponseEntity deleteAllPlayer(){
        playerRepository.deleteAll();
        return ResponseEntity.ok().body("Delete Başarılı");
    }



}
