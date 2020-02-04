package pl.piotrpiechota.nbahighlightsfinder.service;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
import com.google.api.services.youtube.model.VideoListResponse;
import org.springframework.web.util.DefaultUriBuilderFactory;
import pl.piotrpiechota.nbahighlightsfinder.YoutubeUtil;
import pl.piotrpiechota.nbahighlightsfinder.entity.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class YoutubeSearch {

    private static final String PROPERTIES_FILENAME = "youtube.properties";
    private static final long NUMBER_OF_VIDEOS_RETURNED = 5;
    private static YouTube youtube;
    private static final Duration LOWER_LIMIT = Duration.parse("PT08M00S");
    private static final Duration UPPER_LIMIT = Duration.parse("PT15M0S");

    public static String executeSearch(Game game) {
        String requestedVideoId = null;
        try {
            youtube = new YouTube.Builder(YoutubeUtil.HTTP_TRANSPORT, YoutubeUtil.JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {}
            }).setApplicationName("nba-highlights-finder").build();

            String apiKey = getProperties().getProperty("youtube.apikey");
            String queryTerm = game.getHomeTeam() + " " + game.getVisitorTeam();

            YouTube.Search.List search = youtube.search()
                    .list("id,snippet")
                    .setKey(apiKey)
                    .setQ(queryTerm)
                    .setType("video")
                    .setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

            List<SearchResult> searchResultList = search
                    .execute()
                    .getItems();


            for (SearchResult searchResult : searchResultList) {
                String videoId = searchResult.getId().getVideoId();
                YouTube.Videos.List video = youtube.videos()
                        .list("contentDetails");
                VideoListResponse videoResponse = video.setKey(apiKey)
                        .setId(videoId)
                        .execute();
                Duration duration = Duration.parse(videoResponse.getItems().get(0).getContentDetails().getDuration());
                if (timeIsRight(duration)){
                    requestedVideoId = videoId;
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return requestedVideoId;
    }

    public static boolean timeIsRight(Duration videoDuration){
        return videoDuration.compareTo(LOWER_LIMIT) > 0 && videoDuration.compareTo(UPPER_LIMIT) < 0;
    }

    public static Properties getProperties() {
        Properties properties = new Properties();
        try {
            InputStream in = YoutubeSearch.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
            properties.load(in);
        } catch (IOException e) {
            System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
                    + " : " + e.getMessage());
        }
        return properties;
    }
}
