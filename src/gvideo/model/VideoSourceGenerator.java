package gvideo.model;

public interface VideoSourceGenerator {

    boolean isMatch(String url);
    VideoSource generateIfMatch(String url);

}
