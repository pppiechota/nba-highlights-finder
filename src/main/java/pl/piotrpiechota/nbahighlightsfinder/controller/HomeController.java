package pl.piotrpiechota.nbahighlightsfinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.piotrpiechota.nbahighlightsfinder.entity.Game;
import pl.piotrpiechota.nbahighlightsfinder.entity.Team;
import pl.piotrpiechota.nbahighlightsfinder.repository.TeamRepository;
import pl.piotrpiechota.nbahighlightsfinder.service.BallApiService;
import pl.piotrpiechota.nbahighlightsfinder.service.YoutubeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private final YoutubeService youtubeService;
    private final BallApiService ballApiService;
    private final TeamRepository teamRepo;

    public HomeController(YoutubeService youtubeService, BallApiService ballApiService, TeamRepository teamRepo) {
        this.youtubeService = youtubeService;
        this.ballApiService = ballApiService;
        this.teamRepo = teamRepo;
    }

    @ModelAttribute("teams")
    public List<Team> getTeams() {
        return teamRepo.findAll();
    }

    @RequestMapping("/")
    public String getHomepage(HttpServletRequest request, Model model) {
        LocalDate lastDay = LocalDate.now().minusDays(1);

        List<Game> scheduledGames = ballApiService.getLastNightsGames();

        request.getSession().setAttribute("schedule", scheduledGames);
        model.addAttribute("date", lastDay);
        return "home";
    }

    @RequestMapping("/game")
    public String getGame(@RequestParam Integer id, HttpSession session, Model model) {
        List<Game> schedule;
        if (session.getAttribute("schedule") == null) {
            schedule = new ArrayList<>();
        } else {
            schedule = (List<Game>) session.getAttribute("schedule");
        }

        Game clickedGame = schedule.get(id);
        String videoId = youtubeService.executeSearch(clickedGame);

        model.addAttribute("video", videoId);
        model.addAttribute("game", clickedGame);
        return "game";
    }

    @RequestMapping("/about")
    public String getAbout() {
        return "about";
    }
}
