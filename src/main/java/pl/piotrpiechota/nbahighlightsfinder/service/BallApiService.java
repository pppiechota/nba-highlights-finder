package pl.piotrpiechota.nbahighlightsfinder.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.piotrpiechota.nbahighlightsfinder.dto.ScheduleDto;
import pl.piotrpiechota.nbahighlightsfinder.entity.Game;
import pl.piotrpiechota.nbahighlightsfinder.entity.Team;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
public class BallApiService {

    private final int YESTERDAY = 1;
    private final String API_URL_DATE = "http://www.balldontlie.io/api/v1/games?start_date=";
    private final String API_URL_TEAM = "http://www.balldontlie.io/api/v1/games?team_ids[]=";

    public List<Game> getGamesFromDate(LocalDate date) {
        String queryUrl = API_URL_DATE + date + "&end_date=" + date;

        ScheduleDto scheduleDto = getScheduleDto(queryUrl);
        assert scheduleDto != null : "Returned JSON is null";

        return scheduleDto.wasPlayed() ? scheduleDto.getGames() : new ArrayList<>();
    }

    public List<Game> getGamesFromLastNight() {
        LocalDate lastDay = LocalDate.now().minusDays(YESTERDAY);
        return getGamesFromDate(lastDay);
    }

    public List<Game> getTeamGamesFromMonth(Team team, String month) {
        YearMonth yearMonth = YearMonth.parse(month);
        String queryUrl = API_URL_TEAM + team.getId() + "&start_date=" + yearMonth.atDay(1) + "&end_date=" + yearMonth.atEndOfMonth();

        ScheduleDto scheduleDto = getScheduleDto(queryUrl);
        assert scheduleDto != null : "Returned JSON is null";

        scheduleDto.getGames().sort((g1, g2) -> g2.getDate().compareTo(g1.getDate()));
        return scheduleDto.wasPlayed() ? scheduleDto.getGames() : new ArrayList<>();
    }

    private ScheduleDto getScheduleDto(String queryUrl) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(queryUrl, ScheduleDto.class)
                .getBody();
    }
}
