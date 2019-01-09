package test;

import gvideo.model.Video;
import gvideo.platforms.youtube.YoutubeVideoSource;

import java.io.IOException;

public class YoutubeVideoSourceTest {

    public static void main(String[] args) throws IOException {
        for(Video v : new YoutubeVideoSource()){
            System.out.println(v);
        }
    }

}
