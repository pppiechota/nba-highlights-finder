package pl.piotrpiechota.nbahighlightsfinder.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamDto {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("abbreviation")
    private String abbreviation;
    @JsonProperty("city")
    private String city;
    @JsonProperty("conference")
    private String conference;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("name")
    private String name;

}
