package gvideo.platforms.youtube;

import gvideo.model.Video;
import org.jsoup.select.Elements;

import java.util.Iterator;

public class YoutubeVideoSourceTagIterator implements Iterator<Video> {

    private final Elements videoIdTags;
    private int counter = 0;

    YoutubeVideoSourceTagIterator(Elements videoIdTags){
        this.videoIdTags = videoIdTags;
    }

    @Override
    public boolean hasNext() {
        return counter < videoIdTags.size();
    }

    @Override
    public YoutubeVideo next() {
        String videoId = videoIdTags.get(counter).text();
        YoutubeVideo result = new YoutubeVideo(videoId);
        counter++;
        return result;
    }

}
