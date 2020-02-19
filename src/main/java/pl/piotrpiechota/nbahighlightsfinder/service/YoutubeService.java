package pl.piotrpiechota.nbahighlightsfinder.service;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import org.springframework.stereotype.Service;
import pl.piotrpiechota.nbahighlightsfinder.YoutubeUtil;
import pl.piotrpiechota.nbahighlightsfinder.entity.Game;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class YoutubeService {

    private static final String PROPERTIES_FILENAME = "youtube.properties";
    private final int ONLY_ITEM = 0;
    private static final long NUMBER_OF_VIDEOS_RETURNED = 15;
    private static final Duration LOWER_LIMIT = Duration.parse("PT08M00S");
    private static final Duration UPPER_LIMIT = Duration.parse("PT15M0S");

    public List<String> executeSearch(Game game) {

        List<String> requestedVideoIds = new ArrayList<>();

        try {
            YouTube youtube = new YouTube.Builder(YoutubeUtil.HTTP_TRANSPORT, YoutubeUtil.JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {}
            }).setApplicationName("nba-highlights-finder").build();

            String queryTerm = game.getHomeTeam() + " " + game.getVisitorTeam() + " highlights -1st";

            YouTube.Search.List searchList = youtube.search()
                    .list("id,snippet")
                    .setKey(getApiKey())
                    .setQ(queryTerm)
                    .setType("video")
                    .setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

            YouTube.Videos.List videoList = youtube.videos()
                    .list("contentDetails,snippet");

            List<SearchResult> searchResults = searchList
                    .execute()
                    .getItems();

            for (SearchResult searchResult : searchResults) {
                // Retrieve video ID
                String videoId = searchResult.getId().getVideoId();

                // Retrieve video object to get additional data
                Video video = videoList.setKey(getApiKey())
                        .setId(videoId)
                        .execute()
                        .getItems()
                        .get(ONLY_ITEM);

                Duration duration = Duration.parse(video.getContentDetails().getDuration());
                DateTime googleDate = video.getSnippet().getPublishedAt();

                if (timeIsRight(duration) && dateIsRight(googleDate, game.getDate())){
                    requestedVideoIds.add(videoId);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return requestedVideoIds;
    }

    private boolean dateIsRight(DateTime googleDate, Instant gameDate){
        String dateToParse = googleDate.toString();
        Instant publishedAt = Instant.parse(dateToParse);
        return !publishedAt.isBefore(gameDate) && publishedAt.isBefore(gameDate.plus(2, ChronoUnit.DAYS));
    }

    private boolean timeIsRight(Duration videoDuration){
        return videoDuration.compareTo(LOWER_LIMIT) > 0 && videoDuration.compareTo(UPPER_LIMIT) < 0;
    }

    private String getApiKey() {
        Properties properties = new Properties();
        try {
            InputStream in = YoutubeService.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
            properties.load(in);
            return properties.getProperty("youtube.apikey");
        } catch (IOException e) {
            System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
                    + " : " + e.getMessage());
        }
        return null;
    }
}
