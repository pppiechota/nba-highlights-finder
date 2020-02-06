package pl.piotrpiechota.nbahighlightsfinder.service;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.VideoListResponse;
import org.springframework.stereotype.Service;
import pl.piotrpiechota.nbahighlightsfinder.YoutubeUtil;
import pl.piotrpiechota.nbahighlightsfinder.entity.Game;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

@Service
public class YoutubeService {

    private static final String PROPERTIES_FILENAME = "youtube.properties";
    private static final long NUMBER_OF_VIDEOS_RETURNED = 10;
    private static final Duration LOWER_LIMIT = Duration.parse("PT08M00S");
    private static final Duration UPPER_LIMIT = Duration.parse("PT15M0S");

    public String executeSearch(Game game) {
        String requestedVideoId = null;
        try {
            YouTube youtube = new YouTube.Builder(YoutubeUtil.HTTP_TRANSPORT, YoutubeUtil.JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {}
            }).setApplicationName("nba-highlights-finder").build();

            String apiKey = getProperties().getProperty("youtube.apikey");
            String queryTerm = game.getHomeTeam().getName() + " " + game.getVisitorTeam().getName()/*+" "+game.getDate().toString()*/;

            System.out.println("1 "+queryTerm);

            YouTube.Search.List search = youtube.search()
                    .list("id,snippet")
                    .setKey(apiKey)
                    .setQ(queryTerm)
                    .setType("video")
                    .setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

            System.out.println("2 "+search);
            List<SearchResult> searchResultList = search
                    .execute()
                    .getItems();

            for (SearchResult searchResult : searchResultList) {
                System.out.println("."+searchResult);
            }
            System.out.println("3 "+searchResultList);
            for (SearchResult searchResult : searchResultList) {
                String videoId = searchResult.getId().getVideoId();
                System.out.println("4 "+videoId);
                YouTube.Videos.List video = youtube.videos()
                        .list("contentDetails,snippet");
                System.out.println("5 "+video);
                VideoListResponse videoResponse = video.setKey(apiKey)
                        .setId(videoId)
                        .execute();
                System.out.println("6 "+videoResponse);
                Duration duration = Duration.parse(videoResponse.getItems().get(0).getContentDetails().getDuration());
                DateTime dateG = videoResponse.getItems().get(0).getSnippet().getPublishedAt();
                System.out.println(dateG.toString());
                System.out.println(dateIsRight(dateG,game));
                if (timeIsRight(duration) && dateIsRight(dateG,game)){
                    requestedVideoId = videoId;
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return requestedVideoId;
    }

    private boolean dateIsRight(DateTime googleDate, Game game){
        String dateToParse = googleDate.toString().substring(0,10);
        LocalDate publishedAt = LocalDate.parse(dateToParse);
        return publishedAt.isAfter(game.getDate().minusDays(2));
    }

    private boolean timeIsRight(Duration videoDuration){
        return videoDuration.compareTo(LOWER_LIMIT) > 0 && videoDuration.compareTo(UPPER_LIMIT) < 0;
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        try {
            InputStream in = YoutubeService.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
            properties.load(in);
        } catch (IOException e) {
            System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
                    + " : " + e.getMessage());
        }
        return properties;
    }
}
