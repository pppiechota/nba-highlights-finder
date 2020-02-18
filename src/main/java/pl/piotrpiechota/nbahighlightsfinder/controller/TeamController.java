package pl.piotrpiechota.nbahighlightsfinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.piotrpiechota.nbahighlightsfinder.entity.Game;
import pl.piotrpiechota.nbahighlightsfinder.entity.Team;
import pl.piotrpiechota.nbahighlightsfinder.repository.TeamRepository;
import pl.piotrpiechota.nbahighlightsfinder.service.BallApiService;
import pl.piotrpiechota.nbahighlightsfinder.service.YoutubeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TeamController {

    private final int YESTERDAY = 1;
    private final YoutubeService youtubeService;
    private final BallApiService ballApiService;
    private final TeamRepository teamRepo;

    public TeamController(YoutubeService youtubeService, BallApiService ballApiService, TeamRepository teamRepo) {
        this.youtubeService = youtubeService;
        this.ballApiService = ballApiService;
        this.teamRepo = teamRepo;
    }

    @ModelAttribute("teams")
    public List<Team> getTeams() {
        return teamRepo.findAll();
    }

    @RequestMapping("/teams")
    public String getTeamList(Model model) {
        List<Team> west = teamRepo.findAllByConference("West");
        List<Team> east = teamRepo.findAllByConference("East");
        model.addAttribute("westConference", west);
        model.addAttribute("eastConference", east);
        return "teams";
    }

    @RequestMapping("/teams/{id}")
    public String getTeamGames(@PathVariable Integer id, Model model) {
        Team team = teamRepo.findById(id)
                .orElse(new Team());

        String lastDay = LocalDate.now().minusDays(YESTERDAY).format(DateTimeFormatter.ofPattern("yyyy-MM"));
        String seasonStart = "2019-10";
        model.addAttribute("maxDate", lastDay);
        model.addAttribute("minDate", seasonStart);
        model.addAttribute("team", team);
        return "teamGames";
    }

    @RequestMapping(value = "/teams/{id}", method = RequestMethod.POST)
    public String getTeamGamesFromMonth(@PathVariable Integer id, @RequestParam String month,
                                        Model model, HttpServletRequest request) {
        Team team = teamRepo.findById(id)
                .orElse(new Team());

        List<Game> teamGamesFromMonth = ballApiService.getTeamGamesFromMonth(team, month);
        model.addAttribute("teamGamesList", teamGamesFromMonth);
        request.getSession().setAttribute("scheduleTeam", teamGamesFromMonth);
        model.addAttribute("team", team);
        return "teamMonth";
    }

    @RequestMapping("/teams/game")
    public String getTeamList(@RequestParam Integer id, HttpSession session, Model model) {
        List<Game> schedule;
        if (session.getAttribute("scheduleTeam") == null) {
            schedule = new ArrayList<>();
        } else {
            schedule = (List<Game>) session.getAttribute("scheduleTeam");
        }

        Game clickedGame = schedule.get(id);
        String videoId = youtubeService.executeSearch(clickedGame);
        model.addAttribute("video", videoId);
        model.addAttribute("game", clickedGame);
        return "game";
    }
}
