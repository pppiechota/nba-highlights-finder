package pl.piotrpiechota.nbahighlightsfinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.SessionScope;
import pl.piotrpiechota.nbahighlightsfinder.entity.Game;
import pl.piotrpiechota.nbahighlightsfinder.entity.Team;
import pl.piotrpiechota.nbahighlightsfinder.repository.TeamRepository;
import pl.piotrpiechota.nbahighlightsfinder.service.BallApiService;
import pl.piotrpiechota.nbahighlightsfinder.service.YoutubeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@SessionScope
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

    @RequestMapping("/teams")
    public String getTeamList() {
        return "teams";
    }

    @RequestMapping("/teams/{id}")
    public String getTeamGames(@PathVariable Integer id, Model model, HttpServletRequest request) {
        Team team = teamRepo.findById(id)
                .orElse(new Team());

        List<Game> teamGamesFromLastMonth = ballApiService.getTeamGamesFromLastMonth(team);
        model.addAttribute("teamGamesList", teamGamesFromLastMonth);
        request.getSession().setAttribute("scheduleTeam", teamGamesFromLastMonth);
        model.addAttribute("team", team);
        return "teamGames";
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

    @RequestMapping("/about")
    public String getAbout() {
        return "about";
    }

//    @RequestMapping("/test")
//    @ResponseBody
//    public String test(){
//        Game game = new Game();
//        game.setHomeTeam(teamRepo.findFirstByName("Pistons"));
//        game.setVisitorTeam(teamRepo.findFirstByName("Suns"));
//        game.setDate(LocalDate.of(2020,02,05));
//        String videoId = youtubeService.executeSearch(game);
//        System.out.println(videoId);
//        return videoId;
//    }
}
