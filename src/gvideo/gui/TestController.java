package gvideo.gui;

import gvideo.model.Video;
import gvideo.model.VideoSource;
import gvideo.platforms.youtube.YoutubeChannelVideoSource;
import gvideo.platforms.youtube.YoutubeUserVideoSource;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TestController implements Initializable {

    @FXML
    private ListView<Video> videoList;

    @FXML
    private ListView<VideoSource> sourceList;

    @FXML
    private BorderPane videoParent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            YoutubeUserVideoSource source = new YoutubeUserVideoSource("oplkanal");
            sourceList.getItems().add(source);
            sourceList.getItems().add(new YoutubeChannelVideoSource("UCP1JVJUSoUuNxCAgOHQDCrw"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        sourceList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    videoList.getItems().clear();
                    if(newValue != null){
                        for(Video v : newValue){
                            videoList.getItems().add(v);
                        }
                    }
                }
        );

        videoList.getSelectionModel().selectedItemProperty().addListener(
                (videoObservable, oldValue, newValue) -> {
                    if(newValue != null){
                        videoParent.setCenter(newValue.createNode());
                    }
                }
        );
    }

}
