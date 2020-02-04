package pl.piotrpiechota.nbahighlightsfinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import pl.piotrpiechota.nbahighlightsfinder.dto.ScheduledGameDto;
import pl.piotrpiechota.nbahighlightsfinder.entity.Game;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class GameController {

    @RequestMapping("/get-games/{teamID}")
    public String getGames(Model model, @PathVariable Integer teamID) {
        String url = "http://www.balldontlie.io/api/v1/games?team_ids[]=" + teamID + "&start_date=2020-02-09&end_date=2020-02-09";

        RestTemplate restTemplate = new RestTemplate();
        ScheduledGameDto scheduledGame = restTemplate.getForEntity(url, ScheduledGameDto.class).getBody();

        if(!scheduledGame.isPlayed()){
            return "game";
        }

        String dateFromApi = scheduledGame.getDate();
        LocalDate convertedDate = LocalDateTime
                .parse(dateFromApi, DateTimeFormatter.ISO_DATE_TIME)
                .toLocalDate();

        Game game = new Game();
        game.setDate(convertedDate);
        game.setHomeTeam(scheduledGame.getHomeTeam());
        game.setVisitorTeam(scheduledGame.getVisitorTeam());

        model.addAttribute("game", game);
        return "game";
    }


}
