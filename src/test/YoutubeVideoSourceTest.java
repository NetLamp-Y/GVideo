package test;

import gvideo.model.Video;
import gvideo.platforms.youtube.YoutubeChannelVideoSource;
import gvideo.platforms.youtube.YoutubeUserVideoSource;

import java.io.IOException;

public class YoutubeVideoSourceTest {

    public static void main(String[] args) throws IOException {
        for(Video v : new YoutubeChannelVideoSource("UCJgOALRoDjtFsgfmL3KBQKQ")){
            System.out.println(v);
        }
    }

}
