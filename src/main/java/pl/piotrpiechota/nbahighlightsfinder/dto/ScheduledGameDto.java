package pl.piotrpiechota.nbahighlightsfinder.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduledGameDto {

    private String date;
    private String homeTeam;
    private String visitorTeam;
//    private Integer totalCount;
    private boolean played;

//    private String status;          // (7:30pm ET) minus 6 do naszego CET
//    private String homeTeamScore;
//    private String visitorTeamScore;

    @SuppressWarnings("unchecked")
    @JsonProperty("data")
    private void unpackNestedData(Map<String, Object>[] data) {
        this.date = (String) data[0].get("date");
        Map<String, Object> homeTeamMap = (Map<String, Object>) data[0].get("home_team");
        this.homeTeam = (String) homeTeamMap.get("name");
        Map<String, Object> visitorTeamMap = (Map<String, Object>) data[0].get("visitor_team");
        this.visitorTeam = (String) visitorTeamMap.get("name");
    }

    @JsonProperty("meta")
    private void unpackNestedMeta(Map<String, Integer> meta) {
        Integer totalCount = meta.get("total_count");
        if (totalCount == 0){
            this.played = false;
        } else {
            this.played = true;
        }
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

//    public Integer getTotalCount() {
//        return totalCount;
//    }
//
//    public void setTotalCount(Integer totalCount) {
//        this.totalCount = totalCount;
//    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getVisitorTeam() {
        return visitorTeam;
    }

    public void setVisitorTeam(String visitorTeam) {
        this.visitorTeam = visitorTeam;
    }
}