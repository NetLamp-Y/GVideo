package gvideo.platforms.youtube;

import gvideo.model.Video;
import gvideo.model.VideoSource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

public class YoutubeChannelVideoSource implements VideoSource {

    private final Elements entryTags;

    public YoutubeChannelVideoSource(String channelId) throws IOException {
        URL rssLink = new URL("https://www.youtube.com/feeds/videos.xml?channel_id=" + channelId);
        Document htmlDocument = Jsoup.parse(rssLink, 10000);
        this.entryTags = htmlDocument.getElementsByTag("entry");
    }

    @Override
    public Iterator<Video> iterator() {
        return new YoutubeVideoEntryTagIterator(entryTags);
    }
}
