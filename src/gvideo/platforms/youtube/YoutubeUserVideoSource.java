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

public class YoutubeUserVideoSource implements VideoSource {

    private final Elements entryTags;

    public YoutubeUserVideoSource(String userId) throws IOException {
        URL rssLink = new URL("https://www.youtube.com/feeds/videos.xml?user=" + userId);
        Document htmlDocument = Jsoup.parse(rssLink, 10000);
        this.entryTags = htmlDocument.getElementsByTag("entry");
    }

    @Override
    public Iterator<Video> iterator() {
        return new YoutubeVideoEntryTagIterator(entryTags);
    }

}
