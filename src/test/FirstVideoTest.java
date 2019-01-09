package test;

import gvideo.platforms.youtube.YoutubeVideo;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FirstVideoTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        YoutubeVideo video = new YoutubeVideo("SXILfEb_Qu4");
        Node videoNode = video.createNode();

        BorderPane parent = new BorderPane(videoNode);
        primaryStage.setScene(new Scene(parent));

        primaryStage.show();
    }
}
