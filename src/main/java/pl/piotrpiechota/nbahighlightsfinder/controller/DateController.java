package pl.piotrpiechota.nbahighlightsfinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.piotrpiechota.nbahighlightsfinder.entity.Game;
import pl.piotrpiechota.nbahighlightsfinder.service.BallApiService;
import pl.piotrpiechota.nbahighlightsfinder.service.YoutubeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DateController {
    private final String SEASONSTART = "2019-10-22";
    private final int YESTERDAY = 1;
    private final YoutubeService youtubeService;
    private final BallApiService ballApiService;

    public DateController(YoutubeService youtubeService, BallApiService ballApiService) {
        this.youtubeService = youtubeService;
        this.ballApiService = ballApiService;
    }

    @RequestMapping("/calendar")
    public String getDate(Model model) {
        String lastDay = LocalDate.now().minusDays(YESTERDAY).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        model.addAttribute("maxDate", lastDay);
        model.addAttribute("minDate", SEASONSTART);
        return "calendar";
    }

    @RequestMapping(value = "/calendar", method = RequestMethod.POST)
    public String getScheduleFromDate(@RequestParam String another, Model model, HttpServletRequest request) {
        LocalDate pickedDate = LocalDate.parse(another);

        List<Game> scheduledGames = ballApiService.getGamesFromDate(pickedDate);

        request.getSession().setAttribute("dateSchedule", scheduledGames);
        model.addAttribute("date", pickedDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        return "calendarGames";
    }

    @RequestMapping("/calendar/game")
    public String getGameFromDate(@RequestParam Integer id, HttpSession session, Model model) {
        List<Game> scheduledGames;
        if (session.getAttribute("dateSchedule") == null) {
            scheduledGames = new ArrayList<>();
        } else {
            scheduledGames = (List<Game>) session.getAttribute("dateSchedule");
        }

        Game clickedGame = scheduledGames.get(id);
        String videoId = youtubeService.executeSearch(clickedGame);

        model.addAttribute("video", videoId);
        model.addAttribute("game", clickedGame);
        return "game";
    }
}
