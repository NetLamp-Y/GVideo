package gvideo.platforms.youtube;

import gvideo.model.Video;
import javafx.scene.Node;
import javafx.scene.web.WebView;

public class YoutubeVideo implements Video {

    private final String videoId;
    private final String title;

    public YoutubeVideo(String videoId){
        this.videoId = videoId;
        this.title = null;
    }

    public YoutubeVideo(String id, String title) {
        this.videoId = id;
        this.title = title;
    }

    @Override
    public void play() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void setOnFinished(Runnable r) {

    }

    @Override
    public boolean supportsPlay() {
        return false;
    }

    @Override
    public boolean supportsPause() {
        return false;
    }

    @Override
    public boolean supportsOnFinished() {
        return false;
    }

    @Override
    public Node createNode() {
        WebView webView = new WebView();
        webView.getEngine().load("https://www.youtube.com/embed/" + videoId);
        return webView;
    }

    public String toString(){
        if(title == null){
            return super.toString();
        }else {
            return this.title;
        }
    }

}
