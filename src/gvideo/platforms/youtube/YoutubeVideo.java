package gvideo.platforms.youtube;

import gvideo.model.Video;
import javafx.scene.Node;
import javafx.scene.web.WebView;

public class YoutubeVideo implements Video {

    private final String videoId;

    public YoutubeVideo(String videoId){
        this.videoId = videoId;
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
        webView.getEngine().load("https://www.youtube.com/watch?v=" + videoId);
        return webView;
    }

}
