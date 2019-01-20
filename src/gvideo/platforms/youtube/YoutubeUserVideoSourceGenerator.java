package gvideo.platforms.youtube;

import gvideo.model.VideoSource;
import gvideo.model.VideoSourceGenerator;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YoutubeUserVideoSourceGenerator implements VideoSourceGenerator {

    private static final String REGEX = "https?://www\\.youtube\\.com/user/([A-Za-z0-9_\\-]+).*";

    @Override
    public boolean isMatch(String url) {
        return url.matches(REGEX);
    }

    @Override
    public VideoSource generateIfMatch(String url) {
        Matcher m = Pattern.compile(REGEX).matcher(url);
        m.matches();
        try {
            return new YoutubeUserVideoSource(m.group(1));
        } catch (IOException e) {
            return null;
        }
    }

}
