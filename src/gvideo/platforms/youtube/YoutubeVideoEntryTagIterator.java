package gvideo.platforms.youtube;

import gvideo.model.Video;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Iterator;

public class YoutubeVideoEntryTagIterator implements Iterator<Video> {

    private final Elements entryTags;
    private int counter = 0;

    public YoutubeVideoEntryTagIterator(Elements entryTags) {
        this.entryTags = entryTags;
    }

    @Override
    public boolean hasNext() {
        return counter < entryTags.size();
    }

    @Override
    public Video next() {
        Element entryTag = entryTags.get(counter);
        Elements titleElements = entryTag.getElementsByTag("title");
        Elements idElements = entryTag.getElementsByTag("yt:videoId");
        //Ist wirklich ein Element vorhanden? (kommt später) TODO
        counter++;
        return new YoutubeVideo(idElements.get(0).text(), titleElements.get(0).text());
    }

}
