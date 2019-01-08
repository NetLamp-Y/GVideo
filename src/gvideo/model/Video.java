package gvideo.model;

import javafx.scene.Node;

public interface Video {

    void play();
    void pause();
    void setOnFinished(Runnable r);

    boolean supportsPlay();
    boolean supportsPause();
    boolean supportsOnFinished();

    Node createNode();

}
