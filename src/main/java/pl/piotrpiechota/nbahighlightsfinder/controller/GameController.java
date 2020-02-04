package pl.piotrpiechota.nbahighlightsfinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import pl.piotrpiechota.nbahighlightsfinder.dto.ScheduledGameDto;
import pl.piotrpiechota.nbahighlightsfinder.entity.Game;
import pl.piotrpiechota.nbahighlightsfinder.service.YoutubeService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class GameController {

    private final YoutubeService youtubeService;

    public GameController(YoutubeService youtubeService) {
        this.youtubeService = youtubeService;
    }

    @RequestMapping("/get-games/{teamID}")
    public String getGames(Model model, @PathVariable Integer teamID) {
        LocalDate lastDay = LocalDate.now().minusDays(1);

        String url = "http://www.balldontlie.io/api/v1/games?team_ids[]="
                + teamID + "&start_date=" + lastDay + "&end_date=" + lastDay;

        RestTemplate restTemplate = new RestTemplate();
        ScheduledGameDto scheduledGame = restTemplate.getForEntity(url, ScheduledGameDto.class).getBody();

        Game game = new Game();
        if (!scheduledGame.wasPlayed()){
//            game.setPlayed(false);
            return "game";
        }

        String dateFromApi = scheduledGame.getDate();
        LocalDate convertedDate = LocalDateTime
                .parse(dateFromApi, DateTimeFormatter.ISO_DATE_TIME)
                .toLocalDate();

//        game.setPlayed(true);
        game.setDate(convertedDate);
        game.setHomeTeam(scheduledGame.getHomeTeam());
        game.setVisitorTeam(scheduledGame.getVisitorTeam());

        String videoId = youtubeService.executeSearch(game);

        model.addAttribute("video", videoId);
        model.addAttribute("game", game);
        return "game";
    }

//    @RequestMapping("/video")
//    @ResponseBody
//    public String showVideoId() {
//        Game game = new Game();
//        game.setHomeTeam("Atlanta");
//        game.setVisitorTeam("Boston");
//        game.setDate(LocalDate.now());
//
//        String response = YoutubeService.executeSearch(game);
//
//        return response;
//    }
}
