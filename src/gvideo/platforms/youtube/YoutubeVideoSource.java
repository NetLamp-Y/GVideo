package gvideo.platforms.youtube;

import gvideo.model.Video;
import gvideo.model.VideoSource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

public class YoutubeVideoSource implements VideoSource {

    private final Elements videoIdTags;

    public YoutubeVideoSource() throws IOException {
        URL rssLink = new URL("https://www.youtube.com/feeds/videos.xml?user=oplkanal");
        Document htmlDocument = Jsoup.parse(rssLink, 10000);
        this.videoIdTags = htmlDocument.getElementsByTag("yt:videoId");
    }

    @Override
    public Iterator<Video> iterator() {
        return new Iterator<Video>() {

            int counter = 0;

            @Override
            public boolean hasNext() {
                return counter < videoIdTags.size();
            }

            @Override
            public Video next() {
                String videoId = videoIdTags.get(counter).text();
                Video result = new YoutubeVideo(videoId);
                counter++;
                return result;
            }

        };
    }

}
