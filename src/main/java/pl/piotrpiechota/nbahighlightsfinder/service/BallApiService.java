package pl.piotrpiechota.nbahighlightsfinder.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.piotrpiechota.nbahighlightsfinder.dto.ScheduleDto;
import pl.piotrpiechota.nbahighlightsfinder.entity.Game;
import pl.piotrpiechota.nbahighlightsfinder.entity.Team;
import pl.piotrpiechota.nbahighlightsfinder.repository.TeamRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BallApiService {

    private final int YESTERDAY = 1;
    private final TeamRepository teamRepo;
    private final String API_URL_DAY = "http://www.balldontlie.io/api/v1/games?start_date=";
    private final String API_URL_TEAM = "http://www.balldontlie.io/api/v1/games?team_ids[]=";

    public BallApiService(TeamRepository teamRepo) {
        this.teamRepo = teamRepo;
    }

    public List<Game> getGamesFromDate(LocalDate date) {
        String queryUrl = API_URL_DAY + date + "&end_date=" + date;

        ScheduleDto scheduleDto = getScheduleDto(queryUrl);
        assert scheduleDto != null : "Returned JSON is null";

        return scheduleDto.wasPlayed() ? scheduleDto.getGames() : new ArrayList<>();
    }

    public List<Game> getLastNightsGames() {
        LocalDate lastDay = LocalDate.now().minusDays(YESTERDAY);
        return getGamesFromDate(lastDay);
    }

    public List<Game> getTeamGamesFromLastMonth(Team team) {
        LocalDate firstDay = LocalDate.now().minusDays(30);
        LocalDate lastDay = LocalDate.now().minusDays(YESTERDAY);
        String queryUrl = API_URL_TEAM + team.getId() + "&start_date=" + firstDay + "&end_date=" + lastDay;

        ScheduleDto scheduleDto = getScheduleDto(queryUrl);
        assert scheduleDto != null : "Returned JSON is null";

        scheduleDto.getGames().sort((g1, g2)-> g2.getDate().compareTo(g1.getDate()));
        return scheduleDto.wasPlayed() ? scheduleDto.getGames() : new ArrayList<>();
    }

//    public List<Game> getTeamGamesFromMonth(Team team) {
//        LocalDate lastDay = LocalDate.now().minusDays(YESTERDAY);
//        String queryUrl = API_URL_TEAM + team.getId() + "&start_date=" + firstDay + "&end_date=" + lastDay;
//
//        ScheduleDto scheduleDto = getScheduleDto(queryUrl);
//        assert scheduleDto != null : "Returned JSON is null";
//
//        scheduleDto.getGames().sort((g1, g2)-> g2.getDate().compareTo(g1.getDate()));
//        return scheduleDto.wasPlayed() ? scheduleDto.getGames() : new ArrayList<>();
//    }

    private ScheduleDto getScheduleDto(String queryUrl) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(queryUrl, ScheduleDto.class)
                .getBody();
    }
}
