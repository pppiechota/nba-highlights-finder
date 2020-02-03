package pl.piotrpiechota.nbahighlightsfinder.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pl.piotrpiechota.nbahighlightsfinder.dto.ScheduledGameDto;

@RestController
public class HomeController {

    @RequestMapping("/get-games")
    @ResponseBody
    public String getGames(){
        String url = "http://www.balldontlie.io/api/v1/games?team_ids[]=1&start_date=2020-02-09&end_date=2020-02-09";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ScheduledGameDto> responseEntity = restTemplate.getForEntity(url, ScheduledGameDto.class);
        ScheduledGameDto scheduledGame = responseEntity.getBody();
        assert scheduledGame != null;
        System.out.println(scheduledGame.getTotalCount());
        System.out.println(scheduledGame.getDate());
        System.out.println(scheduledGame.getHomeTeam());
        System.out.println(scheduledGame.getVisitorTeam());
        return "some result";
    }

}
