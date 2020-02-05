package pl.piotrpiechota.nbahighlightsfinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.SessionScope;
import pl.piotrpiechota.nbahighlightsfinder.dto.ScheduleDto;
import pl.piotrpiechota.nbahighlightsfinder.entity.Game;
import pl.piotrpiechota.nbahighlightsfinder.service.YoutubeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionScope
public class HomeController {
    private final YoutubeService youtubeService;

    public HomeController(YoutubeService youtubeService) {
        this.youtubeService = youtubeService;
    }

    @RequestMapping("/")
    public String getHomepage(HttpServletRequest request) {
        LocalDate lastDay = LocalDate.now().minusDays(1);

        String url = "http://www.balldontlie.io/api/v1/games?start_date=" + lastDay + "&end_date=" + lastDay;

        RestTemplate restTemplate = new RestTemplate();
        ScheduleDto scheduleDto = restTemplate.getForEntity(url, ScheduleDto.class).getBody();

        if (!scheduleDto.wasPlayed()) {
            return "game";
        }

        List<Game> scheduledGames = scheduleDto.getGames();
        request.getSession().setAttribute("schedule",scheduledGames);

        return "home";
    }

    @RequestMapping("/game")
    public String getGame(@RequestParam Integer gameId, HttpSession session, Model model){
        List<Game> schedule;
        if (session.getAttribute("schedule")==null){
            schedule = new ArrayList<>();
        } else {
            schedule = (List<Game>) session.getAttribute("schedule");
        }

        Game clickedGame = schedule.get(gameId);
        String videoId = youtubeService.executeSearch(clickedGame);
        model.addAttribute("video", videoId);
        model.addAttribute("game", clickedGame);
        return "game";
    }
}
